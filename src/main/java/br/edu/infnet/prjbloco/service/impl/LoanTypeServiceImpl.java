package br.edu.infnet.prjbloco.service.impl;

import br.edu.infnet.prjbloco.DTO.LoanTypeDTO;
import br.edu.infnet.prjbloco.model.domain.FgtsLoan;
import br.edu.infnet.prjbloco.model.domain.LoanType;
import br.edu.infnet.prjbloco.model.domain.RetireesLoan;
import br.edu.infnet.prjbloco.model.domain.ServantLoan;
import br.edu.infnet.prjbloco.model.domain.enuns.LoanPurpose;
import br.edu.infnet.prjbloco.model.repository.LoanTypeRepository;
import br.edu.infnet.prjbloco.service.LoanTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LoanTypeServiceImpl implements LoanTypeService {

    private final LoanTypeRepository repository;
    private final ModelMapper modelMapper;

    public LoanTypeServiceImpl(LoanTypeRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public List<LoanType> getAllLoans(String filtro) {

        if (filtro.equals("ALL")) {

            Iterable<LoanType> loans = repository.findAll();
            return StreamSupport.stream(loans.spliterator(), false)
                    .collect(Collectors.toList());
        } else {
            Iterable<LoanType> loans = repository.findByLoanPurposeEquals(LoanPurpose.valueOf(filtro));
            return StreamSupport.stream(loans.spliterator(), false)
                    .collect(Collectors.toList());

        }

    }

    @Override
    public List<LoanTypeDTO> getAllLoansForCombo(String filtro) {


        List<Object[]> response = repository.findByLoanPurposeEqualsForCombo(LoanPurpose.valueOf(filtro));
        List<LoanTypeDTO> loanTypeDtoList = new ArrayList<>();
        for (Object[] objArray : response) {
            // Faça o mapeamento do Object[] para LoanTypeDto
            LoanTypeDTO loanTypeDto = new LoanTypeDTO();
            loanTypeDto.setId((Integer) objArray[0]);
            loanTypeDto.setName((String) objArray[1]);
            loanTypeDto.setGracePeriod((Integer) objArray[2]);
            loanTypeDto.setInterestRate((BigDecimal) objArray[3]);
            loanTypeDto.setLoanTerm((Integer) objArray[4]);

            loanTypeDtoList.add(loanTypeDto);
        }
        return loanTypeDtoList;

    }

    @Override
    public <T extends LoanType> T saveLoan(T loan) {
        return (T) repository.save(loan);
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

    public String validateFGTSLoan(FgtsLoan fgtsLoan) {

        int compareTo = fgtsLoan.getInterestRate().compareTo(BigDecimal.ZERO);

        if (compareTo <= 0) {
            return "A taxa de juros deve ser maior que 0.";
        }
        if (fgtsLoan.getGracePeriod() != 1) {
            return "Nessa modalidade FGTS o valor da carência deve ser sempre 1.";
        }
        if (fgtsLoan.getLoanTerm() != 1) {
            return "Nessa modalidade FGTS o valor do prazo de pagemento deve ser sempre 1.";
        }

        return "OK";
    }

    public String validateRetireesLoan(RetireesLoan retireesLoan) {

        int compareTo = retireesLoan.getInterestRate().compareTo(BigDecimal.ZERO);

        if (compareTo <= 0) {
            return "A taxa de juros deve ser maior que 0.";
        }
        if (retireesLoan.getGracePeriod() < 1 || retireesLoan.getGracePeriod() > 3) {
            return "Nessa modalidade o valor da carência deve ser entre 1 e 3 meses.";
        }
        if (retireesLoan.getLoanTerm() > 36 || retireesLoan.getLoanTerm() < 3) {
            return "Nessa modalidade o prazo de pagemento deve ser no minimo 3 e no máximo 36 meses.";
        }

        return "OK";
    }

    public String validateServantLoan(ServantLoan servantLoan) {

        int compareTo = servantLoan.getInterestRate().compareTo(BigDecimal.ZERO);

        if (compareTo <= 0) {
            return "A taxa de juros deve ser maior que 0.";
        }
        if (servantLoan.getGracePeriod() < 1 || servantLoan.getGracePeriod() > 12) {
            return "Nessa modalidade o valor da carência deve ser entre 1 e 3 meses.";
        }
        if (servantLoan.getLoanTerm() > 60 || servantLoan.getLoanTerm() < 6) {
            return "Nessa modalidade o prazo de pagemento deve ser no minimo 3 e no máximo 36 meses.";
        }

        return "OK";
    }

    public LoanType getLoanType(Integer id) {

        return (LoanType) repository.findById(id)
                .map(loantype -> modelMapper.map(loantype, LoanType.class))
                .orElseThrow();

    }

}
