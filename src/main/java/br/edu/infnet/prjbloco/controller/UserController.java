package br.edu.infnet.prjbloco.controller;

import br.edu.infnet.prjbloco.model.domain.User;
import br.edu.infnet.prjbloco.model.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class UserController {

    private String message;

    @GetMapping(value = "/user")
    public String callFormRegister () {
        return "user/register";
    }

    @GetMapping(value = "/user/viewList")
    public String callViewList ( Model model) {

        model.addAttribute( "users", UserRepository.getViewList() );
        model.addAttribute( "message", message );
        message = null;

        return "user/viewList";
    }

    @PostMapping(value = "/user/create")
    public String create( User user ){

        UserRepository.create( user );

        message = "Usuário <strong>"+ user.getNome() + "</strong> incluído com sucesso.";

        return "redirect:/user/viewList";
    }

    @GetMapping( value = "/user/delete/{id}")
    public String delete(@PathVariable Integer id ){

        User user = UserRepository.delete( id );

        message = "Usuário <strong>" + user.getNome() + "</strong> excluído com sucesso.";

        return "redirect:/user/viewList";
    }

}
