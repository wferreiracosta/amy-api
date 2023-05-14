package br.com.wferreiracosta.amy.repositories.impl;

import br.com.wferreiracosta.amy.models.Categoria;
import br.com.wferreiracosta.amy.repositories.CategoriaRepository;
import br.com.wferreiracosta.amy.utils.mappers.CategoriaRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static br.com.wferreiracosta.amy.utils.queries.CategoriaQuery.*;
import static java.lang.String.format;
import static org.springframework.jdbc.core.BeanPropertyRowMapper.newInstance;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CategoriaRepositoryImpl implements CategoriaRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

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
            log.error(format("Params: %s | Exception: %s | Query: %s | Message: %s", params, e
                    , FIND_CATEGORIA_BY_ID, message));
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
            log.error(format("Params: %s | Exception: %s | Query: %s | Message: %s", params, e
                    , FIND_CATEGORIA_BY_PRODUTO_ID, message));
            return List.of();
        }
    }

}
