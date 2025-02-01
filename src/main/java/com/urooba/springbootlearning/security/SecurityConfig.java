//package com.urooba.springbootlearning.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.password.CompromisedPasswordChecker;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((requests) -> requests
//                .requestMatchers("/openEye/allUsers", "/openEye/{id}/viewUser", "/openEye/addUser", "openEye/updateUser/{id}",
//                        "/openEye/delete/{id}", "/openEye/allPosts", "openEye/{id}/viewPost", "/openEye/createPost/{id}",
//                        "/openEye/editPost/{id}", "/openEye/deletePost/{id}").authenticated()
//                .requestMatchers("/welcome", "/websiteFeatures", "ContactUs","/").permitAll()
//        );
////        http.formLogin(withDefaults());  Usual formLogin
////        http.formLogin((flc)->flc.disable());   //disabling the form logging and pop up
//        http.formLogin(withDefaults());
//        http.httpBasic(withDefaults());
//        return http.build();
//    }
//
//    //Creating another bean
//   @Bean
//    public UserDetailsService userDetailsService(){
//   UserDetails user = User.withUsername("User").password("{bcrypt}$2a$10$Q7KrxUDKlA34pY3n4PErDu05U4stEHrUiT.Mt.UhUx1VGhZGSXdlG").authorities("read").build();
//   return new InMemoryUserDetailsManager(user);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
//    //For security reasons, stronger passwords are recommended; this method will stop end users from using simple passwords
////
////    @Bean
////    public CompromisedPasswordChecker compromisedPasswordChecker(){
////        return new HaveIBeenPwnedRestApiPasswordChecker();
////    }
//}
//
//
//
//
//
