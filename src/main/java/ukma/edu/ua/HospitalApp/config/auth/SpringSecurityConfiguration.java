package ukma.edu.ua.HospitalApp.config.auth;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import ukma.edu.ua.HospitalApp.api.AuthControllerHttp;
import ukma.edu.ua.HospitalApp.api.auth.AuthController;
import ukma.edu.ua.HospitalApp.exceptions.handlers.CustomAuthenticationFailureHandler;
import ukma.edu.ua.HospitalApp.models.User;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfiguration {
  private final CustomUserDetailsService userDetailsService;
  private final JwtAuthFilter jwtAuthFilter;

  private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

  @Bean
  MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
    return new MvcRequestMatcher.Builder(introspector);
  }

  @Bean
  SecurityFilterChain filterChain(@NotNull HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
    AuthControllerHttp.logger.debug("We are in filter chain");
    return http
            .authorizeHttpRequests(request -> request
                    .requestMatchers(new AntPathRequestMatcher("/css*/**")).permitAll()
                    .requestMatchers(mvc.pattern("/login")).permitAll()  // Permit access to login page
                    //.requestMatchers(mvc.pattern("/**")).permitAll()  // Permit access to login page
                    .requestMatchers(mvc.pattern(AuthController.APP_PREFIX +"/**")).permitAll()
                    .requestMatchers(mvc.pattern(AuthController.APP_PREFIX +"/patient/**")).hasAuthority(User.Roles.PATIENT)
                    .requestMatchers(mvc.pattern("/swagger*/**")).permitAll()
                    .anyRequest().authenticated())
           /* .formLogin(form -> form
                    .loginPage("/login")  // Specify the custom login page URL
                    .failureHandler(customAuthenticationFailureHandler)
                    .permitAll())  // Permit all to access the form login
            */
            // CSRF configuration
            .csrf(AbstractHttpConfigurer::disable)
            // Add custom JWT authentication filter
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
  }




  @Bean
  DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService);
    provider.setPasswordEncoder(passwordEncoder());
    return provider;
  }

  //@Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}