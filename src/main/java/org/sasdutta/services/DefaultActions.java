package org.sasdutta.services;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import org.sasdutta.entities.User;
import org.sasdutta.persistors.UserDao;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

public class DefaultActions implements Actions {
  private final UserDao userDao;

  @Inject
  public DefaultActions(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  @Transactional(rollbackOn = {Exception.class})
  public void upsertUsers(@Nonnull List<User> users) {
    for (User user : users) {
      userDao.save(user);
    }
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
