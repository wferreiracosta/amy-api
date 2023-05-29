package br.com.wferreiracosta.amy.services.impl;

import br.com.wferreiracosta.amy.exceptions.ObjectNotInsertException;
import br.com.wferreiracosta.amy.models.Cidade;
import br.com.wferreiracosta.amy.models.CidadeEstado;
import br.com.wferreiracosta.amy.repositories.CidadeRepository;
import br.com.wferreiracosta.amy.services.CidadeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;
import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class CidadeServiceImpl implements CidadeService {

    private final CidadeRepository cidadeRepository;

    @Override
    public CidadeEstado findById(Long id) {
        final var cidade = cidadeRepository.findById(id);

        if (isNull(cidade)) {
            final var message = format("Não foi encontrado cidade com o id %s", id);
            log.error(message);
            throw new ObjectNotInsertException(message);
        }

        return cidade;
    }

    @Override
    public List<Cidade> findAllByEstadoUf(String uf) {
        final var cidades = cidadeRepository.findByEstadoUf(uf);

        if (cidades.isEmpty()) {
            final var message = format("Não foi encontrado cidade com a uf %s de estado %s", uf);
            log.error(message);
            throw new ObjectNotInsertException(message);
        }

        return cidades;
    }

}
