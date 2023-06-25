package br.com.wferreiracosta.amy.services;

import br.com.wferreiracosta.amy.exceptions.ObjectNotInsertException;
import br.com.wferreiracosta.amy.models.Endereco;
import br.com.wferreiracosta.amy.repositories.EnderecoRepository;
import br.com.wferreiracosta.amy.services.impl.EnderecoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EnderecoServiceTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    private EnderecoService enderecoService;

    @BeforeEach
    void setUp() {
        enderecoService = new EnderecoServiceImpl(enderecoRepository);
    }

    @Test
    void testingInsertWithSuccess() {
        final var endereco = Endereco.builder()
                .id(1L)
                .cep("124578")
                .ddd("11")
                .bairro("Teste Bairro")
                .complemento("Teste Complemento")
                .idCidade(1L)
                .build();

        when(enderecoRepository.insert(endereco))
                .thenReturn(endereco.getId());

        final var enderecoSaved = enderecoService.insert(endereco);

        assertEquals(endereco.getId(), enderecoSaved);
    }

    @Test
    void testingInsertReturnException() {
        final var endereco = Endereco.builder()
                .id(1L)
                .cep("124578")
                .ddd("11")
                .bairro("Teste Bairro")
                .complemento("Teste Complemento")
                .idCidade(1L)
                .build();

        when(enderecoRepository.insert(endereco))
                .thenReturn(null);

        try {
            enderecoService.insert(endereco);
        } catch (final ObjectNotInsertException e) {
            final var message = "Nao foi possivel inserir o endereco";
            assertEquals(message, e.getLocalizedMessage());
        }
    }

}
