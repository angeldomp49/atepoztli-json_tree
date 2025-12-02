package org.makechtec.software.json_tree;

import org.makechtec.software.json_tree.validation.LeafContentType;

public interface JSONLeaf {
    
    String getLeafValue();

    boolean isEmpty();

    LeafContentType getLeafContentType();
    
    

}
