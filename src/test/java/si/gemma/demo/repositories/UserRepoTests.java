package si.gemma.demo.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import si.gemma.demo.entities.User;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepoTests {

  private TestEntityManager entityManager;

  private UserRepository userRepository;

  @Autowired
  public UserRepoTests(TestEntityManager entityManager, UserRepository userRepository) {
    this.entityManager = entityManager;
    this.userRepository = userRepository;
  }

  @Test
  public void findById() {
    User user = new User();
    user.setUsername("username");
    user.setFirstName("firstName");
    user.setLastName("lastName");
    user = entityManager.persist(user);
    entityManager.flush();

    Optional<User> userOptional = userRepository.findById(user.getId());

    assertTrue(userOptional.isPresent(), "User not saved properly.");
    if (userOptional.isPresent()) {
      assertEquals(user.getUsername(), userOptional.get().getUsername(),
          "Usernames are not equals");
    }

  }

  @Test
  public void findByUsername() {
    User user = new User();
    user.setUsername("username");
    user.setFirstName("firstName");
    user.setLastName("lastName");
    user = entityManager.persist(user);
    entityManager.flush();

    Optional<User> userOptional = userRepository.findByUsername(user.getUsername());

    assertTrue(userOptional.isPresent(), "User not saved properly.");
    if (userOptional.isPresent()) {
      assertEquals(user.getUsername(), userOptional.get().getUsername(),
          "Usernames are not equals");
    }

  }

  @Test
  public void findAll() {
    User user = new User();
    user.setUsername("username");
    user.setFirstName("firstName");
    user.setLastName("lastName");
    user = entityManager.persist(user);
    entityManager.flush();

    List<User> users = userRepository.findAll();


    assertEquals(1, users.size(), "The number of the users is not 1.");
    assertEquals(user.getUsername(), users.get(0).getUsername(), "Usernames are not equals");

  }
}
