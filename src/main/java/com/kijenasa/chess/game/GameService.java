package com.kijenasa.chess.game;

import com.github.bhlangonijr.chesslib.Board;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class GameService {

    public List<Game> getGame() {
        return List.of(new Game(
                new Board()
        ));
    }
}
