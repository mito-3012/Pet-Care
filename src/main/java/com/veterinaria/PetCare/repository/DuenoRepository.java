package com.veterinaria.PetCare.repository;

import com.veterinaria.PetCare.entity.Dueno;
import com.veterinaria.PetCare.entity.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DuenoRepository extends JpaRepository<Dueno, Long> {
    List<Dueno> findByDuenoId(Long duenoId);
}