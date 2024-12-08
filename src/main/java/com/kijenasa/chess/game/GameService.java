package com.kijenasa.chess.game;

import com.github.bhlangonijr.chesslib.Board;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getGame() {
        return gameRepository.findAll();
    }
}
