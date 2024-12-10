package com.kijenasa.chess.Move;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.bhlangonijr.chesslib.Square;
import com.github.bhlangonijr.chesslib.move.Move;

public class MoveWrapper {
    Square from;
    Square to;

    public MoveWrapper() {
        from = Square.NONE;
        to = Square.NONE;
    }

    public MoveWrapper(Square from, Square to) {
        this.from = from;
        this.to = to;
    }

    public Square getFrom() {
        return from;
    }

    public void setFrom(Square from) {
        this.from = from;
    }

    public Square getTo() {
        return to;
    }

    public void setTo(Square to) {
        this.to = to;
    }

    @JsonIgnore
    public Move getMove() {
        return new Move(from, to);
    }
}
