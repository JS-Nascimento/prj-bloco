package br.edu.infnet.prjbloco.service.impl;

import br.edu.infnet.prjbloco.model.domain.User;
import br.edu.infnet.prjbloco.service.LoginService;
import br.edu.infnet.prjbloco.service.UserService;
import org.springframework.stereotype.Service;

import static br.edu.infnet.prjbloco.utils.SecurityWords.descriptografar;


@Service
public class LoginServiceImpl implements LoginService {
    private final UserService userService;

    public LoginServiceImpl(UserService userService) {
        this.userService = userService;
    }

    public User authenticate(User user) throws Exception {
        User loggedUser = user;

        User foundedUser = userService.getUserByEmail(loggedUser.getEmail());
        if (foundedUser != null) {
            foundedUser.setSenha(descriptografar(foundedUser.getSenha(), foundedUser.getSecretKey()));
            if (foundedUser.getSenha().equals(loggedUser.getSenha())) {
                return foundedUser;
            }
        }
        return null;


    }


}
