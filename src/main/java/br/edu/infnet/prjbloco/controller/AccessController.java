package br.edu.infnet.prjbloco.controller;

import br.edu.infnet.prjbloco.model.domain.User;
import br.edu.infnet.prjbloco.model.repository.AccessRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AccessController {
    @GetMapping(value = "/login")
    public String loginForm(){
        return "login";
    }

    @PostMapping(value = "/login")
    public String login(@RequestParam String email, @RequestParam String senha){

        User user = new User(email,senha);

        if(AccessRepository.authenticate( user ) != null){
            return "redirect:/home";
        }
        return "redirect:/login";
    }

}
