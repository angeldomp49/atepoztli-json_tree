package org.makechtec.software.json_tree.builders;

import org.makechtec.software.json_tree.arrays.ArrayBooleanLeaf;

import java.util.ArrayList;
import java.util.List;

public class ArrayBooleanLeafBuilder {

    private final List<Boolean> items = new ArrayList<>();

    private ArrayBooleanLeafBuilder() {
    }

    public static ArrayBooleanLeafBuilder builder() {
        return new ArrayBooleanLeafBuilder();
    }

    public ArrayBooleanLeafBuilder add(Boolean item) {
        this.items.add(item);
        return this;
    }

    public ArrayBooleanLeaf build() {
        return new ArrayBooleanLeaf(this.items);
    }

}
