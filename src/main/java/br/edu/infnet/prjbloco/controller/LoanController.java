package br.edu.infnet.prjbloco.controller;

import br.edu.infnet.prjbloco.DTO.LoanTypeDTO;
import br.edu.infnet.prjbloco.model.domain.*;
import br.edu.infnet.prjbloco.service.CostumerService;
import br.edu.infnet.prjbloco.service.LoanService;
import br.edu.infnet.prjbloco.service.LoanTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Controller
public class LoanController {

    private final LoanService service;

    private final CostumerService costumerService;

    private final LoanTypeService loanTypeService;
    private String message;
    private Integer messageType;

    public LoanController(LoanService service, CostumerService costumerService, LoanTypeService loanTypeService) {
        this.service = service;
        this.costumerService = costumerService;
        this.loanTypeService = loanTypeService;
    }

    @GetMapping(value = "/loan")
    public String callFormRegister(Model model, @SessionAttribute("userLogged") User loggedUser) {

        model.addAttribute("message", message);
        model.addAttribute("costumers", costumerService.getAllCostumers(loggedUser));
        model.addAttribute("messageType", messageType);
        message = null;
        messageType = 2;
        return "loan/register";
    }

    @GetMapping(value = "/loan/viewList")
    public String callViewList(Model model, String filtro) {

        model.addAttribute("efectiveLoans", service.getAllLoans(Integer.parseInt(filtro == null ? "0" : filtro)));
        model.addAttribute("loanPurposes", br.edu.infnet.prjbloco.model.domain.enuns.LoanPurpose.values());
        model.addAttribute("message", message);
        model.addAttribute("messageType", messageType);

        message = null;
        messageType = 2;

        return "loan/viewList";
    }

    @PostMapping(value = "/efectivefiltered")
    public String callforFilter(Model model, @RequestParam("filtro") String filtro) {

        return callViewList(model, filtro);
    }

    @PostMapping(value = "/loan/create")
    public String create(Model model,
                         @RequestParam("costumer") String costumer_id,
                         @RequestParam("loanType") String loan_type_id,
                         @RequestParam("interestRate") String interestRate,
                         @RequestParam("gracePeriod") String grace,
                         @RequestParam("loanTerm") String term,
                         @RequestParam("totalRequired") String totalR,
                         @RequestParam("totalLoan") String totalL,

                         @SessionAttribute("userLogged") User loggedUser) {


        Loan efectiveLoan = new Loan();

        efectiveLoan.setCostumer(costumerService.getCostumerById(Integer.parseInt(costumer_id)));
        efectiveLoan.setLoanType(loanTypeService.getLoanType(Integer.parseInt(loan_type_id)));
        efectiveLoan.setGracePeriod(Integer.parseInt(grace));
        efectiveLoan.setInterestRate(new BigDecimal((interestRate)));
        efectiveLoan.setLoanTerm(Integer.parseInt(term));
        efectiveLoan.setTotalRequired(new BigDecimal(totalR));
        efectiveLoan.setTotalLoan(new BigDecimal(totalL));
        efectiveLoan.setRegistrantUser(loggedUser);
        efectiveLoan.setApprovedAt(LocalDate.now());
        efectiveLoan.setStatus(1);


        Loan newLoan = service.saveLoan(efectiveLoan);

        message = "Variação incluída com sucesso.";
        messageType = 2;
        return "redirect:/loan/viewList";
    }

    @GetMapping(value = "/loan/delete/{id}")
    public String delete(@PathVariable Integer id) {

        String response = service.delete(id);

        if ("SUCCESS".equalsIgnoreCase(response)) {
            message = "Variação excluída com sucesso.";
            messageType = 2;
        } else {
            message = "Erro ao excluir registro.";
            messageType = 1;
        }


        return "redirect:/loan/viewList";
    }

    @GetMapping(value = "/loan/update/{id}")
    public String update(@PathVariable Integer id,
                         @SessionAttribute("userLogged") User loggedUser) {

        String response = service.update(id, loggedUser);

        if ("SUCCESS".equalsIgnoreCase(response)) {
            message = "Liberação concluída com sucesso.";
            messageType = 2;
        } else {
            message = "Erro ao liberar registro.";
            messageType = 1;
        }


        return "redirect:/loan/viewList";
    }

    @Transactional
    @RequestMapping(value = "/updateSelectLoanType", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<LoanTypeDTO>> updateSelectLoanType(@RequestParam("valorTipoCliente") String valorTipoCliente) {
        if (valorTipoCliente.equals("APOSENTADOS")) {
            valorTipoCliente = "RETIREES";
        }
        if (valorTipoCliente.equals("SERVIDORES")) {
            valorTipoCliente = "SERVANT";
        }


        List<LoanTypeDTO> dadosAtualizados = loanTypeService.getAllLoansForCombo(valorTipoCliente);


        return ResponseEntity.ok(dadosAtualizados);
    }

    @Transactional
    @RequestMapping(value = "/updateFieldsLoanType", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<LoanType> updateFieldsLoanType(@RequestParam("valorModalidade") String valorModalidade) {


        LoanType dadosAtualizados = loanTypeService.getLoanType(Integer.parseInt(valorModalidade));


        return ResponseEntity.ok(dadosAtualizados);
    }

    @Transactional
    @RequestMapping(value = "/updateLimitLoan", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Costumer> updateLimitLoan(@RequestParam("idCliente") String idCliente) {

        Costumer dadosAtualizados = costumerService.getCostumerById(Integer.parseInt(idCliente));

        return ResponseEntity.ok(dadosAtualizados);

    }

    @Transactional
    @RequestMapping(value = "/getInstalments", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Installments>> getInstalments(
            @RequestParam("loanTerm") String loanTerm,
            @RequestParam("gracePeriod") String gracePeriod,
            @RequestParam("totalLoan") String totalLoan,
            @RequestParam("interestRate") String interestRate) {


        List<Installments> dadosAtualizados = service.getInstallments(Integer.parseInt(loanTerm), Integer.parseInt(gracePeriod), new BigDecimal(totalLoan), new BigDecimal(interestRate));

        return ResponseEntity.ok(dadosAtualizados);
    }
}
