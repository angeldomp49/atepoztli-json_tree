package org.makechtec.software.json_tree.arrays;

import org.makechtec.software.json_tree.JSONLeaf;
import org.makechtec.software.json_tree.ObjectLeaf;

import java.util.Set;

public class ArrayObjectLeaf implements JSONLeaf {

    private final Set<ObjectLeaf> values;

    public ArrayObjectLeaf(Set<ObjectLeaf> values) {
        this.values = values;
    }

    @Override
    public String getLeafValue() {

        var responseBuilder = new StringBuilder("[");

        this.values.forEach( value -> {
            responseBuilder.append(value.getLeafValue()).append(',');
        } );

        if(responseBuilder.lastIndexOf(",") != -1){
            responseBuilder.deleteCharAt(responseBuilder.lastIndexOf(","));
        }

        responseBuilder.append(']');

        return responseBuilder.toString();
    }

}
