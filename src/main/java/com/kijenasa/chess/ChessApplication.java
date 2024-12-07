package com.kijenasa.chess;

import com.kijenasa.chess.game.Game;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@SpringBootApplication
@RestController
public class  ChessApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChessApplication.class, args);
	}

	@GetMapping
	public List<Game> hello() {
		Duration dur = Duration.ofDays(1);
		return List.of(new Game(
				Duration.ofMinutes(15),
				Instant.now()
		));
	}
}
