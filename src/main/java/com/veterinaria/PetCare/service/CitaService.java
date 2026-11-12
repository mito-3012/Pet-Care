package com.veterinaria.PetCare.service;




import com.veterinaria.PetCare.entity.Cita;
import com.veterinaria.PetCare.repository.CitaRepository;
import com.veterinaria.PetCare.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    public List<Cita> getAll() {
        return citaRepository.findAll();
    }

    public Optional<Cita> getById(Long id) {
        return citaRepository.findById(id);
    }

    public List<Cita> getByMascotaId(Long mascotaId) {
        return citaRepository.findByMascotaId(mascotaId);
    }

    public Cita save(Cita cita) {
        // Validación: La mascota debe existir antes de crear o actualizar una cita
        if (cita.getMascota() != null && cita.getMascota().getId() != null) {
            if (!mascotaRepository.existsById(cita.getMascota().getId())) {
                throw new RuntimeException("La mascota especificada no existe en los registros.");
            }
        } else {
            throw new RuntimeException("La cita debe estar asociada a una mascota válida.");
        }
        return citaRepository.save(cita);
    }

    public void delete(Long id) {
        citaRepository.deleteById(id);
    }
}