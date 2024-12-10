package com.kijenasa.chess.Move;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.bhlangonijr.chesslib.Square;

import java.io.IOException;

public class MoveDeserializer extends JsonDeserializer<MoveWrapper> {

    @Override
    public MoveWrapper deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        return new MoveWrapper(
                Square.fromValue(node.get("from").asText()),
                Square.fromValue(node.get("to").asText())
        );
    }
}
