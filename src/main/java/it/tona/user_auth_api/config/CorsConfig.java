package it.tona.user_auth_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")                    // tutte le rotte
                        .allowedOrigins("http://localhost:5173")  // frontend origin
                        .allowedOrigins("https://user-auth-fe-production.up.railway.app") // production frontend origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // metodi permessi
                        .allowCredentials(true)
                        .allowedHeaders("");
            }
        };
    }

}
