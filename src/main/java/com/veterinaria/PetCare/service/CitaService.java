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

    // Para POST (Crear)
    public Cita save(Cita cita) {
        validarMascota(cita);
        return citaRepository.save(cita);
    }

    // Para PUT (Actualizar)
    public Cita update(Long id, Cita citaDetalles) {
        // 1. Verificamos que la cita exista
        if (!citaRepository.existsById(id)) {
            throw new RuntimeException("La cita que intentas actualizar no existe.");
        }

        // 2. Validamos la mascota
        validarMascota(citaDetalles);

        // 3. Aseguramos que el ID de la cita sea el correcto antes de guardar
        citaDetalles.setId(id);

        // 4. Guardamos la cita actualizada
        return citaRepository.save(citaDetalles);
    }

    public void delete(Long id) {
        if (!citaRepository.existsById(id)) {
            throw new RuntimeException("La cita que intentas eliminar no existe.");
        }
        citaRepository.deleteById(id);
    }

    // Método auxiliar para no repetir código de validación
    private void validarMascota(Cita cita) {
        if (cita.getMascota() != null && cita.getMascota().getId() != null) {
            if (!mascotaRepository.existsById(cita.getMascota().getId())) {
                throw new RuntimeException("La mascota especificada no existe en los registros.");
            }
        } else {
            throw new RuntimeException("La cita debe estar asociada a una mascota válida.");
        }
    }
}