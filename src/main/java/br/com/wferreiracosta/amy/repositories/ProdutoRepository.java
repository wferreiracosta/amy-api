package br.com.wferreiracosta.amy.repositories;

import br.com.wferreiracosta.amy.models.Produto;

import java.util.List;

public interface ProdutoRepository {

    public Produto findById(Long id);

    public List<Produto> findProdutoByCategoriaId(Long id);

}
