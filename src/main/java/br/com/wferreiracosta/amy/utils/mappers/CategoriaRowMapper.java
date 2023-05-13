package br.com.wferreiracosta.amy.utils.mappers;

import br.com.wferreiracosta.amy.models.Categoria;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class CategoriaRowMapper implements RowMapper<Categoria> {

    @Override
    public Categoria mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
        return Categoria.builder()
                .id(resultSet.getLong("id"))
                .nome(resultSet.getString("nome"))
                .build();
    }

}
