package br.com.wferreiracosta.amy.repositories;

import br.com.wferreiracosta.amy.exceptions.ObjectNotFoundException;
import br.com.wferreiracosta.amy.models.Categoria;
import br.com.wferreiracosta.amy.models.parameters.CategoriaParameter;
import br.com.wferreiracosta.amy.repositories.impl.CategoriaRepositoryImpl;
import br.com.wferreiracosta.amy.utils.mappers.CategoriaRowMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.util.List;

import static br.com.wferreiracosta.amy.utils.queries.CategoriaQuery.FIND_CATEGORIA_BY_PRODUTO_ID;
import static br.com.wferreiracosta.amy.utils.queries.CategoriaQuery.INSERT_CATEGORIA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoriaRepositoryTest {

    @Mock
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Mock
    private GeneratedKeyHolder keyHolder;

    private CategoriaRepository categoriaRepository;

    @BeforeEach
    void setUp() {
        categoriaRepository = new CategoriaRepositoryImpl(jdbcTemplate, keyHolder);
    }

    @Test
    void testingFindAllReturnAllCategorias() {
        final var informatica = Categoria.builder().id(1L).nome("Informatica").build();

        final var roupa = Categoria.builder().id(2L).nome("Roupa").build();
        final var listCategorias = List.of(informatica, roupa);

        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(listCategorias);

        final var returnCategorias = categoriaRepository.findAll();

        assertEquals(listCategorias.size(), returnCategorias.size());
        assertEquals(listCategorias.get(0), returnCategorias.get(0));
        assertEquals(listCategorias.get(1), returnCategorias.get(1));
    }

    @Test
    void testingFindByIdReturnCategoria() {
        final var informatica = Categoria.builder().id(1L).nome("Informatica").build();

        when(jdbcTemplate.queryForObject(anyString(), any(MapSqlParameterSource.class), any(CategoriaRowMapper.class))).thenReturn(informatica);

        final var returnCategoria = categoriaRepository.findById(informatica.getId());

        assertEquals(informatica.getId(), returnCategoria.getId());
        assertEquals(informatica.getNome(), returnCategoria.getNome());
    }

    @Test
    void testingFindByIdReturnNull() {
        final var exception = mock(ObjectNotFoundException.class);

        when(jdbcTemplate.queryForObject(anyString(), any(MapSqlParameterSource.class), any(CategoriaRowMapper.class))).thenThrow(exception);

        final var id = 1L;

        final var returnCategoria = categoriaRepository.findById(id);

        assertNull(returnCategoria);
    }

    @Test
    void testingFindByProdutoIdReturnCategorias() {
        final var id = 1L;

        final var informatica = Categoria.builder().id(1L).nome("Informatica").build();

        final var roupa = Categoria.builder().id(2L).nome("Roupa").build();
        final var listCategorias = List.of(informatica, roupa);

        when(jdbcTemplate.query(eq(FIND_CATEGORIA_BY_PRODUTO_ID), any(MapSqlParameterSource.class), any(CategoriaRowMapper.class))).thenReturn(listCategorias);

        final var returnCategorias = categoriaRepository.findByProdutoId(id);

        assertEquals(listCategorias.size(), returnCategorias.size());
        assertEquals(listCategorias.get(0), returnCategorias.get(0));
        assertEquals(listCategorias.get(1), returnCategorias.get(1));
    }

    @Test
    void testingFindByProdutoIdReturnEmpty() {
        final var id = 1L;

        final var exception = mock(ObjectNotFoundException.class);

        when(jdbcTemplate.query(eq(FIND_CATEGORIA_BY_PRODUTO_ID), any(MapSqlParameterSource.class), any(CategoriaRowMapper.class))).thenThrow(exception);

        final var returnCategorias = categoriaRepository.findByProdutoId(id);
        Assertions.assertTrue(returnCategorias.isEmpty());
    }

    @Test
    void testingInserNewCategoriaWithSucess() {
        final var categoria = Categoria.builder().id(1L).nome("Informatica").build();

        final var param = CategoriaParameter.builder().nome("Informatica").build();

        when(jdbcTemplate.update(eq(INSERT_CATEGORIA), any(MapSqlParameterSource.class), any(GeneratedKeyHolder.class), eq(new String[]{"id"}))).thenReturn(1);

        when(keyHolder.getKey()).thenReturn(categoria.getId());
        when(keyHolder.getKey().longValue()).thenReturn(categoria.getId());

        final var categoriaIdInsert = categoriaRepository.insert(param);

        assertEquals(categoria.getId(), categoriaIdInsert);
    }

    @Test
    void testingInserNewCategoriaReturnKeyNull() {
        final var categoria = Categoria.builder().id(1L).nome("Informatica").build();

        final var param = CategoriaParameter.builder().nome("Informatica").build();

        when(keyHolder.getKey()).thenReturn(null);

        when(jdbcTemplate.update(eq(INSERT_CATEGORIA),
                any(MapSqlParameterSource.class),
                any(GeneratedKeyHolder.class),
                eq(new String[]{"id"}))).thenReturn(1);


        try{
            categoriaRepository.insert(param);
        }catch (final IllegalArgumentException e){
            final var message = "Erro no momento de obter id quando foi inserir uma nova categoria no banco de dados";
            assertEquals(message, e.getLocalizedMessage());
        }
    }

    @Test
    void testingInserNewCategoriaReturnException() {
        final var exception = mock(DataAccessException.class);
        final var param = CategoriaParameter.builder().nome("Informatica").build();

        when(jdbcTemplate.update(eq(INSERT_CATEGORIA), any(MapSqlParameterSource.class), any(GeneratedKeyHolder.class), eq(new String[]{"id"}))).thenThrow(exception);

        assertNull(categoriaRepository.insert(param));
    }

}
