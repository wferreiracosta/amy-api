package br.com.wferreiracosta.amy.repositories;

import br.com.wferreiracosta.amy.models.Categoria;
import br.com.wferreiracosta.amy.repositories.impl.CategoriaRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoriaRepositoryTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

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

        Assertions.assertEquals(listCategorias.size(), returnCategorias.size());
        Assertions.assertEquals(listCategorias.get(0), returnCategorias.get(0));
        Assertions.assertEquals(listCategorias.get(1), returnCategorias.get(1));
    }

}
