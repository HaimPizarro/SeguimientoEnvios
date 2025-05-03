package com.example.seguimientoenvios.seguimientoenvios;

import com.example.seguimientoenvios.seguimientoenvios.models.SeguimientoEnvios;
import com.example.seguimientoenvios.seguimientoenvios.services.SeguimientoEnviosServices;
import com.example.seguimientoenvios.seguimientoenvios.repository.SeguimientoRepository;
import com.example.seguimientoenvios.seguimientoenvios.exceptions.SeguimientoNotFound;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*; 
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SeguimientoEnviosServicesTest {

    @Mock
    private SeguimientoRepository repo;

    @InjectMocks
    private SeguimientoEnviosServices service;

    private SeguimientoEnvios demo() {
        return new SeguimientoEnvios(1L,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(5),
                "ENTREGADO", "ok", 5, 15, 25);
    }

    @Test
    void getSeguimientoEnvios_returnsList() {
        when(repo.findAll()).thenReturn(List.of(demo()));

        List<SeguimientoEnvios> res = service.getSeguimientoEnvios();

        assertThat(res).hasSize(1);
        assertThat(res.getFirst().getEstadoenvio()).isEqualTo("ENTREGADO");
        verify(repo).findAll();
    }

    @Test
    void getById_exists_ok() {
        when(repo.findById(1L)).thenReturn(Optional.of(demo()));
        assertThat(service.getSeguimientoEnviosById(1L).getObservaciones()).isEqualTo("ok");
    }

    @Test
    void getById_notFound_throws() {
        when(repo.findById(99L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.getSeguimientoEnviosById(99L))
                .isInstanceOf(SeguimientoNotFound.class);
    }

    @Test
    void crearEnvio_idDuplicado_throws() {
        SeguimientoEnvios s = demo();
        when(repo.existsById(1L)).thenReturn(true);
        assertThatThrownBy(() -> service.crearEnvio(s))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

