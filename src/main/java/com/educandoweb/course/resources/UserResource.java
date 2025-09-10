package com.educandoweb.course.resources;

import com.educandoweb.course.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

// Indica que esta classe é um REST Controller, ou seja, ela vai tratar requisições HTTP e retornar JSON
@RestController
// Define a rota base da classe; todas as requisições começarão com /users
@RequestMapping(value = "/users")
public class UserResource {
    // Indica que este método responde a requisições GET em /users
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "Maria", "maria@gmail.com", "999999", "12345"));
        users.add(new User(2L, "João", "joao@gmail.com", "888888", "54321"));

        // Retorna a lista de usuários com status HTTP 200 (OK)
        return ResponseEntity.ok().body(users);
    }


}
