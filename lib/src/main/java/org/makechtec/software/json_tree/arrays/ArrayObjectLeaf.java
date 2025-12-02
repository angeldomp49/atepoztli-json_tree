package org.makechtec.software.json_tree.arrays;

import org.makechtec.software.json_tree.JSONIndexKeyLeaf;
import org.makechtec.software.json_tree.JSONLeaf;
import org.makechtec.software.json_tree.ObjectLeaf;

import java.util.List;
import java.util.Optional;

public class ArrayObjectLeaf implements JSONIndexKeyLeaf {

    private final List<ObjectLeaf> values;

    public ArrayObjectLeaf(List<ObjectLeaf> values) {
        this.values = values;
    }

    @Override
    public String getLeafValue() {

        var responseBuilder = new StringBuilder("[");

        this.values.forEach(value -> {
            responseBuilder.append(value.getLeafValue()).append(',');
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
                        .allMatch(JSONLeaf::isEmpty);
    }

    @Override
    public <T> Optional<T> asLeaf(int key, Class<T> type) {
        return Optional.of(type.cast(values.get(key)));
    }

}
