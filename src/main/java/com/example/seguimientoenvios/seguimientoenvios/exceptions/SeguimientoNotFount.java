package com.example.seguimientoenvios.seguimientoenvios.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SeguimientoNotFount extends RuntimeException{
    public SeguimientoNotFount(Long id) {
        super("No se ha encontrado el envio con id: "+ id);
    }
}