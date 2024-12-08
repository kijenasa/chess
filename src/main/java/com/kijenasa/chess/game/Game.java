package com.kijenasa.chess.game;

import com.github.bhlangonijr.chesslib.*;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Duration;
import java.time.Instant;
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
    private Long id;
    private UUID uuid;
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 100)
    private Board board;

    public Game() {}

    public Game(Long id, Board board) {
        this.id = id;
        this.board = board;
        uuid = UUID.randomUUID();
    }

    public Game(Board board) {
        this.board = new Board();
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
                "uuid=" + uuid +
                ", fen=" + board.getFen() +
                '}';
    }
}
