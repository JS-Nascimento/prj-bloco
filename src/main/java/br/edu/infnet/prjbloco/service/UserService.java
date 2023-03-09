package br.edu.infnet.prjbloco.service;

import br.edu.infnet.prjbloco.model.domain.User;
import br.edu.infnet.prjbloco.service.impl.UserServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    public User create(User user) ;

    public List<User> getAllUsers();

    public User delete(Integer id);

    public User getUserById(Integer id);

    public User update(User user, Integer id);
}
