package com.educandoweb.course.repositories;

import com.educandoweb.course.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

// Interface de repositório que manipula objetos User
// Extende JpaRepository para herdar métodos prontos de CRUD
// Long é o tipo da chave primária (id)

//Aque a gente poderia chamar um @Service, porém não precisa pois o JpaRepository já é um Service
public interface UserRepository extends JpaRepository<User, Long> {
    // Não precisa implementar nada, métodos como save, findAll, findById já vêm prontos
}
