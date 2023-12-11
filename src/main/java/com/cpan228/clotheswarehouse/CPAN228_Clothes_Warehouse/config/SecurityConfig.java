package com.cpan228.clotheswarehouse.CPAN228_Clothes_Warehouse.config;

import com.cpan228.clotheswarehouse.CPAN228_Clothes_Warehouse.model.User;
import com.cpan228.clotheswarehouse.CPAN228_Clothes_Warehouse.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.annotation.web.builders.HttpSecurity.*;



/**
 * Configuration class for Spring is like a holder of beans. We can use this
 * class to define beans that we want to use in our application.
 */
@SuppressWarnings("ALL")
@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            User user = userRepository.findByUsername(username);
            if (user != null) {
                return user;
            }
            throw new UsernameNotFoundException(username);
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests()
                .requestMatchers(toH2Console()).permitAll()
                .requestMatchers("/addItem").hasAnyRole("ADMIN", "WAREHOUSE")
                .requestMatchers("/management").hasRole("ADMIN")
                .requestMatchers("/clothelist").hasRole("USER")
                .requestMatchers("/login").permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/clothelist", true)
                .failureUrl("/login?error=true")
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .and()
                .csrf()
                .ignoringRequestMatchers(toH2Console())
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()
                .and()
                .build();
    }

}
