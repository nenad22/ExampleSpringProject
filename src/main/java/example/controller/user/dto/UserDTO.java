package example.controller.user.dto;

import example.repository.role.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private Collection<Role> roles;
}
