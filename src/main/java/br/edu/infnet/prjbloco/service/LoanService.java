package br.edu.infnet.prjbloco.service;

import br.edu.infnet.prjbloco.model.domain.Loan;

import java.util.List;

public interface LoanService {


    List<Loan> getAllLoans(Integer status);

    Loan saveLoan(Loan loan);

    String delete(Integer id);
}

