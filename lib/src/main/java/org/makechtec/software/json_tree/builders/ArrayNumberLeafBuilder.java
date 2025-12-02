package org.makechtec.software.json_tree.builders;

import org.makechtec.software.json_tree.arrays.ArrayNumberLeaf;

import java.util.ArrayList;
import java.util.List;

public class ArrayNumberLeafBuilder {

    private final List<Number> items = new ArrayList<>();

    private ArrayNumberLeafBuilder() {
    }

    public static ArrayNumberLeafBuilder builder() {
        return new ArrayNumberLeafBuilder();
    }

    public ArrayNumberLeafBuilder add(Number item) {
        this.items.add(item);
        return this;
    }

    public ArrayNumberLeaf build() {
        return new ArrayNumberLeaf(this.items);
    }

}
