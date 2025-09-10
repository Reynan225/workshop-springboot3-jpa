package com.educandoweb.course.config;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration // Marca a classe como configuração do Spring
@Profile("test") // Só ativa essa configuração quando o perfil "test" estiver ativo
public class TestConfig implements CommandLineRunner {

    @Autowired // Injeta automaticamente a implementação de UserRepository
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        // Salva todos os usuários no banco H2 em memória
        userRepository.saveAll(Arrays.asList(u1, u2));
        // Quando a aplicação sobe, esses dados já existem automaticamente
    }
}
