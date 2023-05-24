package br.com.wferreiracosta.amy.services.impl;

import br.com.wferreiracosta.amy.exceptions.ObjectNotFoundException;
import br.com.wferreiracosta.amy.exceptions.ObjectNotInsertException;
import br.com.wferreiracosta.amy.models.Categoria;
import br.com.wferreiracosta.amy.models.CategoriaProdutos;
import br.com.wferreiracosta.amy.models.parameters.CategoriaParameter;
import br.com.wferreiracosta.amy.repositories.CategoriaRepository;
import br.com.wferreiracosta.amy.services.CategoriaService;
import br.com.wferreiracosta.amy.services.ProdutoService;
import br.com.wferreiracosta.amy.utils.mappers.CategoriaMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.wferreiracosta.amy.utils.mappers.CategoriaMapper.map;
import static java.lang.String.format;
import static java.util.Objects.isNull;

@Slf4j
@Service
public class CategoriaServiceImpl implements CategoriaService {

    private CategoriaRepository categoriaRepository;

    private ProdutoService produtoService;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository, @Lazy ProdutoService produtoService) {
        this.categoriaRepository = categoriaRepository;
        this.produtoService = produtoService;
    }

    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll().stream().sorted(Comparator.comparing(Categoria::getId)).collect(Collectors.toList());
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

    @Override
    public CategoriaProdutos findCategoriaWithProdutosById(Long id) {
        final var categoria = findById(id);
        final var produtos = produtoService.findProdutoByCategoriaId(id);
        return map(categoria, produtos);
    }

    @Override
    public Categoria insert(CategoriaParameter categoriaParameter) {
        final var categoria = categoriaRepository.insert(categoriaParameter);

        if (isNull(categoria)) {
            final var message = format("Erro no momento de cadastrar categoria com o nome %s", categoriaParameter.getNome());
            log.error(message);
            throw new ObjectNotInsertException(message);
        }

        return categoria;
    }

}
