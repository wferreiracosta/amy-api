package br.com.wferreiracosta.amy.utils.queries;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ProdutoQuery {

    public static final String FIND_PRODUTO_BY_ID = "SELECT id, nome, descricao, preco FROM amy.produto WHERE id = :id";

    public static final String FIND_PRODUTOS_BY_CATEGORIA_ID = "SELECT p.id as id, p.nome as nome" +
            ", p.descricao as descricao, p.preco as preco FROM produto p INNER JOIN produto_categoria c" +
            " ON c.id_produto = p.id WHERE c.id_categoria = :id_categoria";

}
