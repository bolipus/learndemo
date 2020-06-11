package si.gemma.demo.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import si.gemma.demo.entities.Role;
import si.gemma.demo.entities.User;
import si.gemma.demo.models.RoleDTO;
import si.gemma.demo.models.UserDTO;
import si.gemma.demo.services.RoleService;
import si.gemma.demo.services.UserService;

@Controller
@RequestMapping(value = "api/v1")
public class UserController {

  private UserService userService;
  private RoleService roleService;
  private ModelMapper modelMapper;

  public UserController(UserService userService, RoleService roleService, ModelMapper modelMapper) {
    this.userService = userService;
    this.roleService = roleService;
    this.modelMapper = modelMapper;
  }

  @GetMapping("/users")
  public ResponseEntity<List<UserDTO>> findAllUser()
     List<UserDTO> users = userService.findAll().stream()
        .map(this::convertDTO)
        .collect(Collectors.toList());

     return ResponseEntity.ok(users);
  }

  private UserDTO convertDTO(User user) {
    return modelMapper.map(user, UserDTO.class);
  }

  private User convertEntity(UserDTO userDTO) {
    return modelMapper.map(userDTO, User.class);
  }

  private RoleDTO convertDTO(Role role) {
    return modelMapper.map(role, RoleDTO.class);
  }

  private Role convertEntity(RoleDTO roleDTO) {
    return modelMapper.map(roleDTO, Role.class);
  }
}
