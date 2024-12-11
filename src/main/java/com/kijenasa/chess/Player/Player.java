package com.kijenasa.chess.Player;

import com.github.bhlangonijr.chesslib.Side;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.UUID;

public class Player {
    private UUID uuid;
    private Side side;

    public Player(UUID uuid, Side side) {
        this.uuid = uuid;
        this.side = side;
    }

    public Player(Side side) {
        uuid = UUID.randomUUID();
        this.side = side;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }
}
