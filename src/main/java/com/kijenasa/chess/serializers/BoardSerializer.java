package com.kijenasa.chess.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.github.bhlangonijr.chesslib.Board;
import org.springframework.boot.jackson.JsonComponent;
import java.io.IOException;

@JsonComponent
public class BoardSerializer {
    public static class Serializer extends JsonSerializer<Board> {

        @Override
        public void serialize(Board board, JsonGenerator jgen, SerializerProvider serializers) throws IOException {
            jgen.writeStartObject();
            jgen.writeStringField("fen", board.getFen());
            jgen.writeEndObject();
        }

    }

//    public static class Deserializer extends JsonDeserializer<Board> {
//
//        @Override
//        public Board deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
//            ObjectCodec codec = jsonParser.getCodec();
//            JsonNode tree = codec.readTree(jsonParser);
//            String name = tree.get("name").textValue();
//            int age = tree.get("age").intValue();
//            return new MyObject(name, age);
//        }
//
//        @Override
//        public Board deserialize(com.fasterxml.jackson.core.JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
//            return null;
//        }
//    }
}
