package br.com.wferreiracosta.amy.repositories.impl;

import br.com.wferreiracosta.amy.models.Categoria;
import br.com.wferreiracosta.amy.repositories.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static br.com.wferreiracosta.amy.utils.queries.CategoriaQuery.FIND_ALL_CATEGORIAS;
import static org.springframework.jdbc.core.BeanPropertyRowMapper.newInstance;

@Repository
@RequiredArgsConstructor
public class CategoriaRepositoryImpl implements CategoriaRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Categoria> findAll() {
        return jdbcTemplate.query(FIND_ALL_CATEGORIAS, newInstance(Categoria.class));
    }

}
