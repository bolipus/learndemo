package si.gemma.demo.config;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import si.gemma.demo.entities.User;

public class AuditorAwareImpl implements AuditorAware<String> {

  @Override
  public Optional<String> getCurrentAuditor() {
    if (SecurityContextHolder.getContext().getAuthentication() == null) {
      return Optional.of("admin@gmail.com");
    }

    String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
        .getUsername();

    return Optional.of(username);
  }

}
