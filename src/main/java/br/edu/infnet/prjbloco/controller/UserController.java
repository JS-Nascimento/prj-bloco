package br.edu.infnet.prjbloco.controller;

import br.edu.infnet.prjbloco.model.domain.User;
import br.edu.infnet.prjbloco.model.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class UserController {

    @GetMapping(value = "/user")
    public String callFormRegister () {
        return "user/register";
    }

    @GetMapping(value = "/user/viewList")
    public String callViewList () {

        List<User> list = UserRepository.getViewList();
        System.out.println("Qtd de Usuários : " + list.size());

        for(User user : list){
            System.out.printf("%S - %s\n", user.getNome(), user.getEmail());
        }

        return "user/viewList";
    }

    @PostMapping(value = "/user/create")
    public String create( User user ){

        UserRepository.create( user );

        return "redirect:/user/viewList";
    }

}
