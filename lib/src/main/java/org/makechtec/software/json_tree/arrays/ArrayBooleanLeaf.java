package org.makechtec.software.json_tree.arrays;

import org.makechtec.software.json_tree.JSONIndexKeyLeaf;

import java.util.List;
import java.util.Optional;

public class ArrayBooleanLeaf implements JSONIndexKeyLeaf {

    private final List<Boolean> values;

    public ArrayBooleanLeaf(List<Boolean> values) {
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

    @Override
    public boolean isEmpty() {
        return values.isEmpty();
    }

    @Override
    public <T> Optional<T> asLeaf(int key, Class<T> type) {
        return Optional.of(type.cast(values.get(key)));
    }

}
