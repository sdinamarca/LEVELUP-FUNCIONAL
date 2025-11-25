package com.manejo.Pedidos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // 1. Origen Permitido: La URL de tu aplicación de React
        config.addAllowedOrigin("http://localhost:3000");

        // 2. Cabeceras Permitidas: Permite todas, incluyendo 'Authorization' y 'Content-Type'
        config.addAllowedHeader("*");

        // 3. Métodos Permitidos: Permite GET, POST, PUT, DELETE, etc.
        config.addAllowedMethod("*");

        // 4. Permite el envío de credenciales (necesario para tokens de autenticación)
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 5. Aplica esta configuración a TODAS las rutas de la API (/**)
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}