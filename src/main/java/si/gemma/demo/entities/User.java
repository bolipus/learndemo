package si.gemma.demo.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = "roles")
@Data
public class User implements UserDetails {


  private static final long serialVersionUID = -1672300653972159683L;

  @Id
  @SequenceGenerator(name = "USER_SEQ", allocationSize = 25, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
  private Long id;

  @NonNull
  @Size(min = 3, max = 20)
  @Column(nullable = false, unique = true)
  private String username;

  @NonNull
  private String password;

  @NonNull
  private String firstName;

  @NonNull
  private String lastName;

  @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
  @JoinTable(name = "users_roles",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
  private Set<Role> roles = new HashSet<>();

  @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, orphanRemoval = true,
      fetch = FetchType.LAZY)
  private transient List<Comment> comments;

  @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, orphanRemoval = true,
      fetch = FetchType.LAZY)
  private transient List<Article> articles;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles.stream().map(Role::getName).map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
  }

  public String getFullName() {
    return firstName + " " + lastName;
  }

  public void addRole(Role userRole) {
    roles.add(userRole);
    userRole.addUser(this);
  }

  public void addRoles(Set<Role> userRoles) {
    roles.addAll(userRoles);
    userRoles.forEach(role -> role.addUser(this));
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {

    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }


}
