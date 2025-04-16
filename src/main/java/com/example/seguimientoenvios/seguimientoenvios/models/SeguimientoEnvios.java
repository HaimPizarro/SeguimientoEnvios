package com.example.seguimientoenvios.seguimientoenvios.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "seguimientoenvios") // Nombre real de la tabla en DB
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeguimientoEnvios {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message="El id no puede ser nulo")
    @Positive(message="El id debe ser positivo")
    private Long id;

    // Suponiendo que de momento lo dejaremos como String:
    @NotBlank(message="La fecha de envío es obligatoria")
    private String fechaenvio;

    @NotBlank(message="La fecha final es obligatoria")
    private String fechafinal;

    @NotBlank(message="El estado del envío es obligatorio")
    private String estadoenvio;

    @NotBlank(message="Las observaciones son obligatorias")
    @Size(min=1, max=255, message="Debe tener entre 1 y 255 caracteres")
    private String observaciones;

    // INT: no usar @NotBlank en campos numéricos
    @NotNull(message="El campo peso es obligatorio")
    @Positive(message="El peso debe ser mayor a 0")
    @Max(value = 40, message = "El peso no puede superar 40 (unidades)")
    private int peso;

    @NotNull(message="El campo altura es obligatorio")
    @Positive(message="La altura debe ser mayor a 0")
    @Max(value = 40, message = "La altura no puede superar 40 (unidades)")
    private int altura;

    @NotNull(message="El campo longitud es obligatorio")
    @Positive(message="La longitud debe ser mayor a 0")
    @Max(value = 40, message = "La longitud no puede superar 40 (unidades)")
    private int longitud;
}