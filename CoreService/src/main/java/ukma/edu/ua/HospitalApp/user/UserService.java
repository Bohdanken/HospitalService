package ukma.edu.ua.HospitalApp.user;

import lombok.RequiredArgsConstructor;
import ukma.edu.ua.HospitalApp.common.entities.User;
import ukma.edu.ua.HospitalApp.user.services.UserServiceInternal;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserServiceInternal userServiceInternal;

  public User registerNewUser(String email, String password, User.Role role) {
    return userServiceInternal.registerNewUser(email, password, role);
  }
}
