package si.gemma.demo.services.impl;

import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import lombok.extern.log4j.Log4j2;
import si.gemma.demo.entities.User;
import si.gemma.demo.repositories.UserRepository;

@Service
@Log4j2
public class UserDetailsServiceImpl implements UserDetailsService {

  private UserRepository userRepository;

  public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> userOptional = userRepository.findByUsername(username);
    if (!userOptional.isPresent()) {
      throw new UsernameNotFoundException("User with username " + username + "not found.");
    }
    return userOptional.get();
  }

}
