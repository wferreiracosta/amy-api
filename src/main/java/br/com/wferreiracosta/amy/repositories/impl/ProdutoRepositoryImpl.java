package br.com.wferreiracosta.amy.repositories.impl;

import br.com.wferreiracosta.amy.models.Produto;
import br.com.wferreiracosta.amy.repositories.ProdutoRepository;
import br.com.wferreiracosta.amy.utils.mappers.ProdutoRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import static br.com.wferreiracosta.amy.utils.queries.CategoriaQuery.FIND_CATEGORIA_BY_ID;
import static br.com.wferreiracosta.amy.utils.queries.ProdutoQuery.FIND_PRODUTO_BY_ID;
import static java.lang.String.format;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProdutoRepositoryImpl implements ProdutoRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Produto findById(Long id) {
        final var params = new MapSqlParameterSource()
                .addValue("id", id);
        try {
            return jdbcTemplate.queryForObject(FIND_PRODUTO_BY_ID, params, new ProdutoRowMapper());
        } catch (Exception e) {
            final var message = "Erro no momento de buscar produto por id";
            log.error(format("Params: %s | Exception: %s | Query: %s | Message: %s", params, e
                    , FIND_CATEGORIA_BY_ID, message));
            return null;
        }
    }

}
