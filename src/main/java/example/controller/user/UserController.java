package example.controller.user;

import example.controller.user.dto.NewUserRequest;
import example.controller.user.dto.UserDTO;
import example.domain.UserService;
import example.objectmappers.UserObjecMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public Collection<UserDTO> getUsers() {
        return userService.getUsers()
                .stream()
                .map(UserObjecMapper::userDTOFromUser)
                .collect(Collectors.toList());
    }

    @PostMapping("/")
    public UserDTO registerUser(@Valid @RequestBody final NewUserRequest newUser) {
        return UserObjecMapper.userDTOFromUser(userService.registerUser(UserObjecMapper.userFromUserRequest(newUser)));
    }

}
