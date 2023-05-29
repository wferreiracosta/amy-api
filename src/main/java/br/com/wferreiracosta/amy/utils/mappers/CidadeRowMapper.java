package br.com.wferreiracosta.amy.utils.mappers;

import br.com.wferreiracosta.amy.models.Cidade;
import br.com.wferreiracosta.amy.models.Estado;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class CidadeRowMapper implements RowMapper<Cidade> {

    @Override
    public Cidade mapRow(ResultSet rs, int rowNum) throws SQLException {
        final var estado = Estado.builder()
                .id(rs.getLong("id_estado"))
                .nome(rs.getString("nome_estado"))
                .uf(rs.getString("uf_estado"))
                .build();

        return Cidade.builder()
                .id(rs.getLong("id_cidade"))
                .nome(rs.getString("nome_cidade"))
                .estado(estado)
                .build();
    }

}
