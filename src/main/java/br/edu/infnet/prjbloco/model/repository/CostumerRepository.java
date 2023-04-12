package br.edu.infnet.prjbloco.model.repository;

import br.edu.infnet.prjbloco.model.domain.Costumer;
import br.edu.infnet.prjbloco.model.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CostumerRepository extends CrudRepository<Costumer, Integer> {

    List<Costumer> findAllByUserOrderByNomeAsc(User user);

}
