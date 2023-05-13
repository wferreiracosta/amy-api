package br.com.wferreiracosta.amy.controllers;

import br.com.wferreiracosta.amy.models.ProdutoCategoria;

public interface ProdutoController {

    public ProdutoCategoria findByIdWithCategoria(Long id);

}
