package br.com.wferreiracosta.amy.services;

import br.com.wferreiracosta.amy.models.Categoria;
import br.com.wferreiracosta.amy.models.CategoriaProdutos;

import java.util.List;

public interface CategoriaService {

    public List<Categoria> findAll();

    public Categoria findById(Long id);

    public List<Categoria> findByProdutoId(Long id);

    public CategoriaProdutos findCategoriaWithProdutosById(Long id);

}
