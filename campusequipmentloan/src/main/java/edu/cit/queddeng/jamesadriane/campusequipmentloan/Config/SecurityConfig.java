package edu.cit.queddeng.jamesadriane.campusequipmentloan.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin())) // allow H2 console
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**", "/register", "/login")) // disable CSRF for H2 + REST endpoints
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**", "/register", "/login").permitAll() // allow without login
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults()) // default login form (session-based)
                .logout(Customizer.withDefaults());   // default logout at /logout

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
