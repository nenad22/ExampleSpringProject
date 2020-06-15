package example.domain;

import example.repository.user.UserRepository;
import example.repository.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Collection<User> getUsers() {
        return userRepository.findAll();
    }

    public User registerUser(final User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        return userRepository.save(newUser);
    }


}
