package org.sasdutta.persistors;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.sasdutta.entities.User;

import javax.annotation.Nonnull;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class InMemUserDao implements UserDao {
  private final Provider<EntityManager> emp;

  @Inject
  public InMemUserDao(Provider<EntityManager> emp) {
    this.emp = emp;
  }

  @Override
  public User save(@Nonnull User user) {
    EntityManager em = emp.get();

    if (user.getId() == null) {
      em.persist(user);
    } else {
      user = em.merge(user);
    }
    return user;
  }

  @Override
  public void delete(@Nonnull User user) {
    if (user.getId() != null) {
      EntityManager em = emp.get();
      em.createQuery("DELETE FROM User u WHERE u.id = :id ")
          .setParameter("id", user.getId())
          .executeUpdate();
    }
  }

  @Override
  public Optional<User> findById(long id) {
    return Optional.ofNullable(emp.get().find(User.class, id));
  }

  @Override
  public List<User> findByName(String name) {
    return emp.get()
        .createQuery("SELECT u FROM User u WHERE u.name LIKE :name", User.class)
        .setParameter("name", name)
        .getResultList();
  }
}
