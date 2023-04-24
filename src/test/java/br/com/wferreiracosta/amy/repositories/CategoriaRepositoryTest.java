package br.com.wferreiracosta.amy.repositories;

import br.com.wferreiracosta.amy.exceptions.ObjectNotFoundException;
import br.com.wferreiracosta.amy.models.Categoria;
import br.com.wferreiracosta.amy.repositories.impl.CategoriaRepositoryImpl;
import br.com.wferreiracosta.amy.utils.mappers.CategoriaRowMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoriaRepositoryTest {

    @Mock
    private NamedParameterJdbcTemplate jdbcTemplate;

    private CategoriaRepository categoriaRepository;

    @BeforeEach
    public void setUp() {
        categoriaRepository = new CategoriaRepositoryImpl(jdbcTemplate);
    }

    @Test
    public void testingFindAllReturnAllCategorias() {
        final var informatica = Categoria.builder()
                .id(1L)
                .nome("Informatica")
                .build();

        final var roupa = Categoria.builder()
                .id(2L)
                .nome("Roupa")
                .build();
        final var listCategorias = List.of(informatica, roupa);

        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class)))
                .thenReturn(listCategorias);

        final var returnCategorias = categoriaRepository.findAll();

        assertEquals(listCategorias.size(), returnCategorias.size());
        assertEquals(listCategorias.get(0), returnCategorias.get(0));
        assertEquals(listCategorias.get(1), returnCategorias.get(1));
    }

    @Test
    public void testingFindByIdReturnCategoria(){
        final var informatica = Categoria.builder()
                .id(1L)
                .nome("Informatica")
                .build();

        when(jdbcTemplate.queryForObject(anyString(), any(MapSqlParameterSource.class), any(CategoriaRowMapper.class)))
                .thenReturn(informatica);

        final var returnCategoria = categoriaRepository.findById(informatica.getId());

        assertEquals(informatica.getId(), returnCategoria.getId());
        assertEquals(informatica.getNome(), returnCategoria.getNome());
    }

    @Test
    public void testingFindByIdReturnNull(){
        final var exception = mock(ObjectNotFoundException.class);

        when(jdbcTemplate.queryForObject(anyString(), any(MapSqlParameterSource.class), any(CategoriaRowMapper.class)))
                .thenThrow(exception);

        final var id = 1L;

        final var returnCategoria = categoriaRepository.findById(id);

        assertNull(returnCategoria);
    }

}
