package com.educandoweb.course.resources;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

// Indica que esta classe é um REST Controller, ou seja, ela vai tratar requisições HTTP e retornar JSON
@RestController
// Define a rota base da classe; todas as requisições começarão com /users
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    // Indica que este método responde a requisições GET em /users
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
       List<User> users = service.findAll();

        return ResponseEntity.ok().body(users);
    }

    // Endpoint GET /users/{id} → retorna um usuário específico pelo id
    @GetMapping(value = "/{id}")
    // @PathVariable captura o valor do {id} na URL e passa para o método
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User obj = service.findById(id);

        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj) {
        // @RequestBody indica que o Spring deve converter o JSON recebido no corpo da requisição
        // para um objeto User automaticamente

        obj = service.insert(obj);
        // Chama o serviço para inserir o usuário no banco e retorna o objeto persistido

        /*
        (status 201 Created) que você envie junto um Location header
         apontando para onde o recurso recém-criado pode ser acessado.
         */
         URI uri = ServletUriComponentsBuilder.fromCurrentRequest() // Pega a URL atual do endpoint
                 .path("/{id}").  // Adiciona "/{id}" à URL atual
                 buildAndExpand(obj.getId())// Substitui "{id}" pelo id do objeto recém-criado
                 .toUri(); // Converte para URI, que será usada no header Location

        return ResponseEntity.created(uri).body(obj);
    }
}
