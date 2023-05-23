package br.com.wferreiracosta.amy.utils.mappers;

import br.com.wferreiracosta.amy.models.Categoria;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class CatgoriaMapper {

    public static Categoria map(Long id, String nome) {
        return Categoria.builder().id(id).nome(nome).build();
    }

}
