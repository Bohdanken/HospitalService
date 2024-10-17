package ukma.edu.prescription.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
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
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(o -> o.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)) // Allow H2 console to be displayed in a frame
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/v3/api-docs/**")).permitAll()
                        // Allow access to H2 console
                        .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/actuator/**")).permitAll()
                        // Secure /api/admin/** with API key
                        .requestMatchers(new AntPathRequestMatcher("/api/admin/**")).authenticated()
                        .anyRequest().permitAll()) // Permit other requests
//                        .anyRequest().authenticated())
                .addFilterBefore(apiKeyAuthFilter(), UsernamePasswordAuthenticationFilter.class).build();
    }

    @Bean
    public ApiKeyAuthFilter apiKeyAuthFilter() {
        return new ApiKeyAuthFilter(validApiKey);
    }

    public static class ApiKeyAuthFilter extends OncePerRequestFilter {
        private static final String API_KEY_HEADER = "X-API-KEY";
        private final String validApiKey;

        public ApiKeyAuthFilter(String validApiKey) {
            this.validApiKey = validApiKey;
        }

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
                throws IOException, ServletException {
            if (new AntPathRequestMatcher("/api/admin/**").matches(request)) {
                String requestApiKey = request.getHeader(API_KEY_HEADER);

                if (validApiKey.equals(requestApiKey)) {
                    PreAuthenticatedAuthenticationToken token = new PreAuthenticatedAuthenticationToken(
                            new User("apiUser", "", new ArrayList<>()), null, new ArrayList<>());
                    SecurityContextHolder.getContext().setAuthentication(token);
                } else {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
            }

            chain.doFilter(request, response);
        }

//        @Override
//        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//                throws IOException, ServletException {
//            if (new AntPathRequestMatcher("/swagger-ui/**").matches(request) ||
//                    new AntPathRequestMatcher("/v3/api-docs/**").matches(request) ||
//                    new AntPathRequestMatcher("/h2-console/**").matches(request) || // Allow H2 console
//                    new AntPathRequestMatcher("/api/admin/**").matches(request)) {
//                chain.doFilter(request, response);
//                return;
//            }
//
//            String requestApiKey = request.getHeader(API_KEY_HEADER);
//            if (validApiKey.equals(requestApiKey)) {
//                PreAuthenticatedAuthenticationToken token = new PreAuthenticatedAuthenticationToken(
//                        new User("apiUser", "", new ArrayList<>()), null, new ArrayList<>());
//                SecurityContextHolder.getContext().setAuthentication(token);
//            } else {
//                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                return;
//            }
//
//            chain.doFilter(request, response);
//        }
    }
}
