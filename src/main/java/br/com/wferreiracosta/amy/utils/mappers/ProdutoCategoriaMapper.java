package br.com.wferreiracosta.amy.utils.mappers;

import br.com.wferreiracosta.amy.models.Categoria;
import br.com.wferreiracosta.amy.models.Produto;
import br.com.wferreiracosta.amy.models.ProdutoCategoria;
import lombok.NoArgsConstructor;


import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class ProdutoCategoriaMapper {

    public static ProdutoCategoria map(final Produto produto, final List<Categoria> categorias){
        return ProdutoCategoria.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .preco(produto.getPreco())
                .categorias(categorias)
                .build();
    }

}
