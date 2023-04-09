package br.edu.infnet.prjbloco.controller;

import br.edu.infnet.prjbloco.model.domain.User;
import br.edu.infnet.prjbloco.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.sql.SQLIntegrityConstraintViolationException;


@Controller
public class UserController {

    private final UserService service;

    private Integer messageType;

    private String message;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping(value = "/user")
    public String callFormRegister(Model model) {
        model.addAttribute("message", message);
        model.addAttribute("messageType", messageType);

        message = null;
        messageType = 2;

        return "user/register";
    }

    @GetMapping(value = "/user/viewList")
    public String callViewList ( Model model) {
        model.addAttribute("users", service.getAllUsers());
        model.addAttribute("message", message);
        model.addAttribute("messageType", messageType);

        message = null;
        messageType = 2;

        return "user/viewList";
    }
    @GetMapping(value = "/user/update/{id}")
    public String getUser ( Model model, @PathVariable Integer id) {

        model.addAttribute("usr", service.getUserById(id));
        model.addAttribute("message", message);
        model.addAttribute("messageType", messageType);

        message = null;
        messageType = 2;

        return "user/update";
    }

    @PostMapping(value = "/user/create")
    public String create(Model model, User usr, HttpSession session) {

        if (service.emailExist(usr.getEmail().toLowerCase())) {

            message = "<strong> Email já cadastrado em nossa base.</strong>";
            messageType = 1;
            return "redirect:/user";


        } else {

            User newUser = service.create(usr);
            message = "Usuário <strong>" + newUser.getId() + " - " + newUser.getNome() + "</strong> incluído com sucesso. pelo Usuario";
            messageType = 2;
        }

        try {
            User loggedUser = session.getAttribute("userLogged") != null ? (User) session.getAttribute("userLogged") : null;

            if (loggedUser != null) {

                return "redirect:/user/viewList";
            }
        } catch (RuntimeException e) {

            return "redirect:/login";

        }
        return "redirect:/login";

    }

    @PostMapping(value = "/user/save/{id}")
    public String update(Model model, User usr, @PathVariable Integer id) throws SQLIntegrityConstraintViolationException {

        User updatedUser = service.update(usr, id);
        if (updatedUser.getEmail().equals("duplicado")) {
            message = "<strong> email já cadastrado </strong>";
            messageType = 1;
            return getUser(model, usr.getId());
        }

        message = "Usuário <strong>" + updatedUser.getId() + " - " + updatedUser.getNome() + "</strong> atualizado com sucesso.";
        messageType = 2;

        return "redirect:/user/viewList";


    }

    @GetMapping(value = "/user/delete/{id}")
    public String delete(@PathVariable Integer id) {

        User deleteUser = service.delete(id);

        message = "Usuário <strong>" + deleteUser.getNome() + "</strong> excluído com sucesso.";
        messageType = 2;

        return "redirect:/user/viewList";
    }

}
