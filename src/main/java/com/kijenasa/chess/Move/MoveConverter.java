package com.kijenasa.chess.Move;

import com.github.bhlangonijr.chesslib.move.Move;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MoveConverter implements AttributeConverter<MoveWrapper, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(MoveWrapper move) {
        try {
            return move != null ? objectMapper.writeValueAsString(move) : null;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MoveWrapper convertToEntityAttribute(String moveJson) {

        if (moveJson == null || moveJson.isEmpty()) {
            return null;
        }
        try {
            return objectMapper.readValue(moveJson, MoveWrapper.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
