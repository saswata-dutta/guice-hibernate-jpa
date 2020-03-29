package org.sasdutta.services;

import org.sasdutta.entities.User;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

public interface Actions {
  List<User> upsertUsers(@Nonnull List<User> users);

  void delete(@Nonnull List<User> users);

  Optional<User> findUserById(long id);

  List<User> findUserByName(@Nonnull String name);
}
