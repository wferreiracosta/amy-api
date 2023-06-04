package br.com.wferreiracosta.amy.services.impl;

import br.com.wferreiracosta.amy.exceptions.ObjectNotFoundException;
import br.com.wferreiracosta.amy.exceptions.ObjectNotInsertException;
import br.com.wferreiracosta.amy.models.Estado;
import br.com.wferreiracosta.amy.repositories.EstadoRepository;
import br.com.wferreiracosta.amy.services.EstadoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.lang.String.format;
import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class EstadoServiceImpl implements EstadoService {

    private final EstadoRepository estadoRepository;

    @Override
    public Estado findById(Long id) {
        final var estado = estadoRepository.findById(id);

        if (isNull(estado)) {
            final var message = format("Não foi encontrado estado com o id: %s", id);
            log.error(message);
            throw new ObjectNotFoundException(message);
        }

        return estado;
    }

}
