package br.com.wferreiracosta.amy.utils.mappers;

import br.com.wferreiracosta.amy.models.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class ProdutoRowMapper implements RowMapper<Produto> {

    @Override
    public Produto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Produto.builder()
                .id(rs.getLong("id"))
                .nome(rs.getString("nome"))
                .descricao(rs.getString("descricao"))
                .preco(rs.getBigDecimal("preco"))
                .build();
    }

}
