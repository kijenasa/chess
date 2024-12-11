package com.kijenasa.chess.Player;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.kijenasa.chess.Move.MoveWrapper;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class PlayerSerializer {
    public static class Serializer extends JsonSerializer<Player> {

        @Override
        public void serialize(Player player, JsonGenerator jgen, SerializerProvider serializers) throws IOException {
            jgen.writeStartObject();
            jgen.writeStringField("uuid", player.getUuid().toString());
            jgen.writeStringField("side", player.getSide().toString());
            jgen.writeEndObject();
        }

    }
}
