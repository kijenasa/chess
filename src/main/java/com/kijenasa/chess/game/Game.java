package com.kijenasa.chess.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.bhlangonijr.chesslib.*;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table
public class Game {
    @Id
    @SequenceGenerator(
            name = "game_sequence",
            sequenceName = "game_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "game_sequence"
    )
    private Long id; // todo: get rid of
    @JsonIgnore
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 100)
    private UUID uuid;
    private Board board;

    public Game() {
        board = new Board();
        uuid = UUID.randomUUID();
    }

    public Game(UUID uuid, Board board) {
        this.uuid = uuid;
        this.board = board;
    }

    public Game(Board board) {
        this.board = board;
        uuid = UUID.randomUUID();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", fen=" + board.getFen() +
                '}';
    }
}
