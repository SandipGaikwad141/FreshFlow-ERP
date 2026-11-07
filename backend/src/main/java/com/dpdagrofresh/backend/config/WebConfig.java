package com.dpdagrofresh.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Expose local uploads folder
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");

        // Expose frontend product images folder
        registry.addResourceHandler("/frontend/images/**")
                .addResourceLocations("file:frontend/images/", "classpath:/static/frontend/images/");
    }
}