package com.veterinaria.PetCare.controller;

import com.veterinaria.PetCare.entity.Cita;
import com.veterinaria.PetCare.entity.Mascota;
import com.veterinaria.PetCare.service.CitaService;
import com.veterinaria.PetCare.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
@CrossOrigin("*")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private CitaService citaService;

    @GetMapping
    public List<Mascota> getAll() { return mascotaService.getAll(); }

    @PostMapping
    public Mascota create(@RequestBody Mascota mascota) { return mascotaService.save(mascota); }

    // Endpoint de relación solicitado
    @GetMapping("/{id}/citas")
    public List<Cita> getCitasByMascota(@PathVariable Long id) {
        return citaService.getByMascotaId(id);
    }
}