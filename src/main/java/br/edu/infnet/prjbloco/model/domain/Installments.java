package br.edu.infnet.prjbloco.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Installments {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;

    private Integer installment;

    private BigDecimal installmentAmount;

    private LocalDate dueDate;

    public Installments(Integer installment, BigDecimal installmentAmount, LocalDate dueDate) {
        this.installment = installment;
        this.installmentAmount = installmentAmount;
        this.dueDate = dueDate;
    }

    public Installments(Loan loan, Integer installment, BigDecimal installmentAmount, LocalDate dueDate) {
        this.loan = loan;
        this.installment = installment;
        this.installmentAmount = installmentAmount;
        this.dueDate = dueDate;
    }
}
