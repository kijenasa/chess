package com.kijenasa.chess.game;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getGames() {
        return gameRepository.findAll();
    }

    public Optional<Game> getGameById(long id) {
        return gameRepository.findGameById(id);
    }

    public Optional<Game> getGameByUuid(UUID uuid) {
        return gameRepository.findGameByUuid(uuid);
    }

    public void addNewGame(Game game) {
        gameRepository.save(game);
    }
}
