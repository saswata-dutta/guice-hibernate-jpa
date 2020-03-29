package org.sasdutta.persistors;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.sasdutta.entities.User;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemUserDao implements UserDao {
  private final Provider<EntityManager> emp;

  @Inject
  public InMemUserDao(Provider<EntityManager> emp) {
    this.emp = emp;
  }

  @Override
  public void save(User user) {
    emp.get().merge(user);
  }

  @Override
  public Optional<User> findById(long id) {
    return Optional.ofNullable(emp.get().find(User.class, id));
  }

  @Override
  public List<User> findByName(String name) {
    List<?> result = emp.get().createQuery(
        "SELECT u FROM User u WHERE u.name LIKE :name")
        .setParameter("name", name)
        .getResultList();

    List<User> users = new ArrayList<>();
    for (Object item : result) {
      if (item instanceof User) users.add((User) item);
    }

    return users;
  }
}
