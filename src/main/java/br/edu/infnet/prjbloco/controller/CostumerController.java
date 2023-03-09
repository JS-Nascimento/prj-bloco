package br.edu.infnet.prjbloco.controller;

import br.edu.infnet.prjbloco.model.domain.Costumer;

import br.edu.infnet.prjbloco.model.domain.enuns.TipoCliente;
import br.edu.infnet.prjbloco.service.CostumerService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;


@Controller
public class CostumerController {

    private CostumerService service;
    public CostumerController(CostumerService service){
        this.service = service;
    }

    private String message;

    @GetMapping(value = "/costumer")
    public String callFormRegister () {
        return "costumer/register";
    }

    @GetMapping(value = "/costumer/viewList")
    public String callViewList ( Model model) {

        model.addAttribute( "costumers", service.getAllCostumers() );
        model.addAttribute("tiposClientes", TipoCliente.values());
        model.addAttribute( "message", message );
        message = null;

        return "costumer/viewList";
    }
    @GetMapping(value = "/costumer/update/{id}")
    public String getCostumer ( Model model, @PathVariable Integer id) {
        Costumer costumer = service.getCostumerById(id);
        model.addAttribute( "costumer", costumer );
        model.addAttribute("tiposClientes", TipoCliente.values());
        model.addAttribute( "message", message );
        message = null;

        return "costumer/update";
    }
    @PostMapping(value = "/costumer/create")
    public String create( Costumer costumer ){
        System.out.println(costumer.getTipoCliente());

        Costumer newCostumer = service.create(costumer);

        message = "Solicitante <strong>"+ newCostumer.getId() + " - " + newCostumer.getNome() + "</strong> incluído com sucesso.";

        return "redirect:/costumer/viewList";
    }
    @PostMapping(value = "/costumer/save/{id}")
    public String update(Costumer costumer, @PathVariable Integer id){


        Costumer updatedCostumer = service.update(costumer, id);

        message = "Solicitante <strong>"+ updatedCostumer.getId() + " - " + updatedCostumer.getNome() + "</strong> atualizado com sucesso.";

        return "redirect:/costumer/viewList";
    }

    @GetMapping( value = "/costumer/delete/{id}")
    public String delete(@PathVariable Integer id ){

        Costumer costumer = service.delete( id );

        message = "Solicitante <strong>" + costumer.getNome() + "</strong> excluído com sucesso.";

        return "redirect:/costumer/viewList";
    }

}
