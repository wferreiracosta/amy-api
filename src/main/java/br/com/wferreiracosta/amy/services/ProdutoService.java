package br.com.wferreiracosta.amy.services;

import br.com.wferreiracosta.amy.models.Produto;
import br.com.wferreiracosta.amy.models.ProdutoCategoria;

import java.util.List;

public interface ProdutoService {


    public ProdutoCategoria findByIdWithCategoria(Long id);

    public Produto findById(Long id);

    public List<Produto> findProdutoByCategoriaId(Long id);

}
