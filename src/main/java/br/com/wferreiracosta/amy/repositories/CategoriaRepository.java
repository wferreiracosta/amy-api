package br.com.wferreiracosta.amy.repositories;

import br.com.wferreiracosta.amy.models.Categoria;
import br.com.wferreiracosta.amy.models.parameters.CategoriaParameter;

import java.util.List;

public interface CategoriaRepository {

    public List<Categoria> findAll();
    public Categoria findById(Long id);

    public List<Categoria> findByProdutoId(Long id);

    public Categoria insert(CategoriaParameter categoriaParameter);

}
