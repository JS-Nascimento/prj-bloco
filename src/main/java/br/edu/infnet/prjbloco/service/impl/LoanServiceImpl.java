package br.edu.infnet.prjbloco.service.impl;

import br.edu.infnet.prjbloco.model.domain.Costumer;
import br.edu.infnet.prjbloco.model.domain.Installments;
import br.edu.infnet.prjbloco.model.domain.Loan;
import br.edu.infnet.prjbloco.model.domain.User;
import br.edu.infnet.prjbloco.model.repository.CostumerRepository;
import br.edu.infnet.prjbloco.model.repository.InstallmentRepository;
import br.edu.infnet.prjbloco.model.repository.LoanRepository;
import br.edu.infnet.prjbloco.service.CostumerService;
import br.edu.infnet.prjbloco.service.LoanService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository repository;
    private final InstallmentRepository installmentRepository;

    private final CostumerRepository costumerRepository;
    private final CostumerService costumerService;
    private final ModelMapper modelMapper;

    public LoanServiceImpl(LoanRepository repository, InstallmentRepository installmentRepository, CostumerRepository costumerRepository, CostumerService costumerService, ModelMapper modelMapper) {
        this.repository = repository;
        this.installmentRepository = installmentRepository;
        this.costumerRepository = costumerRepository;
        this.costumerService = costumerService;
        this.modelMapper = modelMapper;
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

        Loan loanS = repository.save(loan);
        List<Installments> parcelas = getInstallmentsWithId(loanS.getId(), loan.getLoanTerm(), loan.getGracePeriod(), loan.getTotalLoan(), loan.getInterestRate());

        installmentRepository.saveAll(parcelas);
        loanS.setInstallmentsList(parcelas);

        Costumer costumer = costumerService.getCostumerById(loanS.getCostumer().getId());

        costumer.setLimiteDisponivel(costumer.getLimiteTotal().subtract(loanS.getTotalLoan()));

        costumerRepository.save(costumer);

        return loanS;


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

    @Override
    public Loan getLoanById(Integer id) {
        return repository.findById(id)
                .map(loan -> modelMapper.map(loan, Loan.class))
                .orElseThrow();
    }

    @Override
    public String update(Integer id, User user) {

        Loan existLoan = getLoanById(id);
        if (!(existLoan == null)) {
            existLoan.setApprovalUser(user);
            existLoan.setStatus(2);
            repository.save(existLoan);
            return "SUCCESS";
        } else {
            return "FAIL";
        }
    }

    @Override
    public List<Installments> getInstallments(Integer loanTerm, Integer gracePeriod, BigDecimal totalLoan, BigDecimal interestRate) {

        List<Installments> parcelas = new ArrayList<>();

        BigDecimal valorParcela = BigDecimal.ZERO;
        BigDecimal valorEmprestimo = totalLoan;
        BigDecimal taxaJurosMensal = interestRate;

        // Calcular valor da parcela
        BigDecimal fatorJuros = BigDecimal.ONE.add(taxaJurosMensal.divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP));
        BigDecimal potencia = BigDecimal.ONE;
        LocalDate dataVencimento = LocalDate.now().plusMonths(gracePeriod - 1).with(TemporalAdjusters.lastDayOfMonth());
        for (int i = 1; i <= loanTerm; i++) {
            valorParcela = valorEmprestimo.multiply(fatorJuros.subtract(BigDecimal.ONE)).setScale(2, RoundingMode.HALF_UP);
            parcelas.add(new Installments(i, valorParcela, dataVencimento));

            valorEmprestimo = valorEmprestimo.multiply(fatorJuros);
            potencia = potencia.multiply(fatorJuros);
        }

        for (Installments parcela : parcelas) {
            parcela.setDueDate(dataVencimento);
            dataVencimento = dataVencimento.plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
        }


        return parcelas.stream()
                .map(parcela -> new Installments(parcela.getInstallment(), parcela.getInstallmentAmount(), parcela.getDueDate()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Installments> getInstallmentsWithId(Integer loanID, Integer loanTerm, Integer gracePeriod, BigDecimal totalLoan, BigDecimal interestRate) {

        List<Installments> parcelas = new ArrayList<>();

        BigDecimal valorParcela = BigDecimal.ZERO;
        BigDecimal valorEmprestimo = totalLoan;
        BigDecimal taxaJurosMensal = interestRate;

        // Calcular valor da parcela
        BigDecimal fatorJuros = BigDecimal.ONE.add(taxaJurosMensal.divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP));
        BigDecimal potencia = BigDecimal.ONE;
        LocalDate dataVencimento = LocalDate.now().plusMonths(gracePeriod - 1).with(TemporalAdjusters.lastDayOfMonth());
        for (int i = 1; i <= loanTerm; i++) {
            valorParcela = valorEmprestimo.multiply(fatorJuros.subtract(BigDecimal.ONE)).setScale(2, RoundingMode.HALF_UP);
            parcelas.add(new Installments(i, valorParcela, dataVencimento));

            valorEmprestimo = valorEmprestimo.multiply(fatorJuros);
            potencia = potencia.multiply(fatorJuros);
        }

        for (Installments parcela : parcelas) {
            parcela.setDueDate(dataVencimento);
            parcela.setLoan(new Loan(loanID));
            dataVencimento = dataVencimento.plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
        }


        return parcelas.stream()
                .map(parcela -> new Installments(parcela.getLoan(), parcela.getInstallment(), parcela.getInstallmentAmount(), parcela.getDueDate()))
                .collect(Collectors.toList());
    }
}
