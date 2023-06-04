package br.com.wferreiracosta.amy.controllers;

import br.com.wferreiracosta.amy.controllers.impl.CategoriaControllerImpl;
import br.com.wferreiracosta.amy.models.Categoria;
import br.com.wferreiracosta.amy.models.CategoriaProdutos;
import br.com.wferreiracosta.amy.services.CategoriaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoriaControllerTest {

    @Mock
    private CategoriaService categoriaService;

    private CategoriaController categoriaController;

    @BeforeEach
    void setUp() {
        categoriaController = new CategoriaControllerImpl(categoriaService);
    }

    @Test
    void testingFindAllReturnAllCategorias() {
        final var informatica = Categoria.builder()
                .id(1L)
                .nome("Informatica")
                .build();

        final var roupa = Categoria.builder()
                .id(2L)
                .nome("Roupa")
                .build();
        final var listCategorias = List.of(informatica, roupa);

        when(categoriaService.findAll()).thenReturn(listCategorias);

        final var returnCategorias = categoriaController.findAll();

        Assertions.assertEquals(listCategorias.size(), returnCategorias.size());
        Assertions.assertEquals(listCategorias.get(0), returnCategorias.get(0));
        Assertions.assertEquals(listCategorias.get(1), returnCategorias.get(1));
    }

    @Test
    void testingFindByIdReturnCategoria() {
        final var informatica = CategoriaProdutos.builder()
                .id(1L)
                .nome("Informatica")
                .build();

        when(categoriaService.findCategoriaWithProdutosById(informatica.getId())).thenReturn(informatica);

        final var returnCategoria = categoriaController.findById(informatica.getId());

        assertEquals(informatica.getId(), returnCategoria.getId());
        assertEquals(informatica.getNome(), returnCategoria.getNome());
    }

}
