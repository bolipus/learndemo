package si.gemma.demo.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import si.gemma.demo.entities.User;
import si.gemma.demo.exceptions.UserNotFoundException;
import si.gemma.demo.models.UserDTO;
import si.gemma.demo.services.UserService;

@RestController
@RequestMapping(value = "/api/v1")
public class UserController {

  private UserService userService;
  private ModelMapper modelMapper;

  public UserController(UserService userService, ModelMapper modelMapper) {
    this.userService = userService;
    this.modelMapper = modelMapper;
  }

  @GetMapping("/users")
  public ResponseEntity<List<UserDTO>> findAllUser() {
    List<UserDTO> users = userService.findAll().stream().map(this::convertDTO).collect(Collectors.toList());

    return ResponseEntity.ok(users);
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<UserDTO> findUser(@PathVariable(name = "id", required = true) Long id)
      throws UserNotFoundException {
    User user = userService.findById(id);
    return ResponseEntity.ok(convertDTO(user));
  }

  @PostMapping("/users")
  public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
    User user = userService.save(convertEntity(userDTO));
    return ResponseEntity.ok(convertDTO(user));
  }

  @PutMapping("/users/{id}")
  public ResponseEntity<UserDTO> updateUser(@PathVariable(name = "id", required = true) Long id,
      @RequestBody UserDTO userDTO) throws UserNotFoundException {

    if (!id.equals(userDTO.getId())) {
      throw new UserNotFoundException("Id is not identical to body id");
    }

    User user = userService.update(convertEntity(userDTO));
    return ResponseEntity.ok(convertDTO(user));
  }

  private UserDTO convertDTO(User user) {
    return modelMapper.map(user, UserDTO.class);
  }

  private User convertEntity(UserDTO userDTO) {
    return modelMapper.map(userDTO, User.class);
  }

}
