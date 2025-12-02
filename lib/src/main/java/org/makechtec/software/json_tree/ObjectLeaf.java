package org.makechtec.software.json_tree;

import org.makechtec.software.json_tree.validation.LeafContentType;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public record ObjectLeaf(Map<String, JSONLeaf> leafs) implements JSONStringKeyLeaf {

    @Override
    public String getLeafValue() {

        var responseBuilder = new StringBuilder("{");

        this.leafs
                .forEach((key, value) -> {
                    responseBuilder.append('"')
                            .append(key)
                            .append('"')
                            .append(':')
                            .append(value.getLeafValue())
                            .append(',');
                });

        if (responseBuilder.lastIndexOf(",") != -1) {
            responseBuilder.deleteCharAt(responseBuilder.lastIndexOf(","));
        }

        responseBuilder.append('}');

        return responseBuilder.toString();
    }

    @Override
    public boolean isEmpty() {

        var hasNoLeafs = this.leafs.isEmpty();
        return hasNoLeafs ||
                this.leafs
                        .values()
                        .stream()
                        .allMatch(JSONLeaf::isEmpty);
    }

    @Override
    public LeafContentType getLeafContentType() {
        return LeafContentType.OBJECT_LEAF;
    }

    @Override
    public Optional<JSONLeaf> asLeaf(String key) {
        return Optional.of(leafs.get(key));
    }

    public Map<String, JSONLeaf> leafs() {
        return Collections.unmodifiableMap(leafs);
    }

}
