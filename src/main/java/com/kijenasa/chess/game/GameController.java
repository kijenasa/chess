package com.kijenasa.chess.game;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("api/game")
public class GameController {

    @GetMapping
    public List<Game> getGame() {
        return List.of(new Game(
                Duration.ofMinutes(15),
                Instant.now()
        ));
    }

    @PostMapping
    public void postMove() {

    }
}
