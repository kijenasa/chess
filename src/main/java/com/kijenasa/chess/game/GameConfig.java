package com.kijenasa.chess.game;

import com.github.bhlangonijr.chesslib.Board;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class GameConfig {

    @Bean
    CommandLineRunner commandLineRunner(GameRepository repository) {
        return args -> {
            Game a = new Game(
                    new Board()
            );
            Game b = new Game(
                    new Board()
            );
            Game c = new Game(
                    new Board()
            );

            repository.saveAll(
                    List.of(a, b, c)
            );
        };
    }
}
