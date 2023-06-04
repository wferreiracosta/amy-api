package br.com.wferreiracosta.amy.models;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Estado {

    private Long id;
    private String nome;
    private String uf;

}
