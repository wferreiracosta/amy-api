package br.com.wferreiracosta.amy.repositories.impl;

import br.com.wferreiracosta.amy.models.Endereco;
import br.com.wferreiracosta.amy.repositories.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import static br.com.wferreiracosta.amy.utils.LogUtil.getLogMessage;
import static br.com.wferreiracosta.amy.utils.queries.EnderecoQuery.INSERT_ENDERECO;
import static java.util.Objects.isNull;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EnderecoRepositoryImpl implements EnderecoRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final GeneratedKeyHolder keyHolder;

    @Override
    public Long insert(final Endereco endereco) {
        final var params = new MapSqlParameterSource()
                .addValue("cep", endereco.getCep())
                .addValue("complemento", endereco.getComplemento())
                .addValue("bairro", endereco.getBairro())
                .addValue("idCidade", endereco.getIdCidade())
                .addValue("ddd", endereco.getDdd());

        try {
            jdbcTemplate.update(INSERT_ENDERECO, params, keyHolder, new String[]{"id"});
            final var key = keyHolder.getKey();

            if (isNull(key)) {
                final var message = "Nao foi possivel obter id inserido";
                final var exception = new IllegalArgumentException(message);
                log.error(getLogMessage(params, exception, INSERT_ENDERECO, message));
                throw exception;
            }

            return key.longValue();
        } catch (Exception e) {
            final var message = "Nao foi possivel inserir o endereco";
            log.error(getLogMessage(params, e, INSERT_ENDERECO, message));
            return null;
        }
    }

}
