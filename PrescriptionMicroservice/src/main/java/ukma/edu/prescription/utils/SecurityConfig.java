package ukma.edu.prescription.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@Configuration
public class SecurityConfig {

    @Value("${api.key}")
    private String validApiKey;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(c -> c.disable())
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/v3/api-docs/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/admin/**")).permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(apiKeyAuthFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Define ApiKeyAuthFilter as a bean and inject the API key
    @Bean
    public ApiKeyAuthFilter apiKeyAuthFilter() {
        return new ApiKeyAuthFilter(validApiKey);
    }

    // Custom filter class to validate API key
    public static class ApiKeyAuthFilter extends OncePerRequestFilter {
        private static final String API_KEY_HEADER = "X-API-KEY";
        private final String validApiKey;

        // Constructor injection for the API key
        public ApiKeyAuthFilter(String validApiKey) {
            this.validApiKey = validApiKey;
        }

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
                throws IOException, ServletException {
            if (new AntPathRequestMatcher("/swagger-ui/**").matches(request) ||
                    new AntPathRequestMatcher("/v3/api-docs/**").matches(request) ||
                    new AntPathRequestMatcher("/api/admin/**").matches(request)) {
                chain.doFilter(request, response);
                return;
            }

            String requestApiKey = request.getHeader(API_KEY_HEADER);
            if (validApiKey.equals(requestApiKey)) {
                PreAuthenticatedAuthenticationToken token = new PreAuthenticatedAuthenticationToken(
                        new User("apiUser", "", new ArrayList<>()), null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(token);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            chain.doFilter(request, response);
        }
    }
}
