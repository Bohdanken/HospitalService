package ukma.edu.ua.HospitalApp.user;

import lombok.RequiredArgsConstructor;
import ukma.edu.ua.HospitalApp.user.repositories.UserRepository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    var user = userRepository
        .findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("There is no account with such email"));
    return User
        .withUsername(user.getEmail())
        .password(user.getPassword())
        .authorities(user.getRole().toString())
        .build();
  }
}
