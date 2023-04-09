package br.edu.infnet.prjbloco.model.repository;

import br.edu.infnet.prjbloco.model.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface AccessRepository extends CrudRepository<User, Integer> {

}
