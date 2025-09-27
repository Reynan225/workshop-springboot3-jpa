package com.educandoweb.course.services;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.exceptions.ResourceNotFoundException;
import com.educandoweb.course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

    }

    public User insert(User obj) {
        return repository.save(obj);
    }

    public void deleted(Long id) {
        repository.deleteById(id);
    }

    public User update(Long id, User obj) {

        User entity = repository.getReferenceById(id);
        updateData(entity, obj);
        
        return repository.save(entity);
    }

    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }
}