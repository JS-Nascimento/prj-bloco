package br.edu.infnet.prjbloco.model.domain.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NivelAcesso {

    ADMIN(1),
    PADRAO(2),
    DIRETORIA(3);


    private Integer id;


}

