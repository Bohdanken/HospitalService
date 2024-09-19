package ukma.edu.ua.HospitalApp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import ukma.edu.ua.HospitalApp.entities.User;
import ukma.edu.ua.HospitalApp.entities.User.Role;
import ukma.edu.ua.HospitalApp.exceptions.UniqueConstraintException;
import ukma.edu.ua.HospitalApp.user.UserService;
import ukma.edu.ua.HospitalApp.user.repositories.UserRepository;

@DisplayName("UserService unit test")
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
  @InjectMocks
  private UserService userService;

  @Mock
  private UserRepository userRepository;

  @Mock
  private PasswordEncoder passwordEncoder;

  private String email = "some@email.com";

  private String password = "somepass";

  private User.Role role = Role.PATIENT;

  @Test
  @Order(1)
  @DisplayName("It should register new user")
  public void registerNewUser() {
    when(passwordEncoder.encode(any())).thenReturn("ENCODED PASSWORD");
    when(userRepository.save(any()))
        .thenAnswer(a -> {
          User user = a.getArgument(0);
          user.setId(Long.valueOf(1));
          return user;
        });

    var response = userService.registerNewUser(email, password, role);
    assertEquals(response.getEmail(), email, "Email is incorrect");
    assertEquals(response.getPassword(), "ENCODED PASSWORD", "Password is not encoded");
    assertEquals(response.getRole(), role, "Role is incorrect");
    assertNotNull(response.getId(), "Id is null");
  }

  @Test
  @Order(2)
  @DisplayName("It should throw an error if user is already registered")
  public void registerOldUser() {
    when(userRepository.save(any())).thenThrow(new DataIntegrityViolationException(""));
    assertThrows(UniqueConstraintException.class,
        () -> userService.registerNewUser(email, password, role),
        "Unique constraint check is not working");
  }
}
