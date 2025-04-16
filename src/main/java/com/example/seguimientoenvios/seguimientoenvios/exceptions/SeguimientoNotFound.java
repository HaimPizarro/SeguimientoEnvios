package com.example.seguimientoenvios.seguimientoenvios.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SeguimientoNotFound extends RuntimeException {
    public SeguimientoNotFound(Long id) {
        super("No se ha encontrado el envío con id: " + id);
    }
}