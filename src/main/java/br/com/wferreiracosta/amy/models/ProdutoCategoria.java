package br.com.wferreiracosta.amy.models;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class ProdutoCategoria {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private List<Categoria> categorias;

}
