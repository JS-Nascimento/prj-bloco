package br.edu.infnet.prjbloco.model.domain;

import br.edu.infnet.prjbloco.model.domain.enuns.LoanPurpose;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
public class FgtsLoan extends LoanType {
    public FgtsLoan(Integer id, LoanPurpose loanPurpose, String name, Integer gracePeriod, BigDecimal interestRate, Integer loanTerm) {
        super(id, loanPurpose, name, gracePeriod, interestRate, loanTerm);
    }
}
