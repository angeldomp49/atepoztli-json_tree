package org.makechtec.software.json_tree.primitives;

import org.makechtec.software.json_tree.JSONLeaf;

public class BooleanJSONLeaf implements JSONLeaf {

    private final boolean value;

    public BooleanJSONLeaf(boolean value) {
        this.value = value;
    }

    public static BooleanJSONLeaf of(boolean value) {
        return new BooleanJSONLeaf(value);
    }

    @Override
    public String getLeafValue() {
        return this.value + "";
    }
}
