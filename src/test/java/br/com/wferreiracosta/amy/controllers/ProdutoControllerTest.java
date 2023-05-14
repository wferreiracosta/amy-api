package br.com.wferreiracosta.amy.controllers;

import br.com.wferreiracosta.amy.controllers.impl.ProdutoControllerImpl;
import br.com.wferreiracosta.amy.models.Categoria;
import br.com.wferreiracosta.amy.models.ProdutoCategoria;
import br.com.wferreiracosta.amy.services.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProdutoControllerTest {

    @Mock
    private ProdutoService produtoService;

    private ProdutoController controller;

    @BeforeEach
    public void setUp() {
        controller = new ProdutoControllerImpl(produtoService);
    }

    @Test
    public void testingFindById() {
        final var informatica = Categoria.builder()
                .id(1L)
                .nome("Informatica")
                .build();

        final var eletronicos = Categoria.builder()
                .id(2L)
                .nome("eletronicos")
                .build();

        final var categorias = List.of(informatica, eletronicos);

        final var produto = ProdutoCategoria.builder()
                .id(1L)
                .nome("Laptop")
                .descricao("Laptop")
                .preco(new BigDecimal(2000))
                .categorias(categorias)
                .build();

        when(produtoService.findByIdWithCategoria(produto.getId())).thenReturn(produto);

        final var returnProduto = controller.findByIdWithCategoria(produto.getId());

        assertEquals(produto.getId(), returnProduto.getId());
        assertEquals(produto.getNome(), returnProduto.getNome());
        assertEquals(produto.getDescricao(), returnProduto.getDescricao());
        assertEquals(produto.getPreco(), returnProduto.getPreco());
        assertEquals(categorias.size(), returnProduto.getCategorias().size());
        assertEquals(categorias.get(0), returnProduto.getCategorias().get(0));
        assertEquals(categorias.get(1), returnProduto.getCategorias().get(1));
    }

}
