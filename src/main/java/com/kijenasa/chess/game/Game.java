package com.kijenasa.chess.game;

import com.github.bhlangonijr.chesslib.*;

import java.time.Duration;
import java.time.Instant;

public class Game {
    private Long id;
    private Duration duration;
    private Instant startTime;
    private Board board;

    public Game() {}

    public Game(Long id, Duration duration, Instant startTime, Board board) {
        this.id = id;
        this.duration = duration;
        this.startTime = startTime;
        this.board = board;
    }

    public Game(Duration duration, Instant startTime) {
        this.duration = duration;
        this.startTime = startTime;
        this.board = new Board();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
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
                ", duration=" + duration +
                ", startTime=" + startTime +
                ", board=" + board.getFen() +
                '}';
    }
}
