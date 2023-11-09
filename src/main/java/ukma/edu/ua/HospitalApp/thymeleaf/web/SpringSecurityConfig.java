package ukma.edu.ua.HospitalApp.thymeleaf.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    public SpringSecurityConfig() {
        super();
    }


    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        http
                .formLogin(formLogin -> formLogin
                        .loginPage("/login/login.html"))
                .logout(logout -> logout
                        .logoutSuccessUrl("/login/login.html"))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                new AntPathRequestMatcher("/"),
                                new AntPathRequestMatcher("login/login.html"),
                                new AntPathRequestMatcher("/operations.html"),
                                new AntPathRequestMatcher("/css/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/patient/**")).hasRole("PATIENT")
                        .requestMatchers(new AntPathRequestMatcher("/doctor/**")).hasRole("DOCTOR")
                        .anyRequest().authenticated());
        return http.build();
    }


    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername("jim").password("{noop}demo").roles("ADMIN").build(),
                User.withUsername("bob").password("{noop}demo").roles("USER").build(),
                User.withUsername("ted").password("{noop}demo").roles("USER","ADMIN").build());
    }


}