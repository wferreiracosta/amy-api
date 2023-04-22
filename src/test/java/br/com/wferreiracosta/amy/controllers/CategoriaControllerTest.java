package br.com.wferreiracosta.amy.controllers;

import br.com.wferreiracosta.amy.controllers.impl.CategoriaControllerImpl;
import br.com.wferreiracosta.amy.models.Categoria;
import br.com.wferreiracosta.amy.services.CategoriaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoriaControllerTest {

    @Mock
    private CategoriaService categoriaService;

    private CategoriaController categoriaController;

    @BeforeEach
    public void setUp(){
        categoriaController = new CategoriaControllerImpl(categoriaService);
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

        when(categoriaService.findAll()).thenReturn(listCategorias);

        final var returnCategorias = categoriaController.findAll();

        Assertions.assertEquals(listCategorias.size(), returnCategorias.size());
        Assertions.assertEquals(listCategorias.get(0), returnCategorias.get(0));
        Assertions.assertEquals(listCategorias.get(1), returnCategorias.get(1));
    }

}
