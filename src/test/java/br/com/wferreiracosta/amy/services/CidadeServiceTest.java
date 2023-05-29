package br.com.wferreiracosta.amy.services;

import br.com.wferreiracosta.amy.models.CidadeEstado;
import br.com.wferreiracosta.amy.models.Estado;
import br.com.wferreiracosta.amy.repositories.CidadeRepository;
import br.com.wferreiracosta.amy.services.impl.CidadeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class CidadeServiceTest {

    @Mock
    private CidadeRepository cidadeRepository;

    private CidadeService cidadeService;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        cidadeService = new CidadeServiceImpl(cidadeRepository);
    }

    @Test
    public void testSearchFindByIdWithSucess(){
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

}
