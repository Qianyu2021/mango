 package com.Mangos.MangosBlog.configuration;

 import org.springframework.context.annotation.Configuration;
 import org.springframework.web.servlet.config.annotation.CorsRegistry;
 import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")  // Specify the origin
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Allow all common methods
                .maxAge(3600);  // 1 hour
    }
}


