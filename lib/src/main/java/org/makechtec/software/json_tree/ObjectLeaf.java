package org.makechtec.software.json_tree;

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
    public <T> Optional<T> asLeaf(String key, Class<T> type) {
        return Optional.of(type.cast(this.leafs().get(key)));
    }

    public Map<String, JSONLeaf> leafs() {
        return Collections.unmodifiableMap(leafs);
    }

}
