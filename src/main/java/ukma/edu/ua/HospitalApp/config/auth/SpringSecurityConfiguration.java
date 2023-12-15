package ukma.edu.ua.HospitalApp.config.auth;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import ukma.edu.ua.HospitalApp.api.AuthControllerHttp;
import ukma.edu.ua.HospitalApp.api.auth.AuthController;
import ukma.edu.ua.HospitalApp.models.User;
import ukma.edu.ua.HospitalApp.config.Endpoints;
import ukma.edu.ua.HospitalApp.models.User;

@SuppressWarnings("LineLengthCheck")
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfiguration {
  private final CustomUserDetailsService userDetailsService;
  private final JwtAuthFilter jwtAuthFilter;

  @Bean
  MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
    return new MvcRequestMatcher.Builder(introspector);
  }

  @Bean
  SecurityFilterChain filterChain(@NotNull HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
    AuthControllerHttp.logger.debug("We are in filter chain");
    String patternLogin=AuthController.APP_PREFIX+"" + Endpoints.AUTH + "*/**";
    return http
            .authorizeHttpRequests(request -> request
                    .requestMatchers(new AntPathRequestMatcher("/css*/**")).permitAll()
                    .requestMatchers(mvc.pattern("/login")).permitAll()  // Permit access to login page
                   // .requestMatchers(mvc.pattern("/api/auth*/**")).permitAll()
                    .requestMatchers(mvc.pattern(patternLogin)).permitAll()
                    .requestMatchers(mvc.pattern(AuthController.APP_PREFIX + Endpoints.PATIENT + "/**")).hasAuthority(User.Roles.PATIENT)
                    .requestMatchers(mvc.pattern(AuthController.APP_PREFIX + Endpoints.PRESCRIPTION + "/**")).hasAuthority(User.Roles.DOCTOR)
                    .requestMatchers(mvc.pattern("/swagger*/**")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                    .anyRequest().authenticated())
           /* .formLogin(form -> form
                    .loginPage("/login")  // Specify the custom login page URL
                    .failureHandler(customAuthenticationFailureHandler)
                    .permitAll())  // Permit all to access the form login*/
            .csrf(AbstractHttpConfigurer::disable)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            //.headers(c -> c.frameOptions(f -> f.sameOrigin()))
            .build();
  }




  @Bean
  DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService);
    provider.setPasswordEncoder(passwordEncoder());
    return provider;
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}