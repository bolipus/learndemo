package si.gemma.demo.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import si.gemma.demo.entities.Role;
import si.gemma.demo.exceptions.RoleNotFoundException;
import si.gemma.demo.models.RoleDTO;
import si.gemma.demo.services.RoleService;

@RestController
@RequestMapping(value = "/api/v1")
public class RoleController {

    private RoleService roleService;

    private ModelMapper modelMapper;

    @Autowired
    public RoleController(RoleService roleService, ModelMapper modelMapper) {
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/roles")
    public ResponseEntity<List<RoleDTO>> findAllRoles() {
        List<RoleDTO> roles = roleService.findAll().stream().map(this::convertDTO).collect(Collectors.toList());
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<RoleDTO> findRole(@PathVariable(name = "id", required = true) Long id)
            throws RoleNotFoundException {
        Role role = roleService.findById(id);
        return ResponseEntity.ok(convertDTO(role));
    }

    @PostMapping("/roles")
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO) {
        Role role = roleService.save(convertEntity(roleDTO));
        return ResponseEntity.ok(convertDTO(role));
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable(name = "id", required = true) Long id,
            @RequestBody RoleDTO roleDTO) throws RoleNotFoundException {

        if (!id.equals(roleDTO.getId())) {
            throw new RoleNotFoundException("Id is not identical to body id");
        }

        Role role = roleService.update(convertEntity(roleDTO));
        return ResponseEntity.ok(convertDTO(role));
    }

    private RoleDTO convertDTO(Role role) {
        return modelMapper.map(role, RoleDTO.class);
    }

    private Role convertEntity(RoleDTO roleDTO) {
        return modelMapper.map(roleDTO, Role.class);
    }
}