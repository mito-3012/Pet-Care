package com.veterinaria.PetCare.controller;

import com.veterinaria.PetCare.entity.Cita;
import com.veterinaria.PetCare.entity.Mascota;
import com.veterinaria.PetCare.service.CitaService;
import com.veterinaria.PetCare.service.MascotaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
@CrossOrigin(origins = "http://localhost:5173") // Seguridad CORS para tu frontend en React
public class MascotaController {

    private final MascotaService mascotaService;
    private final CitaService citaService;

    // Inyección de dependencias mediante el constructor
    public MascotaController(MascotaService mascotaService, CitaService citaService) {
        this.mascotaService = mascotaService;
        this.citaService = citaService;
    }

    @GetMapping
    public ResponseEntity<List<Mascota>> getAll() {
        List<Mascota> mascotas = mascotaService.getAll();
        return ResponseEntity.ok(mascotas);
    }

    @PostMapping
    public ResponseEntity<Mascota> create(@RequestBody Mascota mascota) {
        Mascota nuevaMascota = mascotaService.save(mascota);
        return new ResponseEntity<>(nuevaMascota, HttpStatus.CREATED); // Respuesta 201 Created
    }

    // Endpoint de relación solicitado
    @GetMapping("/{id}/citas")
    public ResponseEntity<List<Cita>> getCitasByMascota(@PathVariable Long id) {
        List<Cita> citas = citaService.getByMascotaId(id);
        if (citas.isEmpty()) {
            return ResponseEntity.noContent().build(); // Respuesta 204 No Content si está vacío
        }
        return ResponseEntity.ok(citas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mascota> update(@PathVariable Long id, @RequestBody Mascota mascota) {
        try {
            mascota.setId(id);
            Mascota mascotaActualizada = mascotaService.save(mascota);
            return ResponseEntity.ok(mascotaActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            mascotaService.delete(id);
            return ResponseEntity.noContent().build(); // Respuesta 204 exitosa sin contenido
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}