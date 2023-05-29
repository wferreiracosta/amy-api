package br.com.wferreiracosta.amy.repositories.impl;

import br.com.wferreiracosta.amy.models.Cidade;
import br.com.wferreiracosta.amy.repositories.CidadeRepository;
import br.com.wferreiracosta.amy.utils.mappers.CidadeRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import static br.com.wferreiracosta.amy.utils.queries.CategoriaQuery.FIND_CATEGORIA_BY_ID;
import static br.com.wferreiracosta.amy.utils.queries.CidadeQuery.FIND_CIDADE_WITH_ESTADO_BY_ID;
import static br.com.wferreiracosta.amy.utils.queries.EstadoQuery.FIND_ESTADO_BY_ID;
import static java.lang.String.format;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CidadeRepositoryImpl implements CidadeRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Cidade findById(Long id) {
        final var params = new MapSqlParameterSource()
                .addValue("id", id);
        try {
            return jdbcTemplate.queryForObject(FIND_CIDADE_WITH_ESTADO_BY_ID, params, new CidadeRowMapper());
        } catch (final Exception e) {
            final var message = "Erro no momento de buscar cidade por id";
            log.error(format("Params: %s | Exception: %s | Query: %s | Message: %s", params, e
                    , FIND_CIDADE_WITH_ESTADO_BY_ID, message));
            return null;
        }
    }

}
