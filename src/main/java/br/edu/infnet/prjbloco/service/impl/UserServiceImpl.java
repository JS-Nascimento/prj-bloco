package br.edu.infnet.prjbloco.service.impl;

import br.edu.infnet.prjbloco.model.domain.User;
import br.edu.infnet.prjbloco.model.repository.UserRepository;
import br.edu.infnet.prjbloco.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static br.edu.infnet.prjbloco.utils.SecurityWords.criptografar;
import static br.edu.infnet.prjbloco.utils.SecurityWords.keyGenerator;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final ModelMapper modelMapper;

    @Value("${SECRET_KEY}")
    private String secretKey;


    public UserServiceImpl(UserRepository repository, ModelMapper modelMapper, @Value("${SECRET_KEY}") String secretKey) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.secretKey = secretKey;
    }

    @Override
    public User create(User user) {
        try {
            String chave = keyGenerator();
            user.setSecretKey(chave);
            user.setSenha(criptografar(user.getSenha(), chave));

            if (user.getNivelAcesso() == null) {
                user.setNivelAcesso(2);
            } else {
                user.setNivelAcesso(user.getNivelAcesso());
            }
            user.setEmail(user.getEmail().toLowerCase().trim());

            return repository.save(user);

        } catch (Exception e) {
            //TODO Handler Exceptions
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        Iterable<User> users = repository.findAll();
        return StreamSupport.stream(users.spliterator(), false)
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
                .map(user -> modelMapper.map(user, User.class))
                .orElseThrow();
    }

    @Override
    public User update(User user, Integer id) {
        try {
            if (repository.existsById(id)) {
                User updatingUser = getUserById(id);
                updatingUser.setId(id);
                updatingUser.setNome(user.getNome());
                updatingUser.setEmail(user.getEmail());

                if (user.getNivelAcesso() != null) {
                    updatingUser.setNivelAcesso(user.getNivelAcesso());
                }

                return repository.save(updatingUser);
            }
        } catch (Exception e) {
            return new User("duplicado", null);

        }
        return null;
    }


    public User getUserByEmail(String email) {

        User user = repository.findByNomeContaining(email);

        return user;
    }

    public Boolean emailExist(String newEmail) {
        return repository.existsByEmail(newEmail);

    }

}
