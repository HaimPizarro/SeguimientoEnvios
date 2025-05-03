package com.example.seguimientoenvios.seguimientoenvios.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.seguimientoenvios.seguimientoenvios.controllers.SeguimientoEnviosController;
import com.example.seguimientoenvios.seguimientoenvios.models.SeguimientoEnvios;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class SeguimientoEnviosModelAssembler
        implements RepresentationModelAssembler<SeguimientoEnvios, EntityModel<SeguimientoEnvios>> {

    @Override
    public @NonNull EntityModel<SeguimientoEnvios> toModel(@NonNull SeguimientoEnvios s) {
        return EntityModel.of(
                s,
                linkTo(methodOn(SeguimientoEnviosController.class).getSeguimientoEnviosById(s.getId())).withSelfRel(),
                linkTo(methodOn(SeguimientoEnviosController.class).actualizarEnvio(s.getId(), s)).withRel("update"),
                linkTo(methodOn(SeguimientoEnviosController.class).eliminarEnvio(s.getId())).withRel("delete"),
                linkTo(methodOn(SeguimientoEnviosController.class).getSeguimientoEnvios()).withRel("collection")
        );
    }
}
