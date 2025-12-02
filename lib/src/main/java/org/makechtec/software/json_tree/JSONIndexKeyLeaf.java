package org.makechtec.software.json_tree;

import java.util.Optional;

public interface JSONIndexKeyLeaf extends JSONLeaf {

    Optional<JSONLeaf> asLeaf(int key);
}
