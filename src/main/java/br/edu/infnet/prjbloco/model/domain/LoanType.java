package br.edu.infnet.prjbloco.model.domain;

import br.edu.infnet.prjbloco.model.domain.enuns.LoanPurpose;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Setter
@AllArgsConstructor
public abstract class LoanType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private LoanPurpose loanPurpose;
    @Column
    private String name;
    @Column
    private Integer gracePeriod;
    @Column(columnDefinition = "DECIMAL(10,2)")
    private BigDecimal interestRate;
    @Column
    private Integer loanTerm;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(cascade = CascadeType.DETACH)
    @JoinColumn(name = "loan_id")
    @JsonIgnore
    private List<Loan> loans;

    public LoanType() {
    }

    public LoanType(Integer id, LoanPurpose loanPurpose, String name, Integer gracePeriod, BigDecimal interestRate, Integer loanTerm) {
        this.id = id;
        this.loanPurpose = loanPurpose;
        this.name = name;
        this.gracePeriod = gracePeriod;
        this.interestRate = interestRate;
        this.loanTerm = loanTerm;
    }

    public String getLoanPurpose() {
        return loanPurpose.getName();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getGracePeriod() {
        return gracePeriod;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public Integer getLoanTerm() {
        return loanTerm;
    }

    public User getUser() {
        return user;
    }

    public List<Loan> getLoans() {
        return loans;
    }
}
