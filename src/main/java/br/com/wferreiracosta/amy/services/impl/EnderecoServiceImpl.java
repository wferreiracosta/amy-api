package br.com.wferreiracosta.amy.services.impl;

import br.com.wferreiracosta.amy.exceptions.ObjectNotInsertException;
import br.com.wferreiracosta.amy.models.Endereco;
import br.com.wferreiracosta.amy.repositories.EnderecoRepository;
import br.com.wferreiracosta.amy.services.EnderecoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnderecoServiceImpl implements EnderecoService {

    private final EnderecoRepository enderecoRepository;

    @Override
    public Long insert(final Endereco endereco) {
        final var id = enderecoRepository.insert(endereco);

        if(isNull(id)){
            final var message = "Nao foi possivel inserir o endereco";
            log.error(message);
            throw new ObjectNotInsertException(message);
        }

        return id;
    }

}
