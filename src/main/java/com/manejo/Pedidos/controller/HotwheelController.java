package com.manejo.Pedidos.controller;

import com.manejo.Pedidos.model.Hotwheels;
import com.manejo.Pedidos.service.HotwheelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotwheels")
public class HotwheelController {

    @Autowired
    private HotwheelService hotwheelService;

    // Crear hotwheel
    @PostMapping
    public ResponseEntity<Hotwheels> crear(@RequestBody Hotwheels hw) {
        return ResponseEntity.ok(hotwheelService.save(hw));
    }

    // Listar todos
    @GetMapping
    public ResponseEntity<List<Hotwheels>> listar() {
        return ResponseEntity.ok(hotwheelService.findAll());
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        Hotwheels hw = hotwheelService.findById(id);

        if (hw == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(hw);
    }

    // Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Hotwheels datos) {
        Hotwheels actualizado = hotwheelService.update(id, datos);

        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(actualizado);
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        hotwheelService.delete(id);
        return ResponseEntity.ok("Hotwheel eliminado");
    }
}
