package org.makechtec.software.json_tree.builders;

import org.makechtec.software.json_tree.ObjectLeaf;
import org.makechtec.software.json_tree.arrays.ArrayBooleanLeaf;
import org.makechtec.software.json_tree.arrays.ArrayObjectLeaf;

import java.util.HashSet;
import java.util.Set;

public class ArrayBooleanLeafBuilder {

    private final Set<Boolean> items = new HashSet<>();

    private ArrayBooleanLeafBuilder(){}

    public static ArrayBooleanLeafBuilder builder(){
        return new ArrayBooleanLeafBuilder();
    }

    public ArrayBooleanLeafBuilder add(Boolean item){
        this.items.add(item);
        return this;
    }

    public ArrayBooleanLeaf build(){
        return new ArrayBooleanLeaf(this.items);
    }

}
