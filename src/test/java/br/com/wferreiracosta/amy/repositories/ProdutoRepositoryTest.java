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
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.math.BigDecimal;

import static br.com.wferreiracosta.amy.utils.queries.ProdutoQuery.FIND_PRODUTO_BY_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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

}
