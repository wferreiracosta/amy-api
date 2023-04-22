package br.com.wferreiracosta.amy.services;

import br.com.wferreiracosta.amy.models.Categoria;
import br.com.wferreiracosta.amy.repositories.CategoriaRepository;
import br.com.wferreiracosta.amy.services.impl.CategoriaServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    private CategoriaService categoriaService;

    @BeforeEach
    public void setUp() {
        categoriaService = new CategoriaServiceImpl(categoriaRepository);
    }

    @Test
    public void tetingFindAllReturnAllCategorias() {
        final var informatica = Categoria.builder()
                .id(1L)
                .nome("Informatica")
                .build();

        final var roupa = Categoria.builder()
                .id(2L)
                .nome("Roupa")
                .build();
        final var listCategorias = List.of(informatica, roupa);

        when(categoriaRepository.findAll()).thenReturn(listCategorias);

        final var returnCategorias = categoriaService.findAll();

        Assertions.assertEquals(listCategorias.size(), returnCategorias.size());
        Assertions.assertEquals(listCategorias.get(0), returnCategorias.get(0));
        Assertions.assertEquals(listCategorias.get(1), returnCategorias.get(1));
    }

}
