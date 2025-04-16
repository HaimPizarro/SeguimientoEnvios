package com.example.seguimientoenvios.seguimientoenvios.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.seguimientoenvios.seguimientoenvios.models.SeguimientoEnvios;
import com.example.seguimientoenvios.seguimientoenvios.models.ResponseWrapper;
import com.example.seguimientoenvios.seguimientoenvios.services.SeguimientoEnviosServices;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@RestController
@RequestMapping("/seguimientoenvios")
public class SeguimientoEnviosController {
    
    private final SeguimientoEnviosServices seguimientoEnviosServices;

    public SeguimientoEnviosController(SeguimientoEnviosServices seguimientoEnvioServices) {
        this.seguimientoEnviosServices = seguimientoEnvioServices;
    }

    @GetMapping
    public List<SeguimientoEnvios> getSeguimientoEnvios() {
        return seguimientoEnviosServices.getSeguimientoEnvios();
    }
    
    @GetMapping("/{id}")
    public SeguimientoEnvios getSeguimientoEnviosByID(@PathVariable Long id) {
        // Llamamos al servicio: getViajeMascotaById(id)
        return seguimientoEnviosServices.getSeguimientoEnviosByID(id);
    }
    
    @GetMapping("/{id}")
    public ViajeMascota getViajeMascota(@PathVariable Long id) {
        // Llamamos al servicio: getViajeMascotaById(id)
        return viajeMascotaServices.getViajeMascotaById(id);
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<ViajeMascota>> crearViaje(@Valid @RequestBody ViajeMascota viajeMascota) {
        // Usamos una variable distinta
        ViajeMascota nuevoViaje = viajeMascotaServices.crearViaje(viajeMascota);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseWrapper<>("Viaje creado con exito", 1, List.of(nuevoViaje)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<ViajeMascota>> actualizarViaje(
            @PathVariable Long id,
            @Valid @RequestBody ViajeMascota viajeMascota) {
        // Igual, variable distinta
        ViajeMascota viajeActualizado = viajeMascotaServices.actualizarViaje(id, viajeMascota);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseWrapper<>("Viaje actualizado con exito", 1, List.of(viajeActualizado)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<ViajeMascota>> eliminarViaje(@PathVariable Long id) {
        ViajeMascota viajeEliminado = viajeMascotaServices.eliminarViaje(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseWrapper<>("Viaje eliminado con exito", 1, List.of(viajeEliminado)));
    }

}
