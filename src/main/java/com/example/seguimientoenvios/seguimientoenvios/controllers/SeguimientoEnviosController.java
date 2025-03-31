package com.example.seguimientoenvios.seguimientoenvios.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.seguimientoenvios.seguimientoenvios.models.SeguimientoEnvios;
import com.example.seguimientoenvios.seguimientoenvios.services.SeguimientoEnviosServices;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/seguimientoenvios")
public class SeguimientoEnviosController {
    
    private final SeguimientoEnviosServices seguimientoEnviosServices;

    public SeguimientoEnviosController(SeguimientoEnviosServices seguimientoEnviosServices) {
        this.seguimientoEnviosServices = seguimientoEnviosServices;
    }
    
    @GetMapping
    public List<SeguimientoEnvios> getAll() {
        return seguimientoEnviosServices.getAll();  
    }
    
    @GetMapping("/{id}")
    public Optional<SeguimientoEnvios> getEnviosById(@PathVariable Long id) {
        return seguimientoEnviosServices.getById(id);
    }

}
