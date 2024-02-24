package org.makechtec.software.json_tree.builders;

import org.makechtec.software.json_tree.arrays.ArrayObjectLeaf;
import org.makechtec.software.json_tree.ObjectLeaf;

import java.util.HashSet;
import java.util.Set;

public class ArrayObjectLeafBuilder {

    private final Set<ObjectLeaf> items = new HashSet<>();

    private ArrayObjectLeafBuilder(){}

    public static ArrayObjectLeafBuilder builder(){
        return new ArrayObjectLeafBuilder();
    }

    public ArrayObjectLeafBuilder add(ObjectLeaf item){
        this.items.add(item);
        return this;
    }

    public ArrayObjectLeaf build(){
        return new ArrayObjectLeaf(this.items);
    }

}
