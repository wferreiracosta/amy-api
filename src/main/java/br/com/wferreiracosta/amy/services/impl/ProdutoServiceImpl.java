package br.com.wferreiracosta.amy.services.impl;

import br.com.wferreiracosta.amy.exceptions.ObjectNotFoundException;
import br.com.wferreiracosta.amy.models.Produto;
import br.com.wferreiracosta.amy.models.ProdutoCategoria;
import br.com.wferreiracosta.amy.repositories.ProdutoRepository;
import br.com.wferreiracosta.amy.services.CategoriaService;
import br.com.wferreiracosta.amy.services.ProdutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static br.com.wferreiracosta.amy.utils.mappers.ProdutoCategoriaMapper.map;
import static java.lang.String.format;
import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaService categoriaService;

    @Override
    public ProdutoCategoria findByIdWithCategoria(final Long id) {
        final var categorias = categoriaService.findByProdutoId(id);
        final var produto = findById(id);
        return map(produto, categorias);
    }

    @Override
    public Produto findById(final Long id) {
        final var produto = produtoRepository.findById(id);

        if (isNull(produto)) {
            final var message = format("Não foi encontrada Produto com esse id: %s", id);
            log.error(message);
            throw new ObjectNotFoundException(message);
        }

        return produto;
    }

}
