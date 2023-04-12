package br.edu.infnet.prjbloco.model.repository;

import br.edu.infnet.prjbloco.model.domain.LoanType;
import br.edu.infnet.prjbloco.model.domain.enuns.LoanPurpose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoanTypeRepository<T extends LoanType> extends JpaRepository<T, Integer> {


    List<T> findByLoanPurposeEquals(@Param("loanPurpose") LoanPurpose loanPurpose);

    @Query(value = "SELECT l.id, l.name, l.gracePeriod, l.interestRate, l.loanTerm FROM LoanType l WHERE l.loanPurpose = :loanPurpose")
    List<Object[]> findByLoanPurposeEqualsForCombo(@Param("loanPurpose") LoanPurpose loanPurpose);


}
