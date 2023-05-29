package br.com.wferreiracosta.amy.services;

import br.com.wferreiracosta.amy.models.Cidade;
import br.com.wferreiracosta.amy.models.CidadeEstado;

import java.util.List;

public interface CidadeService {

    public CidadeEstado findById(Long id);

    public List<Cidade> findAllByEstadoUf(String uf);

}
