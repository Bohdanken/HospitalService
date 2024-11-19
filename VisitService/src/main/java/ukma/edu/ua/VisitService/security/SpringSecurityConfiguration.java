package ukma.edu.ua.VisitService.security;

import lombok.RequiredArgsConstructor;
import ukma.edu.ua.VisitService.security.User.Roles;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;

@SuppressWarnings("LineLengthCheck")
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SpringSecurityConfiguration {
  private final JwtAuthFilter jwtAuthFilter;

  @Bean
  MvcRequestMatcher.Builder mvcRequestMatcher(HandlerMappingIntrospector introspector) {
    return new MvcRequestMatcher.Builder(introspector);
  }

  @Bean
  SecurityFilterChain filterChain(@NotNull HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
    return http
        .authorizeHttpRequests(request -> request
            .requestMatchers(mvc.pattern("/visit/**")).hasAuthority(Roles.PATIENT)
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
}