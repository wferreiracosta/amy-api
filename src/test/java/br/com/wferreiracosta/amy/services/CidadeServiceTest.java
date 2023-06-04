package br.com.wferreiracosta.amy.services;

import br.com.wferreiracosta.amy.exceptions.ObjectNotFoundException;
import br.com.wferreiracosta.amy.models.Cidade;
import br.com.wferreiracosta.amy.models.CidadeEstado;
import br.com.wferreiracosta.amy.models.Estado;
import br.com.wferreiracosta.amy.repositories.CidadeRepository;
import br.com.wferreiracosta.amy.services.impl.CidadeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class CidadeServiceTest {

    @Mock
    private CidadeRepository cidadeRepository;

    private CidadeService cidadeService;

    @BeforeEach
    void setUp() {
        openMocks(this);
        cidadeService = new CidadeServiceImpl(cidadeRepository);
    }

    @Test
    void testFindByIdWithSucess() {
        final var estado = Estado.builder()
                .id(1L)
                .nome("São Paulo")
                .uf("SP")
                .build();

        final var cidade = CidadeEstado.builder()
                .id(1L)
                .nome("São Paulo")
                .estado(estado)
                .build();

        when(cidadeRepository.findById(cidade.getId())).thenReturn(cidade);

        final var returnCidade = cidadeService.findById(cidade.getId());

        assertEquals(cidade.getId(), returnCidade.getId());
        assertEquals(cidade.getNome(), returnCidade.getNome());
        assertEquals(cidade.getEstado(), returnCidade.getEstado());
    }

    @Test
    void testFindByIdWithException() {
        final var cidadeId = 1L;
        when(cidadeRepository.findById(cidadeId)).thenReturn(null);
        try {
            cidadeService.findById(cidadeId);
        } catch (ObjectNotFoundException e) {
            final var message = format("Não foi encontrado cidade com o id %s", cidadeId);
            assertEquals(message, e.getLocalizedMessage());
        }
    }

    @Test
    void testFindAllByEstadoUfWithSucess(){
        final var saoPaulo = Cidade.builder().id(1L).nome("São Paulo").build();
        final var campinas = Cidade.builder().id(2L).nome("Campinas").build();
        final var cidades = List.of(saoPaulo, campinas);
        final var uf = "SP";

        when(cidadeRepository.findByEstadoUf(uf)).thenReturn(cidades);

        final var cidadesReturn = cidadeService.findAllByEstadoUf(uf);

        assertFalse(cidadesReturn.isEmpty());
        assertEquals(cidades.size(), cidadesReturn.size());
        assertEquals(saoPaulo, cidadesReturn.get(0));
        assertEquals(campinas, cidadesReturn.get(1));
    }

    @Test
    void testFindAllByEstadoUfWithException(){
        final var uf = "SP";

        when(cidadeRepository.findByEstadoUf(uf)).thenReturn(List.of());

        try{
            cidadeService.findAllByEstadoUf(uf);
        } catch (ObjectNotFoundException e){
            final var message = format("Não foi encontrado cidade pertencente a uf %s", uf);
            assertEquals(message, e.getLocalizedMessage());
        }
    }

}
