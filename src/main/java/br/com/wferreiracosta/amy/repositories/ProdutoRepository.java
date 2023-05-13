package br.com.wferreiracosta.amy.repositories;

import br.com.wferreiracosta.amy.models.Produto;

public interface ProdutoRepository {

    Produto findById(Long id);

}
