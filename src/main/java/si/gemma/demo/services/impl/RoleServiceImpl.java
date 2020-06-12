package si.gemma.demo.services.impl;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import lombok.extern.log4j.Log4j2;
import si.gemma.demo.entities.Role;
import si.gemma.demo.exceptions.RoleNotFoundException;
import si.gemma.demo.repositories.RoleRepository;
import si.gemma.demo.services.RoleService;

@Service
@Log4j2
public class RoleServiceImpl implements RoleService {

  private RoleRepository roleRepository;

  @Autowired
  public RoleServiceImpl(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @Override
  public List<Role> findAll() {
    return roleRepository.findAll();
  }

  @Override
  public Role findById(Long id) throws RoleNotFoundException {
    Optional<Role> roleOptional = roleRepository.findById(id);
    if (!roleOptional.isPresent()) {
      throw new RoleNotFoundException("Role with id " + id + " not found.");
    }
    return roleOptional.get();
  }

  @Override
  public Role findByName(String name) throws RoleNotFoundException {
    Optional<Role> roleOptional = roleRepository.findByName(name);
    if (!roleOptional.isPresent()) {
      throw new RoleNotFoundException("Role with name " + name + " not found.");
    }
    return roleOptional.get();
  }

  @Override
  @Transactional
  @Secured("ROLE_ADMIN")
  public Role save(Role role) {
    return roleRepository.save(role);
  }

  @Override
  @Transactional
  @Secured("ROLE_ADMIN")
  public Role update(Role updatedRole) throws RoleNotFoundException {
    Role role = findById(updatedRole.getId());
    role.setName(updatedRole.getName());
    return roleRepository.save(role);
  }

  @Override
  @Transactional
  @Secured("ROLE_ADMIN")
  public void delete(Long id) {
    roleRepository.deleteById(id);
  }

}
