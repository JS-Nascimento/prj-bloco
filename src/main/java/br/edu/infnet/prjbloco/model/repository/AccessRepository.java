package br.edu.infnet.prjbloco.model.repository;

import br.edu.infnet.prjbloco.model.domain.User;

public class AccessRepository {
    public static User authenticate (User user) {
        if (user.getEmail().equalsIgnoreCase( user.getSenha() )){
            return new User("Admin", user.getEmail(), user.getSenha());
        }

        return null;
    }
}
