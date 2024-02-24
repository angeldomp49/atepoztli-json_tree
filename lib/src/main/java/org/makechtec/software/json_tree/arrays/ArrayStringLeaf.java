package org.makechtec.software.json_tree.arrays;

import org.makechtec.software.json_tree.JSONLeaf;
import org.makechtec.software.json_tree.ObjectLeaf;

import java.util.Set;

public class ArrayStringLeaf implements JSONLeaf {

    private final Set<String> values;

    public ArrayStringLeaf(Set<String> values) {
        this.values = values;
    }

    @Override
    public String getLeafValue() {

        var responseBuilder = new StringBuilder("[");

        this.values.forEach( value -> {
            responseBuilder.append('"').append(value).append('"').append(',');
        } );

        if(responseBuilder.lastIndexOf(",") != -1){
            responseBuilder.deleteCharAt(responseBuilder.lastIndexOf(","));
        }

        responseBuilder.append(']');

        return responseBuilder.toString();
    }
}
