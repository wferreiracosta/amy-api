package br.com.wferreiracosta.amy.repositories;

import br.com.wferreiracosta.amy.models.Cidade;
import br.com.wferreiracosta.amy.models.CidadeEstado;

import java.util.List;

public interface CidadeRepository {

    public CidadeEstado findById(Long id);

    public List<Cidade> findByEstadoUf(String uf);

}
