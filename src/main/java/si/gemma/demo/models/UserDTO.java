package si.gemma.demo.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

  private Long id;

  private String username;


  private String password;


  private String firstName;


  private String lastName;

  private Set<RoleDTO> roles = new HashSet<>();

  private List<CommentDTO> comments;
}
