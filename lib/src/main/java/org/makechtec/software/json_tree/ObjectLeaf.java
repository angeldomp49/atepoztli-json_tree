package org.makechtec.software.json_tree;

import java.util.Collections;
import java.util.Map;

public record ObjectLeaf(Map<String, JSONLeaf> leafs) implements JSONLeaf {

    @Override
    public String getLeafValue() {

        var responseBuilder = new StringBuilder("{");

        this.leafs
                .forEach((key, value) -> {
                    responseBuilder.append('"')
                            .append(key)
                            .append('"')
                            .append(':')
                            .append(value.getLeafValue())
                            .append(',');
                });

        if (responseBuilder.lastIndexOf(",") != -1) {
            responseBuilder.deleteCharAt(responseBuilder.lastIndexOf(","));
        }

        responseBuilder.append('}');

        return responseBuilder.toString();
    }
    
    public Map<String, JSONLeaf> leafs() {
        return Collections.unmodifiableMap(leafs);
    }

}
