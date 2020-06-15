package example.controller.user.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class LoginRequest {
    private @NotNull @Length(min = 5, max = 45) String name;
    private @NotNull @Length(min = 5, max = 45) String password;
}
