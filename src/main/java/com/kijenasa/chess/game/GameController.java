package com.kijenasa.chess.game;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.game.GameContext;
import com.kijenasa.chess.Move.MoveWrapper;
import com.kijenasa.chess.Player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/game")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/{uuid}")
    public Optional<Game> getId(@PathVariable UUID uuid) {
        return gameService.getGameByUuid(uuid);
    }

    @PostMapping("/{gameUuid}/move")
    @ResponseBody
    public boolean makeMove(@PathVariable UUID gameUuid, @RequestParam UUID playerUuid, @RequestBody MoveWrapper move) {
        return gameService.move(gameUuid, playerUuid, move);
    }

    @GetMapping("/{gameUuid}/events")
    public Flux<String> gameEvents(@PathVariable UUID gameUuid) {
        return gameService.getSink(gameUuid).asFlux();
    }

    @PostMapping("/{gameUuid}/join")
    public Optional<Player> joinGame(@PathVariable UUID gameUuid) {
        return gameService.joinGame(gameUuid);
    }

    @PostMapping
    public ResponseEntity<Void> createGame() {
        Game game = new Game();
        String redirect = "/game/" + game.getUuid().toString();
        gameService.addNewGame(game);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(URI.create(redirect))
                .build();
    }
}

