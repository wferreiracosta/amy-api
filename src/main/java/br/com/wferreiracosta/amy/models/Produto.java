package br.com.wferreiracosta.amy.models;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Produto {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;

}
