package com.testProjects.todolist.security;

import org.springframework.boot.web.servlet.server.CookieSameSiteSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CookieConfig {

    @Bean
    public CookieSameSiteSupplier applicationCookieSameSiteSupplier() {
        // Appliquer SameSite=Lax pour JSESSIONID
        return CookieSameSiteSupplier.ofLax().whenHasName("JSESSIONID");
    }
}
