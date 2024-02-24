package org.makechtec.software.json_tree.primitives;

import org.makechtec.software.json_tree.JSONLeaf;

public class NumberJSONLeaf implements JSONLeaf {

    private final Number value;

    public NumberJSONLeaf(Number value) {
        this.value = value;
    }

    public static NumberJSONLeaf of(Number value) {
        return new NumberJSONLeaf(value);
    }

    @Override
    public String getLeafValue() {
        return this.value + "";
    }
}
