package br.edu.infnet.prjbloco.model.domain.enuns;

public enum TipoCliente {

    APONSENTADOS("Aponsentados e Pensionistas"),
    SERVIDORES("Servidores Públicos"),
    FGTS("Antecipação do FGTS");

    private String descricao;

    TipoCliente(String descricao){
        this.descricao=descricao;
    }
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
