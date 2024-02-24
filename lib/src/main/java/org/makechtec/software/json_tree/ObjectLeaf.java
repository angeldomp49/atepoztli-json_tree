package org.makechtec.software.json_tree;

import java.util.Map;

public class ObjectLeaf implements JSONLeaf {

    private final Map<String, JSONLeaf> leafs;

    public ObjectLeaf(Map<String, JSONLeaf> leafs) {
        this.leafs = leafs;
    }

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

}
