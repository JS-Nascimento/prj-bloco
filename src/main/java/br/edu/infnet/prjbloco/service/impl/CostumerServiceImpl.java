package br.edu.infnet.prjbloco.service.impl;

import br.edu.infnet.prjbloco.model.domain.Costumer;
import br.edu.infnet.prjbloco.model.domain.User;
import br.edu.infnet.prjbloco.model.domain.enuns.TipoCliente;
import br.edu.infnet.prjbloco.model.repository.CostumerRepository;
import br.edu.infnet.prjbloco.service.CostumerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CostumerServiceImpl implements CostumerService {
    private ModelMapper modelMapper;
    private CostumerRepository repository;
    public CostumerServiceImpl(ModelMapper modelMapper, CostumerRepository repository) {
        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    @Override
    public Costumer create(Costumer costumer) {
        try {
            return repository.save(costumer);
        } catch (Exception e) {
            //TO DO Handler Exceptions
            return null;
        }
    }

    @Override
    public List<Costumer> getAllCostumers() {
        Iterable<Costumer> costumers = repository.findAll();
        return StreamSupport.stream(costumers.spliterator(),false)
                .collect(Collectors.toList());
    }

    @Override
    public Costumer delete(Integer id) {
        Optional<Costumer> costumerOptional = repository.findById(id);
        if (costumerOptional.isPresent()) {
            Costumer deletedCostumer = costumerOptional.get();
            repository.deleteById(id);
            return deletedCostumer;
        } else {
            return null;
        }
    }

    @Override
    public Costumer getCostumerById(Integer id) {
        return repository.findById(id)
                .map( costumer -> modelMapper.map( costumer , Costumer.class ) )
                .orElseThrow();
    }

    @Override
    public Costumer update(Costumer costumer, Integer id) {
        if(repository.existsById(id)){
            Costumer updatingCostumer = getCostumerById(id);
            updatingCostumer.setId(id);
            updatingCostumer.setNome(costumer.getNome());
            updatingCostumer.setCpf(costumer.getCpf());
            updatingCostumer.setIdade(costumer.getIdade());
            updatingCostumer.setRecompra(costumer.getRecompra());
            updatingCostumer.setScore(costumer.getScore());
            updatingCostumer.setLimiteDisponivel(costumer.getLimiteDisponivel());
            updatingCostumer.setRendaTotal(costumer.getRendaTotal());
            updatingCostumer.setLimiteTotal(costumer.getLimiteTotal());
            updatingCostumer.setSaldoFgts(costumer.getSaldoFgts());
            updatingCostumer.setTipoCliente(costumer.getTipoCliente());
            return repository.save(updatingCostumer);
        }
        return null;
    }
}
