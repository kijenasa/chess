package com.kijenasa.chess.game;

import java.time.Duration;
import java.time.Instant;

public class Game {
    private Long id;
    private Turn turn;
    private Duration duration;
    private Instant startTime;

    public Game() {}

    public Game(Long id, Turn turn, Duration duration, Instant startTime) {
        this.id = id;
        this.turn = turn;
        this.duration = duration;
        this.startTime = startTime;
    }

    public Game(Turn turn, Duration duration, Instant startTime) {
        this.turn = turn;
        this.duration = duration;
        this.startTime = startTime;
    }

    public Game(Duration duration, Instant startTime) {
        this.turn = Turn.White;
        this.duration = duration;
        this.startTime = startTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Turn getTurn() {
        return turn;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
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

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", turn=" + turn +
                ", duration=" + duration +
                ", startTime=" + startTime +
                '}';
    }
}
