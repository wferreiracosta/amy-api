package br.com.wferreiracosta.amy.controllers.impl;

import br.com.wferreiracosta.amy.controllers.CategoriaController;
import br.com.wferreiracosta.amy.models.Categoria;
import br.com.wferreiracosta.amy.services.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

}
