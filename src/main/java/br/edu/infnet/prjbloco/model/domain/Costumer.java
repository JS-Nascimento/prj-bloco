package br.edu.infnet.prjbloco.model.domain;

import br.edu.infnet.prjbloco.model.domain.enuns.TipoCliente;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Costumer {
    private Integer id;
    private String nome;
    private String cpf;
    private TipoCliente tipoCliente;
    private Integer idade;
    private Double saldoFgts;
    private String recompra;
    private Integer score;
    private Double rendaTotal;
    private Double limiteTotal;
    private Double limiteDisponivel;
}

