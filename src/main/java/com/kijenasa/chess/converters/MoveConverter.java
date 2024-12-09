package com.kijenasa.chess.converters;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bhlangonijr.chesslib.Square;
import com.github.bhlangonijr.chesslib.move.Move;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MoveConverter implements AttributeConverter<Address, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Move move) {
        try {
            return move != null ? objectMapper.writeValueAsString(move) : null;
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public Address convertToEntityAttribute(String moveJson) {
        if (moveJson == null || moveJson.isEmpty()) {
            return null;
        }
        return objectMapper.readValue(moveJson, Address.class);
    }
}
