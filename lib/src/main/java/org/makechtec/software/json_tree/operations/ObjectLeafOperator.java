package org.makechtec.software.json_tree.operations;

import org.makechtec.software.json_tree.JSONLeaf;
import org.makechtec.software.json_tree.ObjectLeaf;

import java.util.HashMap;

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

}
