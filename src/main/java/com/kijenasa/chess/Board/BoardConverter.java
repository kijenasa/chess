package com.kijenasa.chess.Board;

import com.github.bhlangonijr.chesslib.Board;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class BoardConverter implements AttributeConverter<Board, String> {

    @Override
    public String convertToDatabaseColumn(Board board) {
        return board != null ? board.getFen() : null;
    }

    @Override
    public Board convertToEntityAttribute(String fen) {
        if (fen == null || fen.isEmpty()) {
            return null;
        }
        Board board = new Board();
        board.loadFromFen(fen);
        return board;
    }
}
