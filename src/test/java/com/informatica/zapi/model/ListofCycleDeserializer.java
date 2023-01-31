package com.informatica.zapi.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class ListofCycleDeserializer extends StdDeserializer<GetListofCycleResponse> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ListofCycleDeserializer() {
        this(null);
    }

    public ListofCycleDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public GetListofCycleResponse deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();

        JsonNode node = jp.getCodec().readTree(jp);
        GetListofCycleResponse cycleresponse = new GetListofCycleResponse();
        node.fieldNames().forEachRemaining(name -> {
            if (!name.equalsIgnoreCase("recordsCount")) {
                JsonNode cycleNode = node.get(name);
                try {
                    Cycle cycle = (Cycle) mapper.treeToValue(cycleNode, Cycle.class);
                    cycle.setId(Integer.valueOf(name));
                    cycleresponse.addCycle(cycle);
                } catch (JsonProcessingException e) {

                }
            } else {
                cycleresponse.recordsCount((Integer) node.get(name).intValue());
            }
        });
        return cycleresponse;
    }

}
