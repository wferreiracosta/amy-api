package br.com.wferreiracosta.amy.utils.mappers;

import br.com.wferreiracosta.amy.models.CidadeEstado;
import br.com.wferreiracosta.amy.models.Estado;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class CidadeEstadoRowMapper implements RowMapper<CidadeEstado> {

    @Override
    public CidadeEstado mapRow(ResultSet rs, int rowNum) throws SQLException {
        final var estado = Estado.builder()
                .id(rs.getLong("id_estado"))
                .nome(rs.getString("nome_estado"))
                .uf(rs.getString("uf_estado"))
                .build();

        return CidadeEstado.builder()
                .id(rs.getLong("id_cidade"))
                .nome(rs.getString("nome_cidade"))
                .estado(estado)
                .build();
    }

}
