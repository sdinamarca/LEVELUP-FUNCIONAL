package com.manejo.Pedidos.controller;

import com.manejo.Pedidos.model.Coleccion;
import com.manejo.Pedidos.service.ColeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coleccion")
public class ColeccionController {

    @Autowired
    private ColeccionService coleccionService;

    // Agregar hotwheel a la colección de un usuario
    @PostMapping("/add")
    public ResponseEntity<?> agregar(
            @RequestParam Long idUsuario,
            @RequestParam Long idHotwheel) {

        try {
            Coleccion c = coleccionService.addToCollection(idUsuario, idHotwheel);
            return ResponseEntity.ok(c);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Obtener colección completa del usuario
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<?> obtenerColeccion(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(coleccionService.getColeccionByUsuario(idUsuario));
    }


    // Eliminar item de colección
    @DeleteMapping("/{idColeccion}")
    public ResponseEntity<String> eliminar(@PathVariable Long idColeccion) {
        coleccionService.delete(idColeccion);
        return ResponseEntity.ok("Item de colección eliminado");
    }
}
