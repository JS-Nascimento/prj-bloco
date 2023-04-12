package br.edu.infnet.prjbloco.model.repository;

import br.edu.infnet.prjbloco.model.domain.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoanRepository extends CrudRepository<Loan, Integer> {


    List<Loan> findByStatusEquals(@Param("status") Integer status);


}
