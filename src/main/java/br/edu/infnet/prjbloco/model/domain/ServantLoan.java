package br.edu.infnet.prjbloco.model.domain;

import br.edu.infnet.prjbloco.model.domain.enuns.LoanPurpose;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Entity
public class ServantLoan extends LoanType {
    public ServantLoan(Integer id, LoanPurpose loanPurpose, String name, Integer gracePeriod, BigDecimal interestRate, Integer loanTerm) {
        super(id, loanPurpose, name, gracePeriod, interestRate, loanTerm);
    }
}
