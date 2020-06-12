package si.gemma.demo.services.impl;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import lombok.extern.log4j.Log4j2;
import si.gemma.demo.entities.User;
import si.gemma.demo.exceptions.UserNotFoundException;
import si.gemma.demo.repositories.UserRepository;
import si.gemma.demo.services.UserService;

@Service
@Log4j2
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
  public User findByUsername(String username) throws UserNotFoundException {
    Optional<User> userOptional = userRepository.findByUsername(username);
    if (!userOptional.isPresent()) {
      throw new UserNotFoundException("User with username " + username + " not found");
    }
    return userOptional.get();
  }

  @Override
  @Transactional
  @Secured("ROLE_ADMIN")
  public User save(User user) {
    return userRepository.save(user);
  }

  @Override
  @Transactional
  @Secured("ROLE_ADMIN")
  public User update(User updatedUser) throws UserNotFoundException {
    User user = findById(updatedUser.getId());

    user.setFirstName(updatedUser.getFirstName());
    user.setLastName(updatedUser.getLastName());
    user.setUsername(updatedUser.getUsername());

    return userRepository.save(user);
  }

  @Override
  @Transactional
  @Secured("ROLE_ADMIN")
  public void delete(long id) {
    userRepository.deleteById(id);
  }

}
