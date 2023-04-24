package br.com.wferreiracosta.amy.controllers;

import br.com.wferreiracosta.amy.models.Categoria;

import java.util.List;

public interface CategoriaController {

    public List<Categoria> findAll();

    public Categoria findById(Long id);

}
