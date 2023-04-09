package br.edu.infnet.prjbloco.service;

import br.edu.infnet.prjbloco.model.domain.User;

public interface LoginService {

    User authenticate(User user) throws Exception;
}
