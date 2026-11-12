package com.veterinaria.PetCare.service;

import com.veterinaria.PetCare.entity.Dueno;
import com.veterinaria.PetCare.repository.DuenoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DuenoService {

    @Autowired
    private DuenoRepository duenoRepository;

    public List<Dueno> getAll() {
        return duenoRepository.findAll();
    }

    public Optional<Dueno> getById(Long id) {
        return duenoRepository.findById(id);
    }

    public Dueno save(Dueno dueno) {
        return duenoRepository.save(dueno);
    }

    public void delete(Long id) {
        duenoRepository.deleteById(id);
    }
}
