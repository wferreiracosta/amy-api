package br.com.wferreiracosta.amy.utils.mappers;

import br.com.wferreiracosta.amy.models.Categoria;
import br.com.wferreiracosta.amy.models.CategoriaProdutos;
import br.com.wferreiracosta.amy.models.Produto;
import lombok.NoArgsConstructor;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class CategoriaMapper {

    public static Categoria map(Long id, String nome) {
        return Categoria.builder().id(id).nome(nome).build();
    }

    public static CategoriaProdutos map(Categoria categoria, List<Produto> produtoList) {
        return CategoriaProdutos.builder().id(categoria.getId()).nome(categoria.getNome()).produtos(produtoList).build();
    }

}
