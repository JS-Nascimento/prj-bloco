package br.edu.infnet.prjbloco.service;

import br.edu.infnet.prjbloco.DTO.LoanTypeDTO;
import br.edu.infnet.prjbloco.model.domain.FgtsLoan;
import br.edu.infnet.prjbloco.model.domain.LoanType;
import br.edu.infnet.prjbloco.model.domain.RetireesLoan;
import br.edu.infnet.prjbloco.model.domain.ServantLoan;

import java.util.List;

public interface LoanTypeService {
    List<LoanType> getAllLoans(String filtro);

    LoanType getLoanType(Integer id);

    List<LoanTypeDTO> getAllLoansForCombo(String filtro);

    <T extends LoanType> T saveLoan(T loan);

    String delete(Integer id);

    String validateFGTSLoan(FgtsLoan fgtsLoan);

    String validateRetireesLoan(RetireesLoan retireesLoan);

    String validateServantLoan(ServantLoan servantLoan);
}
