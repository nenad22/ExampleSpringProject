package example.objectmappers;

import example.controller.user.dto.NewUserRequest;
import example.controller.user.dto.UserDTO;
import example.repository.role.Role;
import example.repository.user.User;

import java.util.stream.Collectors;

public class UserObjecMapper {

    public static User userFromUserRequest(final NewUserRequest newUserRequest) {
        return User.builder()
                .name(newUserRequest.getName())
                .password(newUserRequest.getPassword())
                .roles(
                        newUserRequest
                                .getRoles().stream()
                                .map(roleId -> new Role(roleId, ""))
                                .collect(Collectors.toList())
                )
                .build();
    }

    public static UserDTO userDTOFromUser(final User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getRoles()
        );
    }
}
