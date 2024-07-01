package ems.employeemanagementsystem.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * This class provides security-related configurations for the application.
 * @author josemarin
 */
@Configuration
public class SecurityConfiguration {

    /**
     * Defines a PasswordEncoder bean that uses BCrypt hashing algorithm.
     *
     * @return a PasswordEncoder bean
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
