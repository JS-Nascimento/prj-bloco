package br.edu.infnet.prjbloco.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
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

    @OneToMany(mappedBy = "loan")
    private List<Installments> installmentsList;


}
