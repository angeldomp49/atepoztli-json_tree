package org.makechtec.software.json_tree;

import java.util.Optional;

public interface JSONStringKeyLeaf extends JSONLeaf {

    Optional<JSONLeaf> asLeaf(String key);
}
