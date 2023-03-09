package br.edu.infnet.prjbloco.controller;

import br.edu.infnet.prjbloco.model.domain.User;
import br.edu.infnet.prjbloco.model.repository.UserRepository;
import br.edu.infnet.prjbloco.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class UserController {

    private UserService service;
    public UserController(UserService service){
        this.service = service;
    }

    private String message;

    @GetMapping(value = "/user")
    public String callFormRegister () {
        return "user/register";
    }

    @GetMapping(value = "/user/viewList")
    public String callViewList ( Model model) {

        model.addAttribute( "users", service.getAllUsers() );
        model.addAttribute( "message", message );
        message = null;

        return "user/viewList";
    }
    @GetMapping(value = "/user/update/{id}")
    public String getUser ( Model model, @PathVariable Integer id) {

        model.addAttribute( "user", service.getUserById(id) );
        model.addAttribute( "message", message );
        message = null;

        return "user/update";
    }

    @PostMapping(value = "/user/create")
    public String create( User user ){
        User newUser = service.create( user );

        message = "Usuário <strong>"+ newUser.getId() + " - " + newUser.getNome() + "</strong> incluído com sucesso.";

        return "redirect:/user/viewList";
    }

    @PostMapping(value = "/user/save/{id}")
    public String update( User user, @PathVariable Integer id ){
        User updatedUser = service.update( user, id );

        message = "Usuário <strong>"+ updatedUser.getId() + " - " + updatedUser.getNome() + "</strong> atualizado com sucesso.";

        return "redirect:/user/viewList";
    }

    @GetMapping( value = "/user/delete/{id}")
    public String delete(@PathVariable Integer id ){

        User user = service.delete( id );

        message = "Usuário <strong>" + user.getNome() + "</strong> excluído com sucesso.";

        return "redirect:/user/viewList";
    }

}
