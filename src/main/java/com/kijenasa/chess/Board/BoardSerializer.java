package com.kijenasa.chess.Board;

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
}
