package br.com.wferreiracosta.amy.controllers;

import br.com.wferreiracosta.amy.models.Categoria;
import br.com.wferreiracosta.amy.models.CategoriaProdutos;
import br.com.wferreiracosta.amy.models.parameters.CategoriaParameter;

import java.util.List;

public interface CategoriaController {

    public List<Categoria> findAll();

    public CategoriaProdutos findById(Long id);

    public Categoria insert(CategoriaParameter categoriaParameter);

}
