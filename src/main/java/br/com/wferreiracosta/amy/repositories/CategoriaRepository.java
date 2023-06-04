package br.com.wferreiracosta.amy.repositories;

import br.com.wferreiracosta.amy.models.Categoria;
import br.com.wferreiracosta.amy.models.parameters.CategoriaParameter;

import java.util.List;

public interface CategoriaRepository {

    List<Categoria> findAll();
    Categoria findById(Long id);

    List<Categoria> findByProdutoId(Long id);

    Long insert(CategoriaParameter categoriaParameter);

}
