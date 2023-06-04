package br.com.wferreiracosta.amy.services;

import br.com.wferreiracosta.amy.exceptions.ObjectNotFoundException;
import br.com.wferreiracosta.amy.models.Estado;
import br.com.wferreiracosta.amy.repositories.EstadoRepository;
import br.com.wferreiracosta.amy.services.impl.EstadoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.hibernate.validator.internal.util.StringHelper.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class EstadoServiceTest {

    @Mock
    private EstadoRepository estadoRepository;

    private EstadoService estadoService;

    @BeforeEach
    void setUp() {
        openMocks(this);
        estadoService = new EstadoServiceImpl(estadoRepository);
    }

    @Test
    void testingFindByIdWithSucess() {
        final var estado = Estado.builder().id(1L).nome("São Paulo").uf("SP").build();

        when(estadoRepository.findById(estado.getId())).thenReturn(estado);

        final var estadoReturn = estadoService.findById(estado.getId());

        assertEquals(estado, estadoReturn);
    }

    @Test
    void testingFindByIdWithException() {
        final var idEstado = 1L;

        when(estadoRepository.findById(idEstado)).thenReturn(null);

        try {
            estadoService.findById(idEstado);
        } catch (ObjectNotFoundException e) {
            final var message = format("Não foi encontrado estado com o id: %s", idEstado);
            assertEquals(message, e.getLocalizedMessage());
        }
    }

}
