package si.gemma.demo.services;

import java.util.List;
import si.gemma.demo.entities.User;
import si.gemma.demo.exceptions.UserNotFoundException;

public interface UserService {

  List<User> findAll();

  User findById(long id) throws UserNotFoundException;

  User findByUsername(String username) throws UserNotFoundException;

  User save(User user);

  User update(User updatedUser) throws UserNotFoundException;

  void delete(long id);
}
