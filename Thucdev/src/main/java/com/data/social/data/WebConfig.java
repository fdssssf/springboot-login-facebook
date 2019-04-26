package com.data.social.data;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/css/**",
                            "/fonts/**",
                            "/img/**",
                            "/js/**",
                            "/plugins/**")
                .addResourceLocations(
                        "classpath:/static/css/",
                        "classpath:/static/fonts/",
                        "classpath:/static/img/",
                        "classpath:/static/js/",
                        "classpath:/static/plugins/");

    }
}
