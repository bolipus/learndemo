package si.gemma.demo.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import si.gemma.demo.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

  Optional<Role> findById();

  Optional<Role> findByName(String name);
}
