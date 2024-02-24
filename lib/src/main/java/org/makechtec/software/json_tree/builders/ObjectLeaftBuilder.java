package org.makechtec.software.json_tree.builders;

import org.makechtec.software.json_tree.JSONLeaf;
import org.makechtec.software.json_tree.ObjectLeaf;
import org.makechtec.software.json_tree.primitives.BooleanJSONLeaf;
import org.makechtec.software.json_tree.primitives.NumberJSONLeaf;
import org.makechtec.software.json_tree.primitives.StringJSONLeaf;

import java.util.HashMap;
import java.util.Map;

public class ObjectLeaftBuilder {

    private final Map<String, JSONLeaf> leafs = new HashMap<>();

    private ObjectLeaftBuilder(){}

    public static ObjectLeaftBuilder builder(){
        return new ObjectLeaftBuilder();
    }

    public ObjectLeaftBuilder put(String key, Number number){
        this.leafs.put(key, NumberJSONLeaf.of(number));
        return this;
    }

    public ObjectLeaftBuilder put(String key, boolean bool){
        this.leafs.put(key, BooleanJSONLeaf.of(bool));
        return this;
    }

    public ObjectLeaftBuilder put(String key, String value){
        this.leafs.put(key, StringJSONLeaf.of(value));
        return this;
    }

    public ObjectLeaftBuilder put(String key, JSONLeaf jsonLeaf){
        this.leafs.put(key, jsonLeaf);
        return this;
    }

    public ObjectLeaf build(){
        return new ObjectLeaf(this.leafs);
    }

}
