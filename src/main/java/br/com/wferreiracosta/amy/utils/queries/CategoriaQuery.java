package br.com.wferreiracosta.amy.utils.queries;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CategoriaQuery {

    public static final String FIND_ALL_CATEGORIAS = "select id, nome from categoria";

    public static final String FIND_CATEGORIA_BY_ID = "select id, nome from categoria where id = :id";

}
