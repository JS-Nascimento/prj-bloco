package br.edu.infnet.prjbloco.service;

import br.edu.infnet.prjbloco.model.domain.Costumer;


import java.util.List;

public interface CostumerService {
    public Costumer create(Costumer costumer) ;

    public List<Costumer> getAllCostumers();

    public Costumer delete(Integer id);

    public Costumer getCostumerById(Integer id);

    public Costumer update(Costumer costumer, Integer id);
}
