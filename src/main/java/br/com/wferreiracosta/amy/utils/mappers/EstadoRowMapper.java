package br.com.wferreiracosta.amy.utils.mappers;

import br.com.wferreiracosta.amy.models.Estado;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class EstadoRowMapper implements RowMapper<Estado> {

    @Override
    public Estado mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Estado.builder()
                .id(rs.getLong("id"))
                .nome(rs.getString("nome"))
                .uf(rs.getString("uf"))
                .build();
    }

}
