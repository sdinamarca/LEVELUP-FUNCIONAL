package com.manejo.Pedidos.config;

import com.manejo.Pedidos.model.Rol;
import com.manejo.Pedidos.repository.RolRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initRoles(RolRepository rolRepository) {
        return args -> {

            if (rolRepository.findByNombre("ADMIN").isEmpty()) {
                rolRepository.save(new Rol(null, "ADMIN"));
            }

            if (rolRepository.findByNombre("USER").isEmpty()) {
                rolRepository.save(new Rol(null, "USER"));
            }

        };
    }
}
