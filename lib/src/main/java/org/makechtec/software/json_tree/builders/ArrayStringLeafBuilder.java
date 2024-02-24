package org.makechtec.software.json_tree.builders;

import org.makechtec.software.json_tree.arrays.ArrayNumberLeaf;
import org.makechtec.software.json_tree.arrays.ArrayStringLeaf;

import java.util.HashSet;
import java.util.Set;

public class ArrayStringLeafBuilder {

    private final Set<String> items = new HashSet<>();

    private ArrayStringLeafBuilder(){}

    public static ArrayStringLeafBuilder builder(){
        return new ArrayStringLeafBuilder();
    }

    public ArrayStringLeafBuilder add(String item){
        this.items.add(item);
        return this;
    }

    public ArrayStringLeaf build(){
        return new ArrayStringLeaf(this.items);
    }

}
