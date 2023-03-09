package br.edu.infnet.prjbloco.model.repository;

import br.edu.infnet.prjbloco.model.domain.Costumer;
import br.edu.infnet.prjbloco.model.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Repository
public interface CostumerRepository extends CrudRepository<Costumer, Integer> {

}
