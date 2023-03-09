package br.edu.infnet.prjbloco.service.impl;

import br.edu.infnet.prjbloco.model.domain.User;
import br.edu.infnet.prjbloco.model.repository.UserRepository;
import br.edu.infnet.prjbloco.service.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
public class UserServiceImpl implements UserService {
    private UserRepository repository;
    private ModelMapper modelMapper;
    public UserServiceImpl(UserRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public User create(User user) {
        try {
            return repository.save(user);
        } catch (Exception e) {
            //TO DO Handler Exceptions
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
       Iterable<User> users = repository.findAll();
       return StreamSupport.stream(users.spliterator(),false)
               .collect(Collectors.toList());
    }

    @Override
    public User delete(Integer id) {
        Optional<User> userOptional = repository.findById(id);
        if (userOptional.isPresent()) {
            User deletedUser = userOptional.get();
            repository.deleteById(id);
            return deletedUser;
        } else {
            return null;
        }


    }

    @Override
    public User getUserById(Integer id) {
        return repository.findById(id)
                .map( user -> modelMapper.map( user , User.class ) )
                .orElseThrow();
    }

    @Override
    public User update(User user, Integer id) {
        if(repository.existsById(id)){
            User updatingUser = getUserById(id);
            updatingUser.setId(id);
            updatingUser.setNome(user.getNome());
            updatingUser.setSenha(user.getSenha());
            updatingUser.setEmail(user.getEmail());
            return repository.save(updatingUser);
        }
        return null;
    }
}
