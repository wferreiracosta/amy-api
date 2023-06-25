package br.com.wferreiracosta.amy.models;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Endereco {

    private Long id;
    private String cep;
    private String complemento;
    private String bairro;
    private Long idCidade;
    private String ddd;

}
