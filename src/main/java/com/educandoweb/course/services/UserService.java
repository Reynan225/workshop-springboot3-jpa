package com.educandoweb.course.services;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.exceptions.ResourceNotFoundException;
import com.educandoweb.course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Indica que esta classe é um serviço gerenciado pelo Spring
// O Spring cria a instância automaticamente e pode injetá-la onde for necessário
@Service
public class UserService {

    // Injeta automaticamente a implementação de UserRepository criada pelo Spring
    // Mantém baixo acoplamento: UserService depende da interface, não da implementação concreta
    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        // Optional é um "envelope" que pode ou não conter um User, obrigando a tratar a ausência de valor
        Optional<User> obj;

        // Busca o usuário pelo id no repositório
        // Se o Optional estiver vazio, lança uma exceção com a mensagem "User with id ... not found"
        // Se existir, retorna o objeto contido no Optional (equivalente a obj.get())
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
    }


}
