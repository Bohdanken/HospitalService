package ukma.edu.ua.HospitalApp.user;

import lombok.RequiredArgsConstructor;
import ukma.edu.ua.HospitalApp.entities.internal.User;
import ukma.edu.ua.HospitalApp.exceptions.UniqueConstraintException;
import ukma.edu.ua.HospitalApp.user.repositories.UserRepository;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  public User registerNewUser(String email, String password, User.Role role) {
    var user = User
        .builder()
        .email(email)
        .password(passwordEncoder.encode(password))
        .role(role)
        .build();

    try {
      return userRepository.save(user);
    } catch (DataIntegrityViolationException e) {
      throw new UniqueConstraintException("User with specified credentials already exists");
    }
  }
}
