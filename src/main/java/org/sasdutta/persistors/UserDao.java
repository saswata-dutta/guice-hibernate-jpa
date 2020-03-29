package org.sasdutta.persistors;

import org.sasdutta.entities.User;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

public interface UserDao {
  User save(@Nonnull User user);

  void delete(@Nonnull User user);

  Optional<User> findById(long id);

  List<User> findByName(String name);
}
