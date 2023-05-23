package br.com.wferreiracosta.amy.controllers.impl;

import br.com.wferreiracosta.amy.controllers.ProdutoController;
import br.com.wferreiracosta.amy.models.ProdutoCategoria;
import br.com.wferreiracosta.amy.services.CategoriaService;
import br.com.wferreiracosta.amy.services.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/produtos")
public class ProdutoControllerImpl implements ProdutoController {

    private final ProdutoService produtoService;

    @Override
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoCategoria findByIdWithCategoria(@PathVariable Long id) {
        return produtoService.findByIdWithCategoria(id);
    }

}
