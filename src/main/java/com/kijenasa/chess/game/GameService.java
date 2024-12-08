package com.kijenasa.chess.game;

import org.springframework.stereotype.Service;

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

    public void addNewGame(Game game) {
        gameRepository.save(game);
    }
}
