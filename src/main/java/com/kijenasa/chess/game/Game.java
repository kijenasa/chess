package com.kijenasa.chess.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.bhlangonijr.chesslib.*;

import com.kijenasa.chess.Move.MoveDeserializer;
import com.kijenasa.chess.Move.MoveWrapper;
import com.kijenasa.chess.Player.Player;
import com.kijenasa.chess.Player.PlayerDeserializer;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
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
    @JsonDeserialize(using = MoveDeserializer.class)
    private MoveWrapper recentMove;
    @JdbcTypeCode(SqlTypes.JSON)
    @JsonDeserialize(using = PlayerDeserializer.class)
    private Player white;
    @JdbcTypeCode(SqlTypes.JSON)
    @JsonDeserialize(using = PlayerDeserializer.class)
    private Player black;

    public Game() {
        board = new Board();
        uuid = UUID.randomUUID();
        recentMove = new MoveWrapper(Square.NONE, Square.NONE);
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

    public MoveWrapper getMove() {
        return recentMove;
    }

    public void setMove(MoveWrapper move) {
        this.recentMove = move;
    }

    public Player getWhite() {
        return white;
    }

    public void setWhite(Player white) {
        this.white = white;
    }

    public Player getBlack() {
        return black;
    }

    public void setBlack(Player black) {
        this.black = black;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", board=" + board +
                ", recentMove=" + recentMove +
                ", whiteUuid=" + white +
                ", blackUuid=" + black +
                '}';
    }
}
