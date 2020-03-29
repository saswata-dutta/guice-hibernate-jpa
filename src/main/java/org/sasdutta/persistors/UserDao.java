package org.sasdutta.persistors;

import org.sasdutta.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
  void save(User user);

  Optional<User> findById(long id);

  List<User> findByName(String name);
}
