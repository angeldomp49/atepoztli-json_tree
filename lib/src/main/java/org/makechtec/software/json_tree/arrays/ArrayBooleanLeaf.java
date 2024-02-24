package org.makechtec.software.json_tree.arrays;

import org.makechtec.software.json_tree.JSONLeaf;

import java.util.Set;

public class ArrayBooleanLeaf implements JSONLeaf {

    private final Set<Boolean> values;

    public ArrayBooleanLeaf(Set<Boolean> values) {
        this.values = values;
    }

    @Override
    public String getLeafValue() {

        var responseBuilder = new StringBuilder("[");

        this.values.forEach(value -> {
            responseBuilder.append(value).append(',');
        });

        if (responseBuilder.lastIndexOf(",") != -1) {
            responseBuilder.deleteCharAt(responseBuilder.lastIndexOf(","));
        }

        responseBuilder.append(']');

        return responseBuilder.toString();
    }

}
