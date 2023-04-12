package br.edu.infnet.prjbloco.model.domain.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum LoanPurpose {

    RETIREES("RETIREES"),
    SERVANT("SERVANT"),
    FGTS("FGTS");

    private String name;


}
