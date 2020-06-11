package si.gemma.demo.services;

import java.util.List;
import si.gemma.demo.entities.User;
import si.gemma.demo.exceptions.UserNotFoundException;

public interface UserService {

  List<User> findAll();

  User findById(long id) throws UserNotFoundException;

  User findByUserName(String username) throws UserNotFoundException;

  User save(User user);

  void delete(long id);
}
