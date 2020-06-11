package si.gemma.demo.services;

import java.util.List;
import java.util.Optional;
import si.gemma.demo.entities.Role;
import si.gemma.demo.exceptions.RoleNotFoundException;

public interface RoleService {
  List<Role> findAll();

  Role findById(Long id) throws RoleNotFoundException;

  Role findByName(String username) throws RoleNotFoundException;

  Role save(Role role);

  void delete(Long id);
}
