package com.veterinaria.PetCare.repository;

import com.veterinaria.PetCare.entity.Dueno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DuenoRepository extends JpaRepository<Dueno, Long> {

    // No necesitas agregar findByDuenoId ni findById.
    // JpaRepository ya te regala todos los métodos básicos (save, findById, findAll, deleteById).

}