package com.example.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDataBase {

    private static final Logger log = LoggerFactory.getLogger(LoadDataBase.class);
    @Bean
    CommandLineRunner initDatabase(GameRepository repository) {

        return args -> {
//            log.info("Preloading " + repository.save(new Player("John")));
//            log.info("Preloading " + repository.save(new Player("Peter")));
//            log.info("Preloading " + repository.save(new Player("Sam")));
//            log.info("Adding player " + repository.findAll().get(0).addPlayer(new Player("John")));

        };
    }

}
