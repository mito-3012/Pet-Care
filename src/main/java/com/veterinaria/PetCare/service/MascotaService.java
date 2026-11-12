package com.veterinaria.PetCare.service;

import com.veterinaria.PetCare.entity.Mascota;
import com.veterinaria.PetCare.repository.DuenoRepository;
import com.veterinaria.PetCare.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaService {
    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private DuenoRepository duenoRepository;

    public List<Mascota> getAll() { return mascotaRepository.findAll(); }
    public Optional<Mascota> getById(Long id) { return mascotaRepository.findById(id); }
    public List<Mascota> getByDuenoId(Long duenoId) { return mascotaRepository.findByDuenoId(duenoId); }

    public Mascota save(Mascota mascota) {
        if (mascota.getDueno() != null && mascota.getDueno().getId() != null) {
            if (!duenoRepository.existsById(mascota.getDueno().getId())) {
                throw new RuntimeException("El dueño especificado no existe");
            }
        }
        return mascotaRepository.save(mascota);
    }

    public void delete(Long id) { mascotaRepository.deleteById(id); }
}