package ukma.edu.ua.HospitalApp.thymeleaf.web;

public class SpringSecurityConfig {


//@Configuration
//@EnableWebSecurity
//public class SpringSecurityConfig {
//
//    public SpringSecurityConfig() {
//        super();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
//        http
//                .formLogin(formLogin -> formLogin
//                        .loginPage("/login"))
//                .logout(logout -> logout
//                        .logoutSuccessUrl("/login/login"))
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers(
//                                new AntPathRequestMatcher("/"),
//                                new AntPathRequestMatcher("/login/login"),
//                                new AntPathRequestMatcher("/operations"),
//                                new AntPathRequestMatcher("/css/**")).permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")
//                        .requestMatchers(new AntPathRequestMatcher("/patient/**")).hasRole("PATIENT")
//                        .requestMatchers(new AntPathRequestMatcher("/doctor/**")).hasRole("DOCTOR")
//                        .anyRequest().authenticated());
//        return http.build();
//    }
//
////    @Bean
////    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
////        http
////                .formLogin(formLogin -> formLogin
////                        .loginPage("/auth/login"))
////                .logout(logout -> logout
////                        .logoutSuccessUrl("/login/login"))
////                .authorizeHttpRequests(authorize -> authorize
////                        .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")
////                        .requestMatchers(new AntPathRequestMatcher("/patient/**")).hasRole("PATIENT")
////                        .requestMatchers(new AntPathRequestMatcher("/doctor/**")).hasRole("DOCTOR")
////                        .anyRequest().authenticated());
////        return http.build();
////    }
//
//
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        return new InMemoryUserDetailsManager(
//                User.withUsername("jim").password("22").roles("PATIENT").build(),
//                User.withUsername("bob").password("33").roles("DOCTOR").build());
//    }


}