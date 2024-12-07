package com.kijenasa.chess;

import com.kijenasa.chess.game.Game;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@SpringBootApplication
public class  ChessApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChessApplication.class, args);
	}
}
