package com.veterinaria.PetCare.controller;

import com.veterinaria.PetCare.entity.Cita;
import com.veterinaria.PetCare.service.CitaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins = "http://localhost:5173") // Ajustado al puerto de Vite (React)
public class CitaController {

    private final CitaService citaService;

    // Inyección de dependencias por constructor (Recomendado sobre @Autowired en atributos)
    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    // Obtener todas las citas
    @GetMapping
    public ResponseEntity<List<Cita>> getAll() {
        List<Cita> citas = citaService.getAll();
        return ResponseEntity.ok(citas);
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
    public ResponseEntity<Cita> create(@RequestBody Cita cita) {
        Cita nuevaCita = citaService.save(cita);
        return new ResponseEntity<>(nuevaCita, HttpStatus.CREATED); // Devuelve 201 Created
    }

    // Actualizar una cita existente
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
            return ResponseEntity.noContent().build(); // Devuelve 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Obtener citas por ID de la mascota
    @GetMapping("/mascota/{mascotaId}")
    public ResponseEntity<List<Cita>> getCitasByMascota(@PathVariable Long mascotaId) {
        List<Cita> citas = citaService.getByMascotaId(mascotaId);
        if (citas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(citas);
    }
}