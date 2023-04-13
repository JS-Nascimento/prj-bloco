package br.edu.infnet.prjbloco.service;

import br.edu.infnet.prjbloco.model.domain.Installments;
import br.edu.infnet.prjbloco.model.domain.Loan;
import br.edu.infnet.prjbloco.model.domain.User;

import java.math.BigDecimal;
import java.util.List;

public interface LoanService {


    List<Loan> getAllLoans(Integer status);

    Loan saveLoan(Loan loan);

    String delete(Integer id);

    String update(Integer id, User user);

    Loan getLoanById(Integer id);

    List<Installments> getInstallments(Integer loanTerm, Integer gracePeriod, BigDecimal totalLoan, BigDecimal interestRate);

    List<Installments> getInstallmentsWithId(Integer loanID, Integer loanTerm, Integer gracePeriod, BigDecimal totalLoan, BigDecimal interestRate);
}

