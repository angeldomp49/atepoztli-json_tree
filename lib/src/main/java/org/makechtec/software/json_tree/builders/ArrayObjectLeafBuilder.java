package org.makechtec.software.json_tree.builders;

import org.makechtec.software.json_tree.ObjectLeaf;
import org.makechtec.software.json_tree.arrays.ArrayObjectLeaf;

import java.util.ArrayList;
import java.util.List;

public class ArrayObjectLeafBuilder {

    private final List<ObjectLeaf> items = new ArrayList<>();

    private ArrayObjectLeafBuilder() {
    }

    public static ArrayObjectLeafBuilder builder() {
        return new ArrayObjectLeafBuilder();
    }

    public ArrayObjectLeafBuilder add(ObjectLeaf item) {
        this.items.add(item);
        return this;
    }

    public ArrayObjectLeaf build() {
        return new ArrayObjectLeaf(this.items);
    }

}
