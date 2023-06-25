package br.com.wferreiracosta.amy.repositories.impl;

import br.com.wferreiracosta.amy.models.Categoria;
import br.com.wferreiracosta.amy.models.parameters.CategoriaParameter;
import br.com.wferreiracosta.amy.repositories.CategoriaRepository;
import br.com.wferreiracosta.amy.utils.mappers.CategoriaRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

import static br.com.wferreiracosta.amy.utils.queries.CategoriaQuery.*;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static org.springframework.jdbc.core.BeanPropertyRowMapper.newInstance;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CategoriaRepositoryImpl implements CategoriaRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final GeneratedKeyHolder keyHolder;

    @Override
    public List<Categoria> findAll() {
        return jdbcTemplate.query(FIND_ALL_CATEGORIAS, newInstance(Categoria.class));
    }

    @Override
    public Categoria findById(final Long id) {
        final var params = new MapSqlParameterSource()
                .addValue("id", id);
        try {
            return jdbcTemplate.queryForObject(FIND_CATEGORIA_BY_ID, params, new CategoriaRowMapper());
        } catch (final Exception e) {
            final var message = "Erro no momento de buscar categoria por id";
            log.error(getLogMessage(params, e, FIND_CATEGORIA_BY_ID, message));
            return null;
        }
    }

    @Override
    public List<Categoria> findByProdutoId(Long id) {
        final var params = new MapSqlParameterSource()
                .addValue("id", id);
        try {
            return jdbcTemplate.query(FIND_CATEGORIA_BY_PRODUTO_ID, params, new CategoriaRowMapper());
        } catch (final Exception e) {
            final var message = "Erro no momento de buscar categoria pelo id do produto";
            log.error(getLogMessage(params, e, FIND_CATEGORIA_BY_PRODUTO_ID, message));
            return List.of();
        }
    }

    @Override
    public Long insert(CategoriaParameter categoriaParameter) {
        final var params = new MapSqlParameterSource()
                .addValue("nome", categoriaParameter.getNome());
        try {
            jdbcTemplate.update(INSERT_CATEGORIA, params, keyHolder, new String[]{"id"});
            final var key = keyHolder.getKey();

            if (isNull(key)) {
                final var message = "Erro no momento de obter id quando foi inserir uma nova categoria no banco de dados";
                final var exception = new IllegalArgumentException(message);
                log.error(getLogMessage(params, exception, INSERT_CATEGORIA, message));
                throw exception;
            }

            return key.longValue();
        } catch (Exception e) {
            final var message = "Erro no momento de inserir uma nova categoria no banco de dados";
            log.error(getLogMessage(params, e, INSERT_CATEGORIA, message));
            return null;
        }
    }

    private String getLogMessage(MapSqlParameterSource params, Exception e, String query, String message) {
        return format("Params: %s | Exception: %s | Query: %s | Message: %s", params, e, query, message);
    }


}
