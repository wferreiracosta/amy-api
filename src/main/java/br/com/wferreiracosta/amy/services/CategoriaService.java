package br.com.wferreiracosta.amy.services;

import br.com.wferreiracosta.amy.models.Categoria;

import java.util.List;

public interface CategoriaService {

    public List<Categoria> findAll();

    public Categoria findById(Long id);

}
