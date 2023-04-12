package br.edu.infnet.prjbloco.service.impl;

import br.edu.infnet.prjbloco.model.domain.Loan;
import br.edu.infnet.prjbloco.model.repository.LoanRepository;
import br.edu.infnet.prjbloco.service.LoanService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository repository;

    public LoanServiceImpl(LoanRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Loan> getAllLoans(Integer status) {

        Iterable<Loan> loans;
        if (status == 0) {

            loans = repository.findAll();
        } else {
            loans = repository.findByStatusEquals(status);
        }
        return StreamSupport.stream(loans.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Loan saveLoan(Loan loan) {

        return repository.save(loan);
    }

    @Override
    public String delete(Integer id) {
        Boolean existLoan = repository.existsById(id);
        if (existLoan) {
            repository.deleteById(id);
            return "SUCCESS";
        } else {
            return "FAIL";
        }
    }
}
