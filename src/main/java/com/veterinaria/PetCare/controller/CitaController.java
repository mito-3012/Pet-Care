package com.veterinaria.PetCare.controller;

import com.veterinaria.PetCare.entity.Cita;
import com.veterinaria.PetCare.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin("*")
public class CitaController {

    @Autowired
    private CitaService citaService;

    // Obtener todas las citas
    @GetMapping
    public List<Cita> getAll() {
        return citaService.getAll();
    }

    // Obtener una cita por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Cita> getById(@PathVariable Long id) {
        Optional<Cita> cita = citaService.getById(id);
        return cita.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva cita
    @PostMapping
    public Cita create(@RequestBody Cita cita) {
        return citaService.save(cita);
    }

    // Actualizar una cita existente (NUEVO)
    @PutMapping("/{id}")
    public ResponseEntity<Cita> update(@PathVariable Long id, @RequestBody Cita cita) {
        try {
            Cita citaActualizada = citaService.update(id, cita);
            return ResponseEntity.ok(citaActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build(); // Devuelve 400 si falla la validación
        }
    }

    // Eliminar una cita por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            citaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Obtener citas por ID de la mascota
    @GetMapping("/mascota/{mascotaId}")
    public List<Cita> getCitasByMascota(@PathVariable Long mascotaId) {
        return citaService.getByMascotaId(mascotaId);
    }
}