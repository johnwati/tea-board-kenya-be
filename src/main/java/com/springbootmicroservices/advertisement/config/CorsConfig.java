//package com.springbootmicroservices.advertisement.config;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
//@Configuration
//public class CorsConfig {
//
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//
//        // Allow all origins
//        config.addAllowedOrigin("*");
//
//        // Allow specific HTTP methods (e.g., GET, POST, PUT, DELETE)
//        config.addAllowedMethod("*");
//
//        // Allow all headers in the request
//        config.addAllowedHeader("*");
//
//        // Allow credentials (e.g., cookies, authentication)
//        config.setAllowCredentials(true);
//
//        source.registerCorsConfiguration("/**", config);
//
//        return new CorsFilter(source);
//    }
//}
//
