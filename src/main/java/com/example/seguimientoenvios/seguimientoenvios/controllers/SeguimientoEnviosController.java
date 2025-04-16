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

    public SeguimientoEnviosController(SeguimientoEnviosServices seguimientoEnviosServices) {
        this.seguimientoEnviosServices = seguimientoEnviosServices;
    }

    @GetMapping
    public List<SeguimientoEnvios> getSeguimientoEnvios() {
        return seguimientoEnviosServices.getSeguimientoEnvios();
    }
    
    @GetMapping("/{id}")
    public SeguimientoEnvios getSeguimientoEnviosById(@PathVariable Long id) {
        return seguimientoEnviosServices.getSeguimientoEnviosById(id);
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<SeguimientoEnvios>> crearEnvio(
            @Valid @RequestBody SeguimientoEnvios seguimientoEnvios) {

        SeguimientoEnvios nuevoEnvio = seguimientoEnviosServices.crearEnvio(seguimientoEnvios);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseWrapper<>("Envío creado con éxito", 1, List.of(nuevoEnvio)));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<SeguimientoEnvios>> actualizarEnvio(
            @PathVariable Long id,
            @Valid @RequestBody SeguimientoEnvios seguimientoEnvios) {

        SeguimientoEnvios seguimientoActualizado = seguimientoEnviosServices.actualizarEnvio(id, seguimientoEnvios);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseWrapper<>("Envío actualizado con éxito", 1, List.of(seguimientoActualizado)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<SeguimientoEnvios>> eliminarEnvio(@PathVariable Long id) {
        SeguimientoEnvios seguimientoEliminado = seguimientoEnviosServices.eliminarEnvio(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseWrapper<>("Envio eliminado con exito", 1, List.of(seguimientoEliminado)));
    }
}