package br.edu.infnet.prjbloco.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private LocalDate approvedAt;

    private Integer status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User approvalUser;

    @ManyToOne
    @JoinColumn(name = "userRegistry_id")
    private User registrantUser;
    @ManyToOne
    @JoinColumn(name = "costumer_id")
    private Costumer costumer;
    @ManyToOne
    @JoinColumn(name = "loan_type_id")
    private LoanType loanType;

    private Integer gracePeriod;

    private BigDecimal interestRate;

    private Integer loanTerm;

    @Column(precision = 20, scale = 2)
    private BigDecimal totalRequired;
    @Column(precision = 20, scale = 2)
    private BigDecimal totalLoan;

    @OneToMany(mappedBy = "loan", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Installments> installmentsList;

    public Loan(Integer id) {
        this.id = id;
    }

    public Loan(Integer id, Integer status, User approvalUser) {
        this.id = id;
        this.status = status;
        this.approvalUser = approvalUser;
    }

    public List<Installments> getInstallments(Integer loanTerm, Integer gracePeriod, BigDecimal totalLoan, BigDecimal interestRate) {

        List<Installments> parcelas = new ArrayList<>();

        BigDecimal valorParcela = BigDecimal.ZERO;
        BigDecimal valorEmprestimo = totalLoan;
        BigDecimal taxaJurosMensal = interestRate;

        // Calcular valor da parcela
        BigDecimal fatorJuros = BigDecimal.ONE.add(taxaJurosMensal.divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP));
        BigDecimal potencia = BigDecimal.ONE;
        LocalDate dataVencimento = LocalDate.now().plusMonths(gracePeriod - 1).with(TemporalAdjusters.lastDayOfMonth());
        for (int i = 1; i <= loanTerm; i++) {
            valorParcela = valorEmprestimo.multiply(fatorJuros.subtract(BigDecimal.ONE)).setScale(2, RoundingMode.HALF_UP);
            parcelas.add(new Installments(i, valorParcela, dataVencimento));

            valorEmprestimo = valorEmprestimo.multiply(fatorJuros);
            potencia = potencia.multiply(fatorJuros);
        }

        for (Installments parcela : parcelas) {
            parcela.setDueDate(dataVencimento);
            dataVencimento = dataVencimento.plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
        }


        return parcelas;
    }
}
