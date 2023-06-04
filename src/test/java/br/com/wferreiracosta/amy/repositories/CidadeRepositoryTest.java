package br.com.wferreiracosta.amy.repositories;

import br.com.wferreiracosta.amy.exceptions.ObjectNotFoundException;
import br.com.wferreiracosta.amy.models.Cidade;
import br.com.wferreiracosta.amy.models.CidadeEstado;
import br.com.wferreiracosta.amy.models.Estado;
import br.com.wferreiracosta.amy.repositories.impl.CidadeRepositoryImpl;
import br.com.wferreiracosta.amy.utils.mappers.CidadeEstadoRowMapper;
import br.com.wferreiracosta.amy.utils.mappers.CidadeRowMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class CidadeRepositoryTest {

    @Mock
    private NamedParameterJdbcTemplate jdbcTemplate;

    private CidadeRepository cidadeRepository;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        cidadeRepository = new CidadeRepositoryImpl(jdbcTemplate);
    }

    @Test
    public void testingByIdWithSucess() {
        final var estado = Estado.builder()
                .id(1L)
                .nome("São Paulo")
                .uf("SP")
                .build();

        final var cidade = CidadeEstado.builder()
                .id(1L)
                .nome("São Paulo")
                .estado(estado)
                .build();

        when(jdbcTemplate.queryForObject(anyString(), any(MapSqlParameterSource.class), any(CidadeEstadoRowMapper.class)))
                .thenReturn(cidade);

        final var cidadeReturn = cidadeRepository.findById(cidade.getId());

        assertEquals(cidade, cidadeReturn);
    }

    @Test
    public void testingByIdReturnNull() {
        final var exception = mock(ObjectNotFoundException.class);

        when(jdbcTemplate.queryForObject(anyString(), any(MapSqlParameterSource.class), any(CidadeEstadoRowMapper.class)))
                .thenThrow(exception);

        assertNull(cidadeRepository.findById(1L));
    }

    @Test
    public void testingFindByEstadoUfWithSucess() {
        final var saoPaulo = Cidade.builder().id(1L).nome("São Paulo").build();
        final var campinas = Cidade.builder().id(2L).nome("Campinas").build();
        final var cidades = List.of(saoPaulo, campinas);
        final var uf = "SP";

        when(jdbcTemplate.query(anyString(), any(MapSqlParameterSource.class), any(CidadeRowMapper.class)))
                .thenReturn(cidades);

        final var cidadesReturn = cidadeRepository.findByEstadoUf(uf);

        assertFalse(cidadesReturn.isEmpty());
        assertEquals(cidades.size(), cidadesReturn.size());
        assertEquals(saoPaulo, cidadesReturn.get(0));
        assertEquals(campinas, cidadesReturn.get(1));
    }

    @Test
    public void testingFindByEstadoUfReturnNull() {
        final var exception = mock(ObjectNotFoundException.class);
        final var uf = "SP";

        when(jdbcTemplate.query(anyString(), any(MapSqlParameterSource.class), any(CidadeRowMapper.class)))
                .thenThrow(exception);

        final var cidadeListReturn = cidadeRepository.findByEstadoUf(uf);

        assertTrue(cidadeListReturn.isEmpty());
    }

}
