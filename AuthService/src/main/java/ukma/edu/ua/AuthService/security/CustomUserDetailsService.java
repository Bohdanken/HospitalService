package ukma.edu.ua.AuthService.security;

import lombok.RequiredArgsConstructor;
import ukma.edu.ua.AuthService.repositories.UserRepository;

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
    try {
      var user = userRepository.findByEmail(email).orElseThrow();
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