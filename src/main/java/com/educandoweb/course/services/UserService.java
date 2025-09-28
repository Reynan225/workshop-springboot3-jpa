package com.educandoweb.course.services;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;
import com.educandoweb.course.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);

        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public User update(Long id, User obj) {

        try {
            User entity = repository.getReferenceById(id);
            updateData(entity, obj);

            return repository.save(entity);
        } catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }
}