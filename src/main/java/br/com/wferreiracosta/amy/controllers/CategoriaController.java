package br.com.wferreiracosta.amy.controllers;

import br.com.wferreiracosta.amy.models.Categoria;
import br.com.wferreiracosta.amy.models.CategoriaProdutos;

import java.util.List;

public interface CategoriaController {

    public List<Categoria> findAll();

    public CategoriaProdutos findById(Long id);

}
