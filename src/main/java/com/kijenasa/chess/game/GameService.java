package com.kijenasa.chess.game;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class GameService {

    public List<Game> getGame() {
        return List.of(new Game(
                Duration.ofMinutes(15),
                Instant.now()
        ));
    }
}
