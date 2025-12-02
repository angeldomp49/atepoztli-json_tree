package org.makechtec.software.json_tree.builders;

import org.makechtec.software.json_tree.arrays.ArrayStringLeaf;

import java.util.ArrayList;
import java.util.List;

public class ArrayStringLeafBuilder {

    private final List<String> items = new ArrayList<>();

    private ArrayStringLeafBuilder() {
    }

    public static ArrayStringLeafBuilder builder() {
        return new ArrayStringLeafBuilder();
    }

    public ArrayStringLeafBuilder add(String item) {
        this.items.add(item);
        return this;
    }

    public ArrayStringLeaf build() {
        return new ArrayStringLeaf(this.items);
    }

}
