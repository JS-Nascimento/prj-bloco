package br.edu.infnet.prjbloco.model.domain;

import br.edu.infnet.prjbloco.model.domain.enuns.TipoCliente;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
@Entity
@Table(name = "costumer")
public class Costumer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @NotEmpty
    private String nome;
    private String cpf;
    @Enumerated(EnumType.STRING)
    private TipoCliente tipoCliente;
    @Column(precision = 3, scale = 0)
    private Integer idade;
    @Column(precision = 12, scale = 2)
    private BigDecimal saldoFgts;
    private String recompra;
    @Column(precision = 3, scale = 0)
    private Integer score;
    @Column(precision = 12, scale = 2)
    private BigDecimal rendaTotal;
    @Column(precision = 12, scale = 2)
    private BigDecimal limiteTotal;
    @Column(precision = 12, scale = 2)
    private BigDecimal limiteDisponivel;






}

