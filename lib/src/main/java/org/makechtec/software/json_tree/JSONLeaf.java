package org.makechtec.software.json_tree;

import org.makechtec.software.json_tree.validation.LeafContent;

public interface JSONLeaf {
    String getLeafValue();

    boolean isEmpty();
    
    LeafContent getLeafContent();

}
