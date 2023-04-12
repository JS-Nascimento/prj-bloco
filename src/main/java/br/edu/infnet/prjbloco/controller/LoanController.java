package br.edu.infnet.prjbloco.controller;

import br.edu.infnet.prjbloco.DTO.LoanTypeDTO;
import br.edu.infnet.prjbloco.model.domain.Loan;
import br.edu.infnet.prjbloco.model.domain.LoanType;
import br.edu.infnet.prjbloco.model.domain.User;
import br.edu.infnet.prjbloco.service.CostumerService;
import br.edu.infnet.prjbloco.service.LoanService;
import br.edu.infnet.prjbloco.service.LoanTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
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
        model.addAttribute("message", message);
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
    public String create(Model model, Loan efectiveLoan,
                         @SessionAttribute("userLogged") User loggedUser) {

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

        // Suponhamos que você obtenha os dados atualizados em uma variável chamada "dadosAtualizados"
        List<LoanTypeDTO> dadosAtualizados = loanTypeService.getAllLoansForCombo(valorTipoCliente);

        // Retorne os dados atualizados como uma resposta HTTP com o código de status 200 (OK)
        return ResponseEntity.ok(dadosAtualizados);
    }

    @Transactional
    @RequestMapping(value = "/updateFieldsLoanType", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<LoanType> updateFieldsLoanType(@RequestParam("valorModalidade") String valorModalidade) {

        // Suponhamos que você obtenha os dados atualizados em uma variável chamada "dadosAtualizados"
        LoanType dadosAtualizados = loanTypeService.getLoanType(Integer.parseInt(valorModalidade));

        // Retorne os dados atualizados como uma resposta HTTP com o código de status 200 (OK)
        return ResponseEntity.ok(dadosAtualizados);
    }
}
