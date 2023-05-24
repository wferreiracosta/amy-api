package br.com.wferreiracosta.amy.services;

import br.com.wferreiracosta.amy.exceptions.ObjectNotFoundException;
import br.com.wferreiracosta.amy.exceptions.ObjectNotInsertException;
import br.com.wferreiracosta.amy.models.Categoria;
import br.com.wferreiracosta.amy.models.Produto;
import br.com.wferreiracosta.amy.models.parameters.CategoriaParameter;
import br.com.wferreiracosta.amy.repositories.CategoriaRepository;
import br.com.wferreiracosta.amy.services.impl.CategoriaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static java.lang.String.format;
import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private ProdutoService produtoService;

    private CategoriaService categoriaService;

    @BeforeEach
    public void setUp() {
        categoriaService = new CategoriaServiceImpl(categoriaRepository, produtoService);
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
        final var listCategorias = of(informatica, roupa);

        when(categoriaRepository.findAll()).thenReturn(listCategorias);

        final var returnCategorias = categoriaService.findAll();

        assertEquals(listCategorias.size(), returnCategorias.size());
        assertEquals(listCategorias.get(0), returnCategorias.get(0));
        assertEquals(listCategorias.get(1), returnCategorias.get(1));
    }

    @Test
    public void testingFindByIdReturnCategoria() {
        final var informatica = Categoria.builder()
                .id(1L)
                .nome("Informatica")
                .build();

        when(categoriaRepository.findById(informatica.getId())).thenReturn(informatica);

        final var returnCategoria = categoriaService.findById(informatica.getId());

        assertEquals(informatica.getId(), returnCategoria.getId());
        assertEquals(informatica.getNome(), returnCategoria.getNome());
    }

    @Test
    public void testingFindByIdReturnException() {
        final var id = 1L;
        final var messageException = format("Não foi encontrada Categoria com esse id: %s", id);

        when(categoriaRepository.findById(id)).thenReturn(null);

        try {
            categoriaService.findById(id);
        } catch (ObjectNotFoundException e) {
            assertEquals(messageException, e.getLocalizedMessage());
        }

    }

    @Test
    public void testingFindByProdutoIdReturnCategorias() {
        final var id = 1L;

        final var informatica = Categoria.builder()
                .id(1L)
                .nome("Informatica")
                .build();

        final var roupa = Categoria.builder()
                .id(2L)
                .nome("Roupa")
                .build();
        final var listCategorias = of(informatica, roupa);

        when(categoriaRepository.findByProdutoId(id)).thenReturn(listCategorias);

        final var returnCategorias = categoriaService.findByProdutoId(id);

        assertEquals(listCategorias.size(), returnCategorias.size());
        assertEquals(listCategorias.get(0), returnCategorias.get(0));
        assertEquals(listCategorias.get(1), returnCategorias.get(1));
    }

    @Test
    public void testingFindByProdutoIdReturnEmpty() {
        final var id = 1L;

        try {
            when(categoriaRepository.findByProdutoId(id)).thenReturn(of());
            categoriaService.findByProdutoId(id);
        } catch (ObjectNotFoundException e) {
            final var message = format("Não foi encontrada Categoria com esse id %s de Produto", id);
            assertEquals(message, e.getLocalizedMessage());
        }
    }

    @Test
    public void testingFindCategoriaWithProdutosById() {
        final var categoria = Categoria.builder()
                .id(1L)
                .nome("Informatica")
                .build();

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

        final var produtos = of(laptop, panela);

        when(categoriaRepository.findById(categoria.getId())).thenReturn(categoria);
        when(produtoService.findProdutoByCategoriaId(categoria.getId())).thenReturn(produtos);

        final var categoriaWithProdutos = categoriaService.findCategoriaWithProdutosById(categoria.getId());

        assertEquals(categoria.getId(), categoriaWithProdutos.getId());
        assertEquals(categoria.getNome(), categoriaWithProdutos.getNome());
        assertFalse(categoriaWithProdutos.getProdutos().isEmpty());
        assertTrue(categoriaWithProdutos.getProdutos().contains(laptop));
        assertTrue(categoriaWithProdutos.getProdutos().contains(panela));
    }

    @Test
    public void testingInserNewCategoriaWithSucess(){
        final var categoria = Categoria.builder()
                .id(1L)
                .nome("Informatica")
                .build();

        final var param = CategoriaParameter.builder()
                .nome("Informatica")
                .build();

        when(categoriaRepository.insert(param)).thenReturn(categoria);

        final var newCategoria = categoriaService.insert(param);

        assertEquals(categoria, newCategoria);
    }

    @Test
    public void testingInserNewCategoriaReturnException(){
        final var param = CategoriaParameter.builder()
                .nome("Informatica")
                .build();

        when(categoriaRepository.insert(param)).thenReturn(null);

        try{
            categoriaService.insert(param);
        } catch (ObjectNotInsertException e){
            final var message = format("Erro no momento de cadastrar categoria com o nome %s", param.getNome());
            assertEquals(message, e.getLocalizedMessage());
        }
    }

}
