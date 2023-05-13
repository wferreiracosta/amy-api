package br.com.wferreiracosta.amy.services.impl;

import br.com.wferreiracosta.amy.exceptions.ObjectNotFoundException;
import br.com.wferreiracosta.amy.models.Categoria;
import br.com.wferreiracosta.amy.repositories.CategoriaRepository;
import br.com.wferreiracosta.amy.services.CategoriaService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll().stream()
                .sorted(Comparator.comparing(Categoria::getId))
                .collect(Collectors.toList());
    }

    @Override
    @SneakyThrows
    public Categoria findById(final Long id) {
        final var categoria = categoriaRepository.findById(id);

        if (isNull(categoria)) {
            final var message = format("Não foi encontrada Categoria com esse id: %s", id);
            log.error(message);
            throw new ObjectNotFoundException(message);
        }

        return categoria;
    }

    @Override
    public List<Categoria> findByProdutoId(Long id) {
        final var categorias = categoriaRepository.findByProdutoId(id);

        if (categorias.isEmpty()) {
            final var message = format("Não foi encontrada Categoria com esse id %s de Produto", id);
            log.error(message);
            throw new ObjectNotFoundException(message);
        }

        return categorias;
    }

}
