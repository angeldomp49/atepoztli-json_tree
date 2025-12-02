package org.makechtec.software.json_tree.primitives;

import org.makechtec.software.json_tree.JSONLeaf;
import org.makechtec.software.json_tree.validation.LeafContentType;

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

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public LeafContentType getLeafContentType() {
        return LeafContentType.NUMBER_LEAF;
    }
}
