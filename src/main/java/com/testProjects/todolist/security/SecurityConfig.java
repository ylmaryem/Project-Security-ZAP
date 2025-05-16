package com.testProjects.todolist.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/signin", "/signup").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/signin")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/signin")
                .permitAll()
            )
            .headers(headers -> headers
            	    .contentSecurityPolicy(csp -> csp
            	        .policyDirectives("default-src 'self'; " +
            	                          "script-src 'self' 'unsafe-inline' https://cdn.jsdelivr.net https://stackpath.bootstrapcdn.com; " +
            	                          "style-src 'self' 'unsafe-inline' https://cdn.jsdelivr.net https://stackpath.bootstrapcdn.com; " +
            	                          "font-src 'self' https://cdn.jsdelivr.net https://stackpath.bootstrapcdn.com; " +
            	                          "img-src 'self' data:; " +
            	                          "object-src 'none'; " +
            	                          "frame-ancestors 'none'; " +
            	                          "form-action 'self';")
            	    )
            	    .httpStrictTransportSecurity(hsts -> hsts
            	        .maxAgeInSeconds(31536000)
            	        .includeSubDomains(true)
            	    )
            	    .frameOptions(frameOptions -> frameOptions.deny())
            	    .xssProtection(xss -> xss.disable())  
            	    .contentTypeOptions(contentTypeOptions -> contentTypeOptions.disable())
            	);


        return http.build();
    }
   
}
