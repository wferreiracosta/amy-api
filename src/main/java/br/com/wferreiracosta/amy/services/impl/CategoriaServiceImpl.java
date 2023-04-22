package br.com.wferreiracosta.amy.services.impl;

import br.com.wferreiracosta.amy.models.Categoria;
import br.com.wferreiracosta.amy.repositories.CategoriaRepository;
import br.com.wferreiracosta.amy.services.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

}
