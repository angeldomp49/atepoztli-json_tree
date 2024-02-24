package org.makechtec.software.json_tree.builders;

import org.makechtec.software.json_tree.arrays.ArrayBooleanLeaf;
import org.makechtec.software.json_tree.arrays.ArrayNumberLeaf;

import java.util.HashSet;
import java.util.Set;

public class ArrayNumberLeafBuilder {

    private final Set<Number> items = new HashSet<>();

    private ArrayNumberLeafBuilder(){}

    public static ArrayNumberLeafBuilder builder(){
        return new ArrayNumberLeafBuilder();
    }

    public ArrayNumberLeafBuilder add(Number item){
        this.items.add(item);
        return this;
    }

    public ArrayNumberLeaf build(){
        return new ArrayNumberLeaf(this.items);
    }

}
