package br.com.wferreiracosta.amy.models;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Cidade {

    private Long id;
    private String nome;
    private Estado estado;

}
