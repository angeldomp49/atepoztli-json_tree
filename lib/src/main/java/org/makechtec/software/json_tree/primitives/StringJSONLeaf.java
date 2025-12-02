package org.makechtec.software.json_tree.primitives;

import org.makechtec.software.json_tree.JSONLeaf;
import org.makechtec.software.json_tree.validation.LeafContentType;

import java.util.Objects;

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

    @Override
    public boolean isEmpty() {
        return Objects.isNull(value) || value.isEmpty();
    }

    @Override
    public LeafContentType getLeafContentType() {
        return LeafContentType.STRING_LEAF;
    }
}
