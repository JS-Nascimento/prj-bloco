package br.edu.infnet.prjbloco.controller;

import br.edu.infnet.prjbloco.model.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

    @GetMapping(value = "/user")
    public String callFormRegister () {
        return "user/register";
    }

    @GetMapping(value = "/user/viewList")
    public String callViewList () {
        return "user/viewList";
    }
    @PostMapping(value = "/user/create")
    public String create( User user ){
        System.out.println("Inclusão" + user);
        return "redirect:/user/viewList";
    }

}
