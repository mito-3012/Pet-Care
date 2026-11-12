package com.veterinaria.PetCare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Dueno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rut;
    private String nombre;
    private String telefono;
    private String email;

    @OneToMany(mappedBy = "dueno", cascade = CascadeType.ALL)
    @JsonIgnore // Evita recursividad infinita al serializar a JSON
    private List<Mascota> mascotas;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRut() { return rut; }
    public void setRut(String rut) { this.rut = rut; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public List<Mascota> getMascotas() { return mascotas; }
    public void setMascotas(List<Mascota> mascotas) { this.mascotas = mascotas; }
}
