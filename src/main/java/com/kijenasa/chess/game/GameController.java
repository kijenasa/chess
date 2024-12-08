package com.kijenasa.chess.game;

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

//    @GetMapping("/id/{id}")
//    public Optional<Game> getGame(@PathVariable long id) {
//        return gameService.getGameById(id);
//    }

    @GetMapping("/{uuid}")
    public Optional<Game> getId(@PathVariable UUID uuid) {
        return gameService.getGameByUuid(uuid);
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

