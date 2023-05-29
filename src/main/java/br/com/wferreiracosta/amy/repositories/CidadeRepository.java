package br.com.wferreiracosta.amy.repositories;

import br.com.wferreiracosta.amy.models.Cidade;

public interface CidadeRepository {

    public Cidade findById(Long id);

}
