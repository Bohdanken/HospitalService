package ukma.edu.ua.HospitalApp.common.security;

import lombok.RequiredArgsConstructor;
import ukma.edu.ua.HospitalApp.common.entities.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
  private final EntityManager em;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    try {
      var query = em
          .createQuery("SELECT u FROM " + User.class.getSimpleName() + " u WHERE u.email = :email");
      query.setParameter("email", email);
      var user = (User) query.getSingleResult();

      return AppUser
          .builder()
          .userId(user.getId())
          .username(user.getEmail())
          .password(user.getPassword())
          .role(user.getRole())
          .build();
    } catch (Exception e) {
      throw new UsernameNotFoundException("User not found");
    }
  }
}
