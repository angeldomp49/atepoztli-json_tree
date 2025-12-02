package org.makechtec.software.json_tree.arrays;

import org.makechtec.software.json_tree.JSONIndexKeyLeaf;
import org.makechtec.software.json_tree.JSONLeaf;
import org.makechtec.software.json_tree.primitives.BooleanJSONLeaf;
import org.makechtec.software.json_tree.validation.LeafContentType;

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
    public LeafContentType getLeafContentType() {
        return LeafContentType.ARRAY_LEAF;
    }

    @Override
    public Optional<JSONLeaf> asLeaf(int key) {
        var wrapper = new BooleanJSONLeaf(values.get(key));

        return Optional.of(wrapper);
    }

}
