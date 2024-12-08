package com.kijenasa.chess.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/game")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public List<Game> getGame() {
        return gameService.getGame();
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

