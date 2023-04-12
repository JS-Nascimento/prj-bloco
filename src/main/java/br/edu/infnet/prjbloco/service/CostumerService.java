package br.edu.infnet.prjbloco.service;

import br.edu.infnet.prjbloco.model.domain.Costumer;
import br.edu.infnet.prjbloco.model.domain.User;

import java.util.List;

public interface CostumerService {
    Costumer create(Costumer costumer) ;

    List<Costumer> getAllCostumers(User user);

    Costumer delete(Integer id);

    Costumer getCostumerById(Integer id);

    Costumer update(Costumer costumer, Integer id);
}
