package com.veterinaria.PetCare.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha;
    private String motivo;
    private String diagnostico;
    private Double costo;

    @ManyToOne
    @JoinColumn(name = "mascota_id")
    private Mascota mascota;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }
    public Double getCosto() { return costo; }
    public void setCosto(Double costo) { this.costo = costo; }
    public Mascota getMascota() { return mascota; }
    public void setMascota(Mascota mascota) { this.mascota = mascota; }
}
