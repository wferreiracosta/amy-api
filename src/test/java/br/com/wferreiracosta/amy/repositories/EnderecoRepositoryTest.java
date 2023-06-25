package br.com.wferreiracosta.amy.repositories;

import br.com.wferreiracosta.amy.exceptions.ObjectNotInsertException;
import br.com.wferreiracosta.amy.models.Endereco;
import br.com.wferreiracosta.amy.repositories.impl.EnderecoRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EnderecoRepositoryTest {

    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Mock
    private GeneratedKeyHolder keyHolder;

    private EnderecoRepository enderecoRepository;

    @BeforeEach
    void setUp() {
        enderecoRepository = new EnderecoRepositoryImpl(namedParameterJdbcTemplate, keyHolder);
    }

    @Test
    void testingInsertWithSucess() {
        final var endereco = Endereco.builder()
                .id(1L)
                .cep("02356898")
                .complemento("Teste Complemento")
                .bairro("Teste Bairro")
                .idCidade(1L)
                .ddd("11")
                .build();

        when(keyHolder.getKey()).thenReturn(endereco.getId());
        when(keyHolder.getKey().longValue()).thenReturn(endereco.getId());
        when(namedParameterJdbcTemplate.update(
                anyString(),
                any(MapSqlParameterSource.class),
                any(GeneratedKeyHolder.class),
                eq(new String[]{"id"}))
        ).thenReturn(1);

        final var enderecoIdSaved = enderecoRepository.insert(endereco);

        assertEquals(endereco.getId(), enderecoIdSaved);
    }

    @Test
    void testingInsertReturnkeyNullException() {
        final var endereco = Endereco.builder()
                .id(1L)
                .cep("02356898")
                .complemento("Teste Complemento")
                .bairro("Teste Bairro")
                .idCidade(1L)
                .ddd("11")
                .build();

        when(keyHolder.getKey()).thenReturn(null);
        when(namedParameterJdbcTemplate.update(
                anyString(),
                any(MapSqlParameterSource.class),
                any(GeneratedKeyHolder.class),
                eq(new String[]{"id"}))
        ).thenReturn(1);

        try {
            enderecoRepository.insert(endereco);
        } catch (final IllegalArgumentException e) {
            final var message = "Nao foi possivel obter id inserido";
            assertEquals(message, e.getLocalizedMessage());
        }
    }

    @Test
    void testingInsertReturnNullException() {
        final var endereco = Endereco.builder()
                .id(1L)
                .cep("02356898")
                .complemento("Teste Complemento")
                .bairro("Teste Bairro")
                .idCidade(1L)
                .ddd("11")
                .build();

        final var exception = mock(ObjectNotInsertException.class);

        when(namedParameterJdbcTemplate.update(
                anyString(),
                any(MapSqlParameterSource.class),
                any(GeneratedKeyHolder.class),
                eq(new String[]{"id"}))
        ).thenThrow(exception);

        assertNull(enderecoRepository.insert(endereco));
    }

}
