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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "seguimientoenvios")//nombre de la tabla
public class SeguimientoEnvios {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message="El campo id es obligatorio")
    @Positive(message="El campo id debe ser positivo")
    private Long id;

    //TODO: Agregar restriccion de fechas
    @NotBlank(message="El campo id es obligatorio")
    private String fechaenvio;

    //TODO:agregar restriccion de fechas
    @NotBlank(message="El campo id es obligatorio")
    private String fechafinal;

    @NotBlank(message="El estado es obligatiorio")
    private String estadoenvio;

    @NotBlank(message="Las observaciones son obligatorias")
    @Size(min=1, max=255, message=("Debe tener tener entre 1 y 255 caracteres") )
    private String observaciones;
    
    @NotBlank(message="El campo peso es obligatorio")
    @Positive(message="El campo peso debe tener un valor sobre 0")
    @Min(value=1, message="El peso debe tener por lo menos 1 caracter")
    @Max(value = 40, message = "El peso debe tener un maximo de 40 caractres")
    private int peso;

    @NotBlank(message="El campo altura es obligatorio")
    @Positive(message="El campo altura debe tener un valor sobre 0")
    @Min(value=1, message="La altura debe tener por lo menos 1 caracter")
    @Max(value = 40, message = "La altura debe tener un maximo de 40 caractres")
    private int altura;

    @NotBlank(message="El campo longitud es obligatorio")
    @Positive(message="El campo peso longitud tener un valor sobre 0")
    @Min(value=1, message="La longitud debe tener por lo menos 1 caracter")
    @Max(value = 40, message = "La longitud debe tener un maximo de 40 caractres")
    private int longitud;
}
