package com.university.uch_university.config;

import com.university.uch_university.controllers.CustomAuthenticationSuccessHandler;
import com.university.uch_university.model.RoleEnum;
import com.university.uch_university.model.UserModel;
import com.university.uch_university.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthenticationSuccessHandler successHandler;

    public WebSecurityConfig(UserRepository userRepository,
                             PasswordEncoder passwordEncoder,
                             CustomAuthenticationSuccessHandler successHandler) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.successHandler = successHandler;
    }

    @PostConstruct
    public void createDefaultUser() {
        if (!userRepository.existsByUsername("admin")) {
            UserModel admin = new UserModel();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("Qwerty@123"));
            admin.setRoles(Collections.singleton(RoleEnum.ADMIN));
            userRepository.save(admin);
        }
        if (!userRepository.existsByUsername("director")) {
            UserModel director = new UserModel();
            director.setUsername("director");
            director.setPassword(passwordEncoder.encode("Qwerty@123"));
            director.setRoles(Collections.singleton(RoleEnum.DIRECTOR));
            userRepository.save(director);
        }
        if (!userRepository.existsByUsername("manager")) {
            UserModel Manager = new UserModel();
            Manager.setUsername("manager");
            Manager.setPassword(passwordEncoder.encode("Qwerty@123"));
            Manager.setRoles(Collections.singleton(RoleEnum.MANAGER));
            userRepository.save(Manager);
        }
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> {
            UserModel user = userRepository.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("Такой пользователь не существует!");
            }
            return new User(
                    user.getUsername(),
                    user.getPassword(),
                    user.getRoles()
            );
        }).passwordEncoder(passwordEncoder);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize ->
                        authorize.requestMatchers("/login", "/registration", "/api/**").permitAll()
                                .requestMatchers("/posts/**").hasAuthority("ADMIN")
                                .requestMatchers("/employees/**").hasAuthority("ADMIN")
                                .requestMatchers("/departments/**").hasAuthority("ADMIN")
                                .requestMatchers("/projects/**").hasAuthority("DIRECTOR")
                                .requestMatchers("/targets/**").hasAuthority("MANAGER")
                                .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler(successHandler)
                        .permitAll())
                .logout(logout -> logout.permitAll())
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable());

        return http.build();
    }
}
