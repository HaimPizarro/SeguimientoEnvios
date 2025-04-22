package com.example.seguimientoenvios.seguimientoenvios.services;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.seguimientoenvios.seguimientoenvios.models.SeguimientoEnvios;
import com.example.seguimientoenvios.seguimientoenvios.repository.SeguimientoRepository;
import com.example.seguimientoenvios.seguimientoenvios.exceptions.SeguimientoNotFound;

@Service
public class SeguimientoEnviosServices {

    private final SeguimientoRepository seguimientoRepository;

    public SeguimientoEnviosServices(SeguimientoRepository seguimientoRepository) {
        this.seguimientoRepository = seguimientoRepository;
    }

    public List<SeguimientoEnvios> getSeguimientoEnvios() {
        return seguimientoRepository.findAll();
    }

    public SeguimientoEnvios getSeguimientoEnviosById(Long id) {
        return seguimientoRepository.findById(id)
                .orElseThrow(() -> new SeguimientoNotFound(id));
    }

    public SeguimientoEnvios crearEnvio(SeguimientoEnvios seguimientoEnvios) {
        if (seguimientoEnvios.getId() != null && seguimientoRepository.existsById(seguimientoEnvios.getId())) {
            throw new IllegalArgumentException(
                "El envío con id: " + seguimientoEnvios.getId() + " ya existe");
        }
        return seguimientoRepository.save(seguimientoEnvios);
    }

    public SeguimientoEnvios actualizarEnvio(Long id, SeguimientoEnvios seguimientoEnvios) {
        SeguimientoEnvios seguimientoActual = seguimientoRepository.findById(id)
                .orElseThrow(() -> new SeguimientoNotFound(id));

        seguimientoActual.setFechaenvio(seguimientoEnvios.getFechaenvio());
        seguimientoActual.setFechafinal(seguimientoEnvios.getFechafinal());
        seguimientoActual.setEstadoenvio(seguimientoEnvios.getEstadoenvio());
        seguimientoActual.setObservaciones(seguimientoEnvios.getObservaciones());
        seguimientoActual.setPeso(seguimientoEnvios.getPeso());
        seguimientoActual.setAltura(seguimientoEnvios.getAltura());
        seguimientoActual.setLongitud(seguimientoEnvios.getLongitud());

        return seguimientoRepository.save(seguimientoActual);
    }

    public SeguimientoEnvios eliminarEnvio(Long id) {
        SeguimientoEnvios seguimientoEnvios = seguimientoRepository.findById(id)
                .orElseThrow(() -> new SeguimientoNotFound(id));
        seguimientoRepository.delete(seguimientoEnvios);
        return seguimientoEnvios;
    }
}