package org.makechtec.software.json_tree.primitives;

import org.makechtec.software.json_tree.JSONLeaf;

public class StringJSONLeaf implements JSONLeaf {

    private final String value;

    public StringJSONLeaf(String value) {
        this.value = value;
    }

    public static StringJSONLeaf of(String value) {
        return new StringJSONLeaf(value);
    }

    @Override
    public String getLeafValue() {
        return "\"" + this.value + "\"";
    }
}
