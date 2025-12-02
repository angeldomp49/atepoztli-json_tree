package org.makechtec.software.json_tree.arrays;

import org.makechtec.software.json_tree.JSONIndexKeyLeaf;
import org.makechtec.software.json_tree.primitives.StringJSONLeaf;
import org.makechtec.software.json_tree.validation.LeafContent;

import java.util.List;
import java.util.Optional;

public class ArrayStringLeaf implements JSONIndexKeyLeaf {

    private final List<String> values;

    public ArrayStringLeaf(List<String> values) {
        this.values = values;
    }

    @Override
    public String getLeafValue() {

        var responseBuilder = new StringBuilder("[");

        this.values.forEach(value -> {
            responseBuilder.append('"').append(value).append('"').append(',');
        });

        if (responseBuilder.lastIndexOf(",") != -1) {
            responseBuilder.deleteCharAt(responseBuilder.lastIndexOf(","));
        }

        responseBuilder.append(']');

        return responseBuilder.toString();
    }

    @Override
    public boolean isEmpty() {
        var hasNoLeafs = this.values.isEmpty();
        return hasNoLeafs ||
                this.values
                        .stream()
                        .allMatch(String::isEmpty);
    }

    @Override
    public LeafContent getLeafContent() {
        return LeafContent.ARRAY_LEAF;
    }

    @Override
    public <T> Optional<T> asLeaf(int key, Class<T> type) {
        var wrapper = new StringJSONLeaf(values.get(key));

        return Optional.of(type.cast(wrapper));
    }
}
