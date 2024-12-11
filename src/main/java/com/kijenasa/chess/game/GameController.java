package com.kijenasa.chess.game;

import com.github.bhlangonijr.chesslib.Side;
import com.github.bhlangonijr.chesslib.move.Move;
import com.kijenasa.chess.Player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{gameUuid}")
    @ResponseBody
    public void makeMove(@PathVariable UUID gameUuid, @RequestParam UUID playerUuid) { // TODO: take in the move as a request param :p
        System.out.println("move");
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

