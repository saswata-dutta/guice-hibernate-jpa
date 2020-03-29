package org.sasdutta.services;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import org.sasdutta.entities.User;
import org.sasdutta.persistors.UserDao;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DefaultActions implements Actions {
  private final UserDao userDao;

  @Inject
  public DefaultActions(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  @Transactional
  public List<User> upsertUsers(@Nonnull List<User> users) {
    List<User> persisted = new ArrayList<>();
    for (User user : users) {
      persisted.add(userDao.save(user));
    }

    return persisted;
  }

  @Override
  @Transactional
  public void delete(@Nonnull List<User> users) {
    users.forEach(userDao::delete);
  }

  @Override
  public Optional<User> findUserById(long id) {
    return userDao.findById(id);
  }

  @Override
  public List<User> findUserByName(@Nonnull String name) {
    return userDao.findByName(name);
  }
}
