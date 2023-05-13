package br.com.wferreiracosta.amy.utils.queries;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ProdutoQuery {

    public static final String FIND_PRODUTO_BY_ID = "SELECT id, nome, descricao, preco FROM amy.produto WHERE id = :id";

}
