package com.manejo.Pedidos.service;

import com.manejo.Pedidos.model.Hotwheels;
import com.manejo.Pedidos.repository.ColeccionRepository;
import com.manejo.Pedidos.repository.HotwheelRepository;
import com.manejo.Pedidos.repository.UsuarioHWRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ColeccionServiceTest {

    @Test
    void testEvitarDuplicado() {

        ColeccionRepository colecRepo = mock(ColeccionRepository.class);
        UsuarioHWRepository userRepo = mock(UsuarioHWRepository.class);
        HotwheelRepository hotRepo = mock(HotwheelRepository.class);

        ColeccionService service = new ColeccionService();
        service.setColeccionRepository(colecRepo);
        service.setUsuarioHWRepository(userRepo);
        service.setHotwheelRepository(hotRepo);

        when(colecRepo.existsByUsuarioIdAndHotwheelId(1L, 1L)).thenReturn(true);

        Exception ex = assertThrows(RuntimeException.class, () -> {
            service.addToCollection(1L, 1L);
        });

        assertTrue(ex.getMessage().contains("ya está en la colección"));
    }
}
