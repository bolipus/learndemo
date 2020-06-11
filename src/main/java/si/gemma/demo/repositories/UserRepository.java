package si.gemma.demo.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import si.gemma.demo.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);
}
