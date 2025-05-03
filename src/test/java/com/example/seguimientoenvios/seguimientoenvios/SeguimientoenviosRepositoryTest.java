package com.example.seguimientoenvios.seguimientoenvios;

import com.example.seguimientoenvios.seguimientoenvios.models.SeguimientoEnvios;
import com.example.seguimientoenvios.seguimientoenvios.repository.SeguimientoRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SeguimientoenviosRepositoryTest {

    @Autowired
    private SeguimientoRepository repo;

    @Test
    void saveAndFindById_ok() {
        SeguimientoEnvios s = new SeguimientoEnvios(
                null,
                LocalDateTime.now(),                // fecha envío
                LocalDateTime.now().plusDays(2),    // fecha final
                "EN RUTA",
                "Sin novedades",
                10, 20, 30);                        // peso, altura, longitud
        s = repo.save(s);

        assertThat(s.getId()).isNotNull();
        assertThat(repo.findById(s.getId()))
                .isPresent()
                .get()
                .extracting(SeguimientoEnvios::getEstadoenvio)
                .isEqualTo("EN RUTA");
    }
}
