package org.makechtec.software.json_tree;

import java.util.Optional;

public interface JSONIndexKeyLeaf extends JSONLeaf {

    <T> Optional<T> asLeaf(int key, Class<T> type);
}
