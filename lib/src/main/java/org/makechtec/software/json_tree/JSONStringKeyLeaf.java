package org.makechtec.software.json_tree;

import java.util.Optional;

public interface JSONStringKeyLeaf extends JSONLeaf {

    <T> Optional<T> asLeaf(String key, Class<T> type);
}
