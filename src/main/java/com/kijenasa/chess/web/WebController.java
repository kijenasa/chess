package com.kijenasa.chess.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class WebController {
    @GetMapping("/game/{uuid}")
    public String game(@RequestParam(required = true) UUID uuid) {
        return "Hello, " + uuid.toString();
    }
}
