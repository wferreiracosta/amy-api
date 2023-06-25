package br.com.wferreiracosta.amy.utils.queries;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class EnderecoQuery {

    public static final String INSERT_ENDERECO = "insert into endereco (cep, complemento, bairro, ddd, id_cidade) values (:cep, :complemento, :bairro, :ddd, :id_cidade)";

}
