package com.manejo.Pedidos.service;

import com.manejo.Pedidos.model.Hotwheels;
import com.manejo.Pedidos.repository.HotwheelRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class HotwheelServiceTest {



    @Test
    void testFindById() {

        HotwheelRepository repo = mock(HotwheelRepository.class);
        HotwheelService service = new HotwheelService();
        service.setHotwheelRepository(repo); // agregar setter o constructor si no existe

        Hotwheels hw = new Hotwheels();
        hw.setId(1L);
        hw.setModelo("DeLorean");

        when(repo.findById(1L)).thenReturn(Optional.of(hw));

        Hotwheels resultado = service.findById(1L);

        assertNotNull(resultado);
        assertEquals("DeLorean", resultado.getModelo());
        verify(repo, times(1)).findById(1L);
    }
}
