package com.example.seguimientoenvios.seguimientoenvios;

import com.example.seguimientoenvios.seguimientoenvios.models.SeguimientoEnvios;
import com.example.seguimientoenvios.seguimientoenvios.controllers.SeguimientoEnviosController;
import com.example.seguimientoenvios.seguimientoenvios.services.SeguimientoEnviosServices;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SeguimientoEnviosController.class)
class SeguimientoEnviosControllerTest {

    @Autowired
    private MockMvc mvc;

    @org.springframework.test.context.bean.override.mockito.MockitoBean
    private SeguimientoEnviosServices service;

    @Test
    void getById_ok() throws Exception {
        SeguimientoEnvios demo = new SeguimientoEnvios(
                1L,
                LocalDateTime.parse("2025-05-04T10:00:00"),
                LocalDateTime.parse("2025-05-06T10:00:00"),
                "EN RUTA",
                "Paquete frágil",
                8, 15, 20);

        when(service.getSeguimientoEnviosById(1L)).thenReturn(demo);

        mvc.perform(get("/seguimientoenvios/1").accept(MediaTypes.HAL_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.estadoenvio").value("EN RUTA"))
           .andExpect(jsonPath("$.peso").value(8));
    }
}
