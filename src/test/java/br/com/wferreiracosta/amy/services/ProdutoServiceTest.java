package br.com.wferreiracosta.amy.services;

import br.com.wferreiracosta.amy.exceptions.ObjectNotFoundException;
import br.com.wferreiracosta.amy.models.Categoria;
import br.com.wferreiracosta.amy.models.Produto;
import br.com.wferreiracosta.amy.repositories.ProdutoRepository;
import br.com.wferreiracosta.amy.services.impl.ProdutoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private CategoriaService categoriaService;

    private ProdutoService produtoService;

    @BeforeEach
    void setUp() {
        produtoService = new ProdutoServiceImpl(produtoRepository, categoriaService);
    }

    @Test
    void testingfindByIdReturnProduto() {
        final var produto = Produto.builder()
                .id(1L)
                .nome("Laptop")
                .descricao("Laptop")
                .preco(new BigDecimal(2000))
                .build();

        when(produtoRepository.findById(produto.getId())).thenReturn(produto);

        final var returnProduto = produtoService.findById(produto.getId());

        assertEquals(produto.getId(), returnProduto.getId());
        assertEquals(produto.getNome(), returnProduto.getNome());
        assertEquals(produto.getDescricao(), returnProduto.getDescricao());
        assertEquals(produto.getPreco(), returnProduto.getPreco());
    }

    @Test
    void testingfindByIdReturnException() {
        final var id = 1L;

        when(produtoRepository.findById(id)).thenReturn(null);

        try {
            produtoService.findById(id);
        } catch (ObjectNotFoundException e) {
            final var message = format("Não foi encontrada Produto com esse id: %s", id);
            assertEquals(message, e.getLocalizedMessage());
        }

    }

    @Test
    void testingFindByIdWithCategoria() {
        final var informatica = Categoria.builder()
                .id(1L)
                .nome("Informatica")
                .build();

        final var eletronicos = Categoria.builder()
                .id(2L)
                .nome("eletronicos")
                .build();

        final var categorias = List.of(informatica, eletronicos);

        final var produto = Produto.builder()
                .id(1L)
                .nome("Laptop")
                .descricao("Laptop")
                .preco(new BigDecimal(2000))
                .build();

        when(produtoRepository.findById(produto.getId())).thenReturn(produto);
        when(categoriaService.findByProdutoId(produto.getId())).thenReturn(categorias);

        final var returnProduto = produtoService.findByIdWithCategoria(produto.getId());

        assertEquals(produto.getId(), returnProduto.getId());
        assertEquals(produto.getNome(), returnProduto.getNome());
        assertEquals(produto.getDescricao(), returnProduto.getDescricao());
        assertEquals(produto.getPreco(), returnProduto.getPreco());
        assertEquals(categorias.size(), returnProduto.getCategorias().size());
        assertEquals(categorias.get(0), returnProduto.getCategorias().get(0));
        assertEquals(categorias.get(1), returnProduto.getCategorias().get(1));
    }

    @Test
    void testingFindProdutoByCategoria() {
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

        when(produtoRepository.findProdutoByCategoriaId(categoriaId)).thenReturn(produtos);

        final var produtosReturnList = produtoService.findProdutoByCategoriaId(categoriaId);

        assertEquals(produtos, produtosReturnList);
    }
}
