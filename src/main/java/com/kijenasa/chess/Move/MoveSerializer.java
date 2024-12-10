package com.kijenasa.chess.Move;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.bhlangonijr.chesslib.move.Move;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class MoveSerializer {
    public static class Serializer extends JsonSerializer<MoveWrapper> {

        @Override
        public void serialize(MoveWrapper move, JsonGenerator jgen, SerializerProvider serializers) throws IOException {
            jgen.writeStartObject();
            jgen.writeStringField("from", move.getMove().getFrom().toString());
            jgen.writeStringField("to", move.getMove().getTo().toString());
            jgen.writeEndObject();
        }

    }
}
