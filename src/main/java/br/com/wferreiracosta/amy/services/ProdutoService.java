package br.com.wferreiracosta.amy.services;

import br.com.wferreiracosta.amy.models.Produto;
import br.com.wferreiracosta.amy.models.ProdutoCategoria;

public interface ProdutoService {

    public ProdutoCategoria findByIdWithCategoria(Long id);

    public Produto findById(Long id);

}
