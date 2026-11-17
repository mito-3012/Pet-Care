package com.veterinaria.PetCare.controller;

import com.veterinaria.PetCare.entity.Dueno;
import com.veterinaria.PetCare.entity.Mascota;
import com.veterinaria.PetCare.service.DuenoService;
import com.veterinaria.PetCare.service.MascotaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/duenos")
@CrossOrigin(origins = "http://localhost:5173") // Ajustado de "*" al puerto seguro de React
public class DuenoController {

    private final DuenoService duenoService;
    private final MascotaService mascotaService;

    // Inyección de dependencias por constructor (Mejor práctica que @Autowired)
    public DuenoController(DuenoService duenoService, MascotaService mascotaService) {
        this.duenoService = duenoService;
        this.mascotaService = mascotaService;
    }

    @GetMapping
    public ResponseEntity<List<Dueno>> getAll() {
        List<Dueno> duenos = duenoService.getAll();
        return ResponseEntity.ok(duenos);
    }

    @PostMapping
    public ResponseEntity<Dueno> create(@RequestBody Dueno dueno) {
        Dueno nuevoDueno = duenoService.save(dueno);
        return new ResponseEntity<>(nuevoDueno, HttpStatus.CREATED); // Código 201 Created
    }

    // Endpoint de relación solicitado
    @GetMapping("/{id}/mascotas")
    public ResponseEntity<List<Mascota>> getMascotasByDueno(@PathVariable Long id) {
        List<Mascota> mascotas = mascotaService.getByDuenoId(id);
        if (mascotas.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna 204 si el dueño no tiene mascotas
        }
        return ResponseEntity.ok(mascotas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dueno> update(@PathVariable Long id, @RequestBody Dueno dueno) {
        try {
            dueno.setId(id);
            Dueno duenoActualizado = duenoService.save(dueno);
            return ResponseEntity.ok(duenoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            duenoService.delete(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content tras borrar exitosamente
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // Retorna 404 si el ID no existe
        }
    }
}