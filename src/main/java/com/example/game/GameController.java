package com.example.game;

import java.util.List;
import java.util.Objects;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@RestController
public class GameController {

    private final GameRepository repository;
    private Long nextId;
    private Integer playerCounter = 1;
    private Integer order;

    GameController(GameRepository repository) {
        this.repository = repository;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/game")
    List<Player> getPlayers() {
        return repository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/game/next")
    Player getNext() {
        List<Player> players = repository.findAll();
        for (Player player : players) {
            if (player.getNum().equals(order)) return player;
        }
        throw new PlayerNotFoundException(0L);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/game/win")
    Player getWinner() {
        List<Player> players = repository.findAll();
        Player winner = players.get(0);
        for (Player player : players) {
            if (player.getScore() > winner.getScore()) winner = player;
        }
        return winner;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/game")
    Player addPlayer(@RequestBody Player player) {
        player.setScore(0);
        player.setNum(playerCounter);
        playerCounter++;
        return repository.save(player);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/game/move")
    Player makeMove(@RequestBody Integer score) {
        Integer nextNum = order;
        if (order < playerCounter - 1) {
            order++;
        } else {
            order = 1;
        }
        List<Player> players = repository.findAll();
        for (Player player : players) {
            if (player.getNum().equals(nextNum)) {
                player.addScore(score);
                return repository.save(player);
            }
        }
        throw new PlayerNotFoundException(0L);
//        Long id = nextId;
//        if (nextId < repository.count()) {
//            nextId++;
//        } else {
//            nextId = 1L;
//        }
//        return repository.findById(id)
//                .map(player -> {
//                    player.addScore(score);
//                    return repository.save(player);
//                })
//                .orElseThrow(() -> new PlayerNotFoundException(id));
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping("/game/start")
    List<Player> startGame(@RequestBody Long id) {
        order = 1;
        List<Player> players = repository.findAll();
        for (Player player : players) {
            player.setScore(0);
            repository.save(player);
        }
        return players;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping("/game/{id}")
    List<Player> deletePlayer(@PathVariable Long id) {
        repository.deleteById(id);
        List<Player> players = repository.findAll();
        for (Player player : players) {
            if (player.getId() > id) {
                Integer newNum = player.getNum() - 1;
                player.setNum(newNum);
                repository.save(player);
            }
        }
        playerCounter--;
        return repository.findAll();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }

}
