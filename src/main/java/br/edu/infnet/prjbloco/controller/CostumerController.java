package br.edu.infnet.prjbloco.controller;

import br.edu.infnet.prjbloco.model.domain.Costumer;
import br.edu.infnet.prjbloco.model.domain.User;
import br.edu.infnet.prjbloco.model.repository.CostumerRepository;
import br.edu.infnet.prjbloco.model.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class CostumerController {

    private String message;

    @GetMapping(value = "/costumer")
    public String callFormRegister () {
        return "costumer/register";
    }

    @GetMapping(value = "/costumer/viewList")
    public String callViewList ( Model model) {

        model.addAttribute( "costumers", CostumerRepository.getViewList() );
        model.addAttribute( "message", message );
        message = null;

        return "costumer/viewList";
    }

    @PostMapping(value = "/costumer/create")
    public String create( Costumer costumer ){

        CostumerRepository.create( costumer );

        message = "Solicitante <strong>"+ costumer.getNome() + "</strong> incluído com sucesso.";

        return "redirect:/costumer/viewList";
    }

    @GetMapping( value = "/costumer/delete/{id}")
    public String delete(@PathVariable Integer id ){

        Costumer costumer = CostumerRepository.delete( id );

        message = "Solicitante <strong>" + costumer.getNome() + "</strong> excluído com sucesso.";

        return "redirect:/costumer/viewList";
    }

}
