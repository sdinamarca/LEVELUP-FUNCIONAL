package com.manejo.Pedidos.service;

import com.manejo.Pedidos.model.UsuarioHW;
import com.manejo.Pedidos.repository.HotwheelRepository;
import com.manejo.Pedidos.repository.UsuarioHWRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class UsuarioHWServiceTest {



    @Test
    void testLoginExitoso() {

        UsuarioHWRepository repo = Mockito.mock(UsuarioHWRepository.class);
        UsuarioHWService service = new UsuarioHWService();
        service.setUsuarioHWRepository(repo);

        UsuarioHW u = new UsuarioHW();
        u.setEmail("test@email.com");
        u.setContrasena("1234");

        when(repo.findByEmail("test@email.com")).thenReturn(Optional.of(u));

        UsuarioHW resultado = service.login("test@email.com", "1234");

        assertNotNull(resultado);
    }

    @Test
    void testLoginFallido() {

        UsuarioHWRepository repo = Mockito.mock(UsuarioHWRepository.class);
        UsuarioHWService service = new UsuarioHWService();
        service.setUsuarioHWRepository(repo);

        when(repo.findByEmail("x@email.com")).thenReturn(Optional.empty());

        UsuarioHW resultado = service.login("x@email.com", "incorrecto");

        assertNull(resultado);
    }
}
