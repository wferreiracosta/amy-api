package br.com.wferreiracosta.amy.utils.queries;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CidadeQuery {

    public static String FIND_CIDADE_WITH_ESTADO_BY_ID = "SELECT c.id as id_cidade, c.nome as nome_cidade, e.id as id_estado, e.nome as nome_estado, e.uf as uf_estado FROM cidade c INNER JOIN estado e ON c.id_estado = e.id WHERE c.id = :id";
    public static String FIND_ALL_CIDADES_BY_ESTADO_UF = "SELECT c.id as id_cidade, c.nome as nome_cidade FROM cidade c INNER JOIN estado e ON c.id_estado = e.id WHERE e.uf = :uf";

}
