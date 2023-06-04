package br.com.wferreiracosta.amy.repositories.impl;

import br.com.wferreiracosta.amy.models.Estado;
import br.com.wferreiracosta.amy.repositories.EstadoRepository;
import br.com.wferreiracosta.amy.utils.mappers.EstadoRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import static br.com.wferreiracosta.amy.utils.queries.EstadoQuery.FIND_ESTADO_BY_ID;
import static java.lang.String.format;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EstadoRepositoryImpl implements EstadoRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Estado findById(Long id) {
        final var params = new MapSqlParameterSource()
                .addValue("id", id);
        try {
            return jdbcTemplate.queryForObject(FIND_ESTADO_BY_ID, params, new EstadoRowMapper());
        } catch (final Exception e) {
            final var message = "Erro no momento de buscar estado por id";
            log.error(format("Params: %s | Exception: %s | Query: %s | Message: %s", params, e
                    , FIND_ESTADO_BY_ID, message));
            return null;
        }
    }

}
