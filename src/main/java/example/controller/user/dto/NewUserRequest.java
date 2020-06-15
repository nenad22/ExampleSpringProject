package example.controller.user.dto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Getter
@Setter
public class NewUserRequest {
    private @NotNull @Size(min = 5, max = 45) String name;
    private @NotNull @Size(min = 5, max = 45) String password;
    private @NotNull @NotEmpty Collection<Long> roles;
}
