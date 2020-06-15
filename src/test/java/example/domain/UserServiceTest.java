package example.domain;

import example.repository.user.UserRepository;
import example.repository.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;

    @Before
    public void setUp() {
        when(userRepository.save(any(User.class))).then(returnsFirstArg());
    }

    @Test
    public void encryptsPasswordWhenRegisteringUser() {
        final String password = "password";

        final User user = User.builder().password(password).build();
        final User savedUser = userService.registerUser(user);

        assertNotEquals(password, savedUser.getPassword());
    }

    @TestConfiguration
    static class UserServiceTestContextConfiguration {

        @Bean
        public UserService userService() {
            return new UserService();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }
}