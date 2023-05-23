package br.com.wferreiracosta.amy.controllers.impl;

import br.com.wferreiracosta.amy.controllers.CategoriaController;
import br.com.wferreiracosta.amy.models.Categoria;
import br.com.wferreiracosta.amy.models.CategoriaProdutos;
import br.com.wferreiracosta.amy.models.parameters.CategoriaParameter;
import br.com.wferreiracosta.amy.services.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/categorias")
public class CategoriaControllerImpl implements CategoriaController {

    private final CategoriaService categoriaService;

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Categoria> findAll() {
        return categoriaService.findAll();
    }

    @Override
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoriaProdutos findById(@PathVariable Long id) {
        return categoriaService.findCategoriaWithProdutosById(id);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria insert(@RequestBody @Valid CategoriaParameter categoriaParameter) {
        return null;
    }

}
