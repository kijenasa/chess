package com.kijenasa.chess.Player;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.bhlangonijr.chesslib.Side;
import com.github.bhlangonijr.chesslib.Square;
import com.kijenasa.chess.Move.MoveWrapper;

import java.io.IOException;
import java.util.UUID;

public class PlayerDeserializer extends JsonDeserializer<Player> {

    @Override
    public Player deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        return new Player(
                UUID.fromString(node.get("uuid").asText()),
                Side.fromValue(node.get("side").asText())
        );
    }
}
