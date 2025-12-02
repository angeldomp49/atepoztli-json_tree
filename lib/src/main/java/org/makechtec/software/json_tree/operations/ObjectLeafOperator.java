package org.makechtec.software.json_tree.operations;

import org.makechtec.software.json_tree.JSONLeaf;
import org.makechtec.software.json_tree.ObjectLeaf;
import org.makechtec.software.json_tree.validation.LeafContentType;

import java.util.HashMap;
import java.util.Optional;

public class ObjectLeafOperator {

    public ObjectLeaf merge(ObjectLeaf... leaves) {
        var allLeafs = new HashMap<String, JSONLeaf>();

        if (leaves == null) return new ObjectLeaf(allLeafs);

        for (ObjectLeaf leaf : leaves) {
            if (leaf == null) continue;
            allLeafs.putAll(leaf.leafs());
        }

        return new ObjectLeaf(allLeafs);
    }
    
    public Optional<String> extractStringValue(JSONLeaf leaf) {
        if(leaf.isEmpty() || !leaf.getLeafContentType().equals(LeafContentType.STRING_LEAF)){
            return Optional.empty();
        }
        
        return Optional.of(leaf.getLeafValue());
    }
    
    public Optional<Boolean> extractBooleanValue(JSONLeaf leaf) {
        if(leaf.isEmpty() || !leaf.getLeafContentType().equals(LeafContentType.BOOLEAN_LEAF)){
            return Optional.empty();
        }
        
        return Optional.of(Boolean.parseBoolean(leaf.getLeafValue()));
    }
    
    public Optional<Double> extractLongValue(JSONLeaf leaf) {
        if(leaf.isEmpty() || !leaf.getLeafContentType().equals(LeafContentType.NUMBER_LEAF)){
            return Optional.empty();
        }
        
        try {
            return Optional.of(Double.parseDouble(leaf.getLeafValue()));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

}
