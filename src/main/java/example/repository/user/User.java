package example.repository.user;

import example.repository.role.Role;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;

    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(
            name = "user_has_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;
}
