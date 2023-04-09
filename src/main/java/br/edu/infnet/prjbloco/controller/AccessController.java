package br.edu.infnet.prjbloco.controller;

import br.edu.infnet.prjbloco.model.domain.User;
import br.edu.infnet.prjbloco.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;


@Controller
@SessionAttributes("userLogged")
public class AccessController {
    @Autowired
    private LoginService loginService;


    @GetMapping(value = "/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping(value = "/login")
    public String login(Model model, @RequestParam String email, @RequestParam String senha) throws Exception {

        User user = new User(email, senha);

        User loggedUser = loginService.authenticate(user);

        if (loggedUser != null) {
            model.addAttribute("userLogged", loggedUser);
            return "redirect:/home";
        }
        model.addAttribute("message", "As credenciais para o usuário <strong>" + user.getEmail() + "</strong> são inválida.");
        model.addAttribute("messageType", 1);
        return loginForm();
    }

    @GetMapping(value = "/logout")
    public String logout(HttpSession session, SessionStatus status) {
        status.setComplete();
        session.removeAttribute("userLogged");
        return "redirect:/";
    }
}
