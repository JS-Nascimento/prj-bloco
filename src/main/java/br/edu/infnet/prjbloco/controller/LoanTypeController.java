package br.edu.infnet.prjbloco.controller;

import br.edu.infnet.prjbloco.model.domain.FgtsLoan;
import br.edu.infnet.prjbloco.model.domain.RetireesLoan;
import br.edu.infnet.prjbloco.model.domain.ServantLoan;
import br.edu.infnet.prjbloco.model.domain.User;
import br.edu.infnet.prjbloco.service.LoanTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static br.edu.infnet.prjbloco.model.domain.enuns.LoanPurpose.*;

@Controller
public class LoanTypeController {

    private final LoanTypeService service;
    private String message;
    private Integer messageType;
    public LoanTypeController(LoanTypeService service) {
        this.service = service;
    }

    @GetMapping(value = "/loantype")
    public String callFormRegister(Model model) {
        model.addAttribute("message", message);
        model.addAttribute("messageType", messageType);
        message = null;
        messageType = 2;
        return "loantype/register";
    }

    @GetMapping(value = "/loantype/viewList")
    public String callViewList(Model model, String filtro) {

        filtro = filtro == null ? "ALL" : filtro;

        model.addAttribute("loans", service.getAllLoans(filtro));
        model.addAttribute("loanPurposes", br.edu.infnet.prjbloco.model.domain.enuns.LoanPurpose.values());
        model.addAttribute("message", message);
        model.addAttribute("messageType", messageType);

        message = null;
        messageType = 2;

        return "loantype/viewList";
    }

    @PostMapping(value = "/loantypefiltered")
    public String callforFilter(Model model, @RequestParam("filtro") String filtro) {

        return callViewList(model, filtro);
    }

    @PostMapping(value = "/loantype/create")
    public String create(Model model,
                         @RequestParam("loanPurpose") String loanPurpose,
                         @RequestParam("name") String name,
                         @RequestParam("gracePeriod") Integer gracePeriod,
                         @RequestParam("interestRate") BigDecimal interestRate,
                         @RequestParam("loanTerm") Integer loanTerm,
                         @SessionAttribute("userLogged") User loggedUser) {


        if (loanPurpose.equals(RETIREES.getName())) {
            RetireesLoan retireesLoan = new RetireesLoan(null, RETIREES, name, gracePeriod, interestRate, loanTerm);
            retireesLoan.setUser(loggedUser);
            message = service.validateRetireesLoan(retireesLoan);
            if (!"OK".equalsIgnoreCase(message)) {
                messageType = 1;
                model.addAttribute("loanType", retireesLoan);
                return "redirect:/loantype";
            }
            RetireesLoan newRetireesLoan = service.saveLoan(retireesLoan);
            message = "Variação incluída com sucesso.";
            messageType = 2;

        }

        if (loanPurpose.equals(FGTS.getName())) {
            FgtsLoan fgtsLoan = new FgtsLoan(null, FGTS, name, gracePeriod, interestRate, loanTerm);
            fgtsLoan.setUser(loggedUser);
            message = service.validateFGTSLoan(fgtsLoan);
            if (!"OK".equalsIgnoreCase(message)) {
                messageType = 1;
                model.addAttribute("loanType", fgtsLoan);
                return "redirect:/loantype";
            }
            FgtsLoan newFgtsLoan = service.saveLoan(fgtsLoan);
            message = "Variação incluída com sucesso.";
            messageType = 2;

        }

        if (loanPurpose.equals(SERVANT.getName())) {
            ServantLoan servantLoan = new ServantLoan(null, SERVANT, name, gracePeriod, interestRate, loanTerm);
            servantLoan.setUser(loggedUser);
            message = service.validateServantLoan(servantLoan);
            if (!"OK".equalsIgnoreCase(message)) {
                messageType = 1;
                model.addAttribute("loanType", servantLoan);
                return "redirect:/loantype";
            }
            ServantLoan newServantLoan = service.saveLoan(servantLoan);
            message = "Variação incluída com sucesso.";
            messageType = 2;

        }
        return "redirect:/loantype/viewList";
    }

    @GetMapping(value = "/loantype/delete/{id}")
    public String delete(@PathVariable Integer id) {

        String response = service.delete(id);

        if ("SUCCESS".equalsIgnoreCase(response)) {
            message = "Variação excluída com sucesso.";
            messageType = 2;
        } else {
            message = "Erro ao excluir registro.";
            messageType = 1;
        }


        return "redirect:/loantype/viewList";
    }
}
