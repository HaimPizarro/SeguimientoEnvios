package com.example.seguimientoenvios.seguimientoenvios.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeguimientoEnvios {
    private Long id;
    private String fechaenvio;
    private String fechafinal;
    private String estadoenvio;
    private String observaciones;
    private int peso;
    private int altura;
    private int longitud;
}
