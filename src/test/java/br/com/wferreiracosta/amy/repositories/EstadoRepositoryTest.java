package br.com.wferreiracosta.amy.repositories;

import br.com.wferreiracosta.amy.exceptions.ObjectNotFoundException;
import br.com.wferreiracosta.amy.models.Estado;
import br.com.wferreiracosta.amy.repositories.impl.EstadoRepositoryImpl;
import br.com.wferreiracosta.amy.utils.mappers.EstadoRowMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class EstadoRepositoryTest {

    @Mock
    private NamedParameterJdbcTemplate jdbcTemplate;

    private EstadoRepository estadoRepository;

    @BeforeEach
    void setUp() {
        openMocks(this);
        estadoRepository = new EstadoRepositoryImpl(jdbcTemplate);
    }

    @Test
    void testingFindByIdWithSucess() {
        final var estado = Estado.builder()
                .id(1L)
                .nome("São Paulo")
                .uf("SP")
                .build();

        when(jdbcTemplate.queryForObject(anyString(), any(MapSqlParameterSource.class), any(EstadoRowMapper.class)))
                .thenReturn(estado);

        final var estadoReturn = estadoRepository.findById(estado.getId());

        assertEquals(estado, estadoReturn);
    }

    @Test
    void testingFindByIdReturnNull() {
        final var exception = mock(ObjectNotFoundException.class);

        when(jdbcTemplate.queryForObject(anyString(), any(MapSqlParameterSource.class), any(EstadoRowMapper.class)))
                .thenThrow(exception);

        assertNull(estadoRepository.findById(1L));
    }

}
