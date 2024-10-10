package ukma.edu.ua.HospitalApp.common.security;

import lombok.RequiredArgsConstructor;
import ukma.edu.ua.HospitalApp.common.Endpoints;
import ukma.edu.ua.HospitalApp.common.entities.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;

@SuppressWarnings("LineLengthCheck")
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfiguration {
  @Value("${app.prefix}")
  private String appPrefix;

  private final UserDetailsService userDetailsService;
  private final JwtAuthFilter jwtAuthFilter;

  @Bean
  MvcRequestMatcher.Builder mvcRequestMatcher(HandlerMappingIntrospector introspector) {
    return new MvcRequestMatcher.Builder(introspector);
  }

  @Bean
  SecurityFilterChain filterChain(@NotNull HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
    return http
        .authorizeHttpRequests(request -> request
            .requestMatchers(mvc.pattern(appPrefix + Endpoints.AUTH + "*/**")).permitAll()
            .requestMatchers(mvc.pattern(appPrefix + Endpoints.PATIENT + "*/**")).hasAuthority(User.Roles.PATIENT)
            .requestMatchers(mvc.pattern(appPrefix + Endpoints.PRESCRIPTION + "*/**")).hasAuthority(User.Roles.DOCTOR)
            .requestMatchers(mvc.pattern("/swagger*/**")).permitAll()
            .anyRequest().permitAll())
        .csrf(o -> o.disable())
        .sessionManagement((r) -> r.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .exceptionHandling((e) -> e
            .authenticationEntryPoint((request, response, authException) -> response.sendError(
                HttpServletResponse.SC_UNAUTHORIZED,
                authException.getMessage()))
            .accessDeniedHandler((request, response, authException) -> response.sendError(
                HttpServletResponse.SC_FORBIDDEN,
                authException.getMessage())))
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

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}