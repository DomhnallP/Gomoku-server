package com.dopoil.gomokuserver.hibernate.custom.serializers;

import com.dopoil.gomokuserver.domain.Board;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class BoardSerializer extends StdSerializer<Board> {

    public BoardSerializer() {
        this(null);
    }

    public BoardSerializer(Class<Board> t) {
        super(t);
    }

    /**
     * Used to serialise the board data array
     * @param board
     * @param jgen
     * @param provider
     * @throws IOException
     * @throws JsonProcessingException
     */
    @Override
    public void serialize(
            Board board, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject();
        jgen.writeArrayFieldStart("board");

        for(Integer[] i: board.getBoard()){
            jgen.writeStartArray();
            for(Integer j : i){
                jgen.writeNumber(j);
            }
            jgen.writeEndArray();
        }
        jgen.writeEndArray();
        jgen.writeEndObject();
    }
}
