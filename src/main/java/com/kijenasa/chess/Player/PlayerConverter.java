package com.kijenasa.chess.Player;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Side;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;
import java.util.UUID;

@Converter(autoApply = true)
public class PlayerConverter implements AttributeConverter<Player, List<String>> {

    @Override
    public List<String> convertToDatabaseColumn(Player player) {
        return player != null ? List.of(player.getUuid().toString(), player.getSide().toString()) : null;
    }

    @Override
    public Player convertToEntityAttribute(List<String> strings) {
        if(strings != null) {
            return new Player(
                    UUID.fromString(strings.get(0)),
                    Side.fromValue(strings.get(1))
            );
        }
        return null;
    }
}

