package com.qw.config;


import org.springframework.web.filter.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {
    public CorsConfig() {

    }

    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:8080");
        // set if send cookie
        config.setAllowCredentials(true);

        // set allowed request methods
        config.addAllowedMethod("*");

        // set allowed header
        config.addAllowedHeader("*");

        // 为url添加映射路径
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", config);



        return new CorsFilter(corsSource);




    }
}
