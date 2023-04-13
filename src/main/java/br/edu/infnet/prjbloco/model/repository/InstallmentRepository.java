package br.edu.infnet.prjbloco.model.repository;

import br.edu.infnet.prjbloco.model.domain.Installments;
import org.springframework.data.repository.CrudRepository;

public interface InstallmentRepository extends CrudRepository<Installments, Integer> {


}
