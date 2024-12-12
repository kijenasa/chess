package com.kijenasa.chess.game;

import com.github.bhlangonijr.chesslib.Side;
import com.kijenasa.chess.Move.MoveWrapper;
import com.kijenasa.chess.Player.Player;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final ConcurrentHashMap<String, Sinks.Many<String>> sinks = new ConcurrentHashMap<>();

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Sinks.Many<String> getSink(UUID gameUuid) {
        return sinks.computeIfAbsent(gameUuid.toString(), k -> Sinks.many().multicast().directAllOrNothing());
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

    public void broadcastMove(UUID gameUuid, MoveWrapper move) {
        if(getGameByUuid(gameUuid).isPresent()) {
            Game game = getGameByUuid(gameUuid).get();

            getSink(gameUuid).tryEmitNext(move.toString());
        }
    }

    public boolean move(UUID gameUuid, MoveWrapper move) {
        if(getGameByUuid(gameUuid).isEmpty()) {
            return false;
        }
        Game game = getGameByUuid(gameUuid).get();

        game.setMove(move);
        gameRepository.save(game);
        broadcastMove(gameUuid, move);
        return true;
    }

    public Optional<Side> getSideByUuid(UUID gameUuid, UUID playerUuid) {
        if(getGameByUuid(gameUuid).isEmpty()) {
            return Optional.empty();
        }
        Game game = getGameByUuid(gameUuid).get();
        if(game.getWhite().getUuid() == playerUuid) {
            return Optional.of(Side.WHITE);
        } else if(game.getBlack().getUuid() == playerUuid) {
            return Optional.of(Side.BLACK);
        }
        return Optional.empty();
    }

    public Optional<Player> joinGame(UUID gameUuid) { // TODO: make it use only one db call
        if(getGameByUuid(gameUuid).isEmpty()) {
            return Optional.empty();
        }
        Game game = getGameByUuid(gameUuid).get();
        Player player;

        if(game.getWhite() == null) {
            player = new Player(UUID.randomUUID(), Side.WHITE);
            game.setWhite(player);
        } else if(game.getBlack() == null) {
            player = new Player(UUID.randomUUID(), Side.BLACK);
            game.setBlack(player);
        } else {
            return Optional.empty();
        }

        gameRepository.save(game);
        return Optional.of(player);
    }
}
