package com.kijenasa.chess.web;

import com.kijenasa.chess.game.Game;
import com.kijenasa.chess.game.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.UUID;

@Controller
public class WebController {
    private final GameService gameService;

    @Autowired
    public WebController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/game/{uuid}")
    public String game(@PathVariable UUID uuid, Model model) {
        Optional<Game> game =  gameService.getGameByUuid(uuid);
        game.ifPresent(value -> model.addAttribute("fen", value.getBoard().getFen()));
        return "game";
    }
}
