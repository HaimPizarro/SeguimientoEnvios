package com.example.seguimientoenvios.seguimientoenvios.services;

import org.springframework.stereotype.Service;

import com.example.seguimientoenvios.seguimientoenvios.models.SeguimientoEnvios;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
public class SeguimientoEnviosServices {
    private List<SeguimientoEnvios> seguimientoEnvios = new ArrayList<>();

    public SeguimientoEnviosServices() {
        seguimientoEnvios.add(new SeguimientoEnvios(1L, "2021-01-01", "2021-01-02", "Pendiente", "Prueba", 10, 10, 10));
        seguimientoEnvios.add(new SeguimientoEnvios(2L, "2021-01-02", "2021-01-03", "Pendiente", "Prueba", 10, 10, 10));
        seguimientoEnvios.add(new SeguimientoEnvios(3L, "2021-01-03", "2021-01-04", "Pendiente", "Prueba", 10, 10, 10));
        seguimientoEnvios.add(new SeguimientoEnvios(4L, "2021-01-04", "2021-01-05", "Pendiente", "Prueba", 10, 10, 10));
        seguimientoEnvios.add(new SeguimientoEnvios(5L, "2021-01-05", "2021-01-06", "Pendiente", "Prueba", 10, 10, 10));
    }

    public List<SeguimientoEnvios> getAll() {
        return seguimientoEnvios;
    }

    public Optional<SeguimientoEnvios> getById(Long id) {
        return seguimientoEnvios.stream().filter(s -> s.getId().equals(id)).findFirst();
    }
}
