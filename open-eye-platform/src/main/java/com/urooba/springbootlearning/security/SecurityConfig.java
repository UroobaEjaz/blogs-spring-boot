package com.urooba.springbootlearning.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/openEye/allUsers", "/openEye/{id}/viewUser", "/openEye/addUser", "openEye/updateUser/{id}",
                        "/openEye/delete/{id}", "/openEye/allPosts", "openEye/{id}/viewPost", "/openEye/createPost/{id}",
                        "/openEye/editPost/{id}", "/openEye/deletePost/{id}").authenticated()
                .requestMatchers("/welcome", "/websiteFeatures", "ContactUs","/").permitAll()
        );
//        http.formLogin(withDefaults());  Usual formLogin
//        http.formLogin((flc)->flc.disable());   //disabling the form logging and pop up
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }

    //Creating another bean
   @Bean
    public UserDetailsService userDetailsService(){
   UserDetails user = User.withUsername("User").password("{noop}12345").authorities("read").build();
   return new InMemoryUserDetailsManager(user);
    }
}





