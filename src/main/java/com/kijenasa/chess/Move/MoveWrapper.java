package com.kijenasa.chess.Move;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Piece;
import com.github.bhlangonijr.chesslib.Rank;
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
    public Move getMove(Board board) {
        Piece promotionPiece = Piece.NONE;
        if(board.getPiece(from) == Piece.WHITE_PAWN || board.getPiece(from) == Piece.BLACK_PAWN) {
            if (to.getRank() == Rank.RANK_8) {
                promotionPiece = Piece.WHITE_QUEEN;
            } else if (to.getRank() == Rank.RANK_1) {
                promotionPiece = Piece.BLACK_QUEEN;
            }
        }
        return new Move(from, to, promotionPiece);
    }

    @Override
    public String toString() {
        return "MoveWrapper{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
