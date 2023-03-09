package br.edu.infnet.prjbloco.model.domain.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public enum TipoCliente {

    APOSENTADOS("Aponsentados e Pensionistas"),
    SERVIDORES( "Servidores Públicos"),
    FGTS("Antecipação do FGTS");


    private String descricao;







}
