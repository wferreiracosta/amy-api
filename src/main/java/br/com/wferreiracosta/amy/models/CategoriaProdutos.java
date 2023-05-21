package br.com.wferreiracosta.amy.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class CategoriaProdutos {

    private Long id;
    private String nome;
    private List<Produto> produtos;

}
