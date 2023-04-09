package br.edu.infnet.prjbloco.service;

import br.edu.infnet.prjbloco.model.domain.User;

import java.util.List;


public interface UserService {
    User create(User user);

    List<User> getAllUsers();

    User delete(Integer id);

    User getUserById(Integer id);

    User update(User user, Integer id);

    User getUserByEmail(String email);

    Boolean emailExist(String newEmail);
}
