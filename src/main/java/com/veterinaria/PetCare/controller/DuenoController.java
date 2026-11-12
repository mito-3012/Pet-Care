package com.veterinaria.PetCare.controller;

import com.veterinaria.PetCare.entity.Dueno;
import com.veterinaria.PetCare.entity.Mascota;
import com.veterinaria.PetCare.service.DuenoService;
import com.veterinaria.PetCare.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/duenos")
@CrossOrigin("*") // Para permitir peticiones desde React opcional
public class DuenoController {

    @Autowired
    private DuenoService duenoService;

    @Autowired
    private MascotaService mascotaService;

    @GetMapping
    public List<Dueno> getAll() { return duenoService.getAll(); }

    @PostMapping
    public Dueno create(@RequestBody Dueno dueno) { return duenoService.save(dueno); }

    // Endpoint de relación solicitado
    @GetMapping("/{id}/mascotas")
    public List<Mascota> getMascotasByDueno(@PathVariable Long id) {
        return mascotaService.getByDuenoId(id);
    }
}

