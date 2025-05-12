package com.example.seguimientoenvios.seguimientoenvios;

import com.example.seguimientoenvios.seguimientoenvios.controllers.SeguimientoEnviosController;
import com.example.seguimientoenvios.seguimientoenvios.exceptions.SeguimientoNotFound;
import com.example.seguimientoenvios.seguimientoenvios.models.SeguimientoEnvios;
import com.example.seguimientoenvios.seguimientoenvios.services.SeguimientoEnviosServices;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SeguimientoEnviosController.class)
class SeguimientoEnviosControllerTest {

    @Autowired
    private MockMvc mvc;

    @SuppressWarnings("removal")          
    @MockBean
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

    @Test
    void getById_notFound_returns404() throws Exception {
        when(service.getSeguimientoEnviosById(99L))
                .thenThrow(new SeguimientoNotFound(99L));

        mvc.perform(get("/seguimientoenvios/99").accept(MediaTypes.HAL_JSON))
           .andExpect(status().isNotFound())
           .andExpect(jsonPath("$.message")
                      .value("No se ha encontrado el envío con id: 99"));
    }

    @Test
    void crearEnvio_ok() throws Exception {
        SeguimientoEnvios guardado = new SeguimientoEnvios(
                10L,
                LocalDateTime.parse("2025-06-01T09:00:00"),
                LocalDateTime.parse("2025-06-03T09:00:00"),
                "CREADO",
                "Sin novedades",
                5, 10, 15);

        when(service.crearEnvio(any(SeguimientoEnvios.class)))
                .thenReturn(guardado);

        String body = """
            {
              "fechaenvio" : "2025-06-01T09:00:00",
              "fechafinal" : "2025-06-03T09:00:00",
              "estadoenvio": "CREADO",
              "observaciones": "Sin novedades",
              "peso": 5,
              "altura": 10,
              "longitud": 15
            }""";

        mvc.perform(post("/seguimientoenvios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
           .andExpect(status().isCreated())
           .andExpect(jsonPath("$.status")
                      .value("Envío creado con éxito"))
           .andExpect(jsonPath("$.data[0].id").value(10))
           .andExpect(jsonPath("$.data[0].estadoenvio").value("CREADO"));
    }
}