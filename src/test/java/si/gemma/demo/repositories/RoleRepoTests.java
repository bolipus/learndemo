package si.gemma.demo.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import si.gemma.demo.entities.Role;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RoleRepoTests {

  private TestEntityManager entityManager;

  private RoleRepository roleRepository;

  @Autowired
  public RoleRepoTests(TestEntityManager entityManager, RoleRepository roleRepository) {
    this.entityManager = entityManager;
    this.roleRepository = roleRepository;
  }

  @Test
  public void findById() {
    Role role = new Role();
    role.setName("ROLE_ADMIN");
    role = entityManager.persist(role);

    Optional<Role> roleOptional = roleRepository.findById(role.getId());

    assertTrue(roleOptional.isPresent(), "Role was not present");
    if (roleOptional.isPresent()) {
      assertEquals(role.getName(), roleOptional.get().getName(), "Names of role are not equals");
    }
  }


  @Test
  public void findByName() {
    Role role = new Role();
    role.setName("ROLE_ADMIN");
    role = entityManager.persist(role);

    Optional<Role> roleOptional = roleRepository.findByName(role.getName());

    assertTrue(roleOptional.isPresent(), "Role was not present");
    if (roleOptional.isPresent()) {
      assertEquals(role.getName(), roleOptional.get().getName(), "Names of role are not equals");
    }
  }

}
