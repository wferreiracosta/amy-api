package br.com.wferreiracosta.amy.repositories;

import br.com.wferreiracosta.amy.exceptions.ObjectNotFoundException;
import br.com.wferreiracosta.amy.models.Produto;
import br.com.wferreiracosta.amy.repositories.impl.ProdutoRepositoryImpl;
import br.com.wferreiracosta.amy.utils.mappers.ProdutoRowMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.math.BigDecimal;
import java.util.List;

import static br.com.wferreiracosta.amy.utils.queries.ProdutoQuery.FIND_PRODUTOS_BY_CATEGORIA_ID;
import static br.com.wferreiracosta.amy.utils.queries.ProdutoQuery.FIND_PRODUTO_BY_ID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProdutoRepositoryTest {

    @Mock
    private NamedParameterJdbcTemplate jdbcTemplate;

    private ProdutoRepository produtoRepository;

    @BeforeEach
    public void setUp() {
        produtoRepository = new ProdutoRepositoryImpl(jdbcTemplate);
    }

    @Test
    public void testingFindById() {
        final var laptop = Produto.builder()
                .id(1L)
                .nome("laptop")
                .descricao("Laptop")
                .preco(new BigDecimal(2000))
                .build();

        when(jdbcTemplate.queryForObject(eq(FIND_PRODUTO_BY_ID), any(MapSqlParameterSource.class), any(ProdutoRowMapper.class)))
                .thenReturn(laptop);

        final var returnProduto = produtoRepository.findById(laptop.getId());

        assertEquals(laptop.getId(), returnProduto.getId());
        assertEquals(laptop.getNome(), returnProduto.getNome());
        assertEquals(laptop.getDescricao(), returnProduto.getDescricao());
        assertEquals(laptop.getPreco(), returnProduto.getPreco());
    }

    @Test
    public void testingFindByIdReturnException() {
        final var exception = mock(ObjectNotFoundException.class);
        final var id = 1L;

        when(jdbcTemplate.queryForObject(eq(FIND_PRODUTO_BY_ID), any(MapSqlParameterSource.class), any(ProdutoRowMapper.class)))
                .thenThrow(exception);

        assertNull(produtoRepository.findById(id));
    }

    @Test
    public void testingFindProdutoByCategoriaIdReturnSucess() {
        final var laptop = Produto.builder()
                .id(1L)
                .nome("Laptop")
                .descricao("Laptop")
                .preco(new BigDecimal(2000))
                .build();

        final var panela = Produto.builder()
                .id(2L)
                .nome("Panela")
                .descricao("Panela")
                .preco(new BigDecimal(2000))
                .build();

        final var produtos = List.of(laptop, panela);
        final var categoriaId = 1L;

        when(jdbcTemplate.query(eq(FIND_PRODUTOS_BY_CATEGORIA_ID), any(MapSqlParameterSource.class), any(ProdutoRowMapper.class)))
                .thenReturn(produtos);

        final var produtosReturnList = produtoRepository.findProdutoByCategoriaId(categoriaId);

        assertEquals(produtos, produtosReturnList);
    }

    @Test
    public void testingFindProdutosByCategoriaIdReturnEmptyList() {
        final var expection = mock(DataAccessException.class);
        final var categoriaId = 1L;

        when(jdbcTemplate.query(eq(FIND_PRODUTOS_BY_CATEGORIA_ID), any(MapSqlParameterSource.class), any(ProdutoRowMapper.class)))
                .thenThrow(expection);

        final var produtosReturnList = produtoRepository.findProdutoByCategoriaId(categoriaId);

        assertTrue(produtosReturnList.isEmpty());
    }
}
