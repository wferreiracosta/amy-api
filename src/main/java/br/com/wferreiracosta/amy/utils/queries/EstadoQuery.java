package br.com.wferreiracosta.amy.utils.queries;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class EstadoQuery {

    public static String FIND_ESTADO_BY_ID = "SELECT id, nome, uf FROM estado WHERE id = :id";

}
