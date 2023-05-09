package com.debugdemons.bimdb.deserializers;

import com.debugdemons.bimdb.domain.WatchProviders;
import com.debugdemons.bimdb.domain.WatchProvidersResult;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

public class WatchProviderResultDeserializer extends JsonDeserializer<WatchProvidersResult> {

    @Override
    public WatchProvidersResult deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        WatchProvidersResult result = new WatchProvidersResult();
        result.setWatchProviders(new ArrayList<>());

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = jsonParser.readValueAsTree();
        jsonNode.findValue("results").fields().forEachRemaining(stringJsonNodeEntry -> {
            try {
                WatchProviders item = objectMapper.treeToValue(stringJsonNodeEntry.getValue(), WatchProviders.class);
                item.setCountry(stringJsonNodeEntry.getKey());
                result.getWatchProviders().add(item);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });

        return result;
    }
}
