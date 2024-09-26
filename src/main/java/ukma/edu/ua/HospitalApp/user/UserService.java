package ukma.edu.ua.HospitalApp.user;

import lombok.RequiredArgsConstructor;
import ukma.edu.ua.HospitalApp.entities.UserDTO;
import ukma.edu.ua.HospitalApp.entities.internal.User;
import ukma.edu.ua.HospitalApp.exceptions.UniqueConstraintException;
import ukma.edu.ua.HospitalApp.user.repositories.UserRepository;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.context.ApplicationEventPublisher;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;
  private final ApplicationEventPublisher events;

  public User registerNewUser(String email, String password, User.Role role) {
    var user = User
        .builder()
        .email(email)
        .password(passwordEncoder.encode(password))
        .role(role)
        .build();

    try {
      var savedUser = userRepository.save(user);
      var userDTO = UserDTO.fromUser(savedUser);

      events.publishEvent(userDTO);

      return user;
    } catch (DataIntegrityViolationException e) {
      throw new UniqueConstraintException("User with specified credentials already exists");
    }
  }
}
