package si.gemma.demo.services.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.gemma.demo.entities.User;
import si.gemma.demo.exceptions.UserNotFoundException;
import si.gemma.demo.repositories.UserRepository;
import si.gemma.demo.services.UserService;

@Service
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;


  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }

  @Override
  public User findById(long id) throws UserNotFoundException {
    Optional<User> userOptional = userRepository.findById(id);
    if (!userOptional.isPresent()) {
      throw new UserNotFoundException("User with id " + id + " not found");
    }
    return userOptional.get();
  }

  @Override
  public User findByUserName(String username) throws UserNotFoundException {
    Optional<User> userOptional = userRepository.findByUsername(username);
    if (!userOptional.isPresent()) {
      throw new UserNotFoundException("User with username " + username + " not found");
    }
    return userOptional.get();
  }

  @Override
  public User save(User user) {
    return userRepository.save(user);
  }

  @Override
  public void delete(long id) {
    userRepository.deleteById(id);
  }

}
