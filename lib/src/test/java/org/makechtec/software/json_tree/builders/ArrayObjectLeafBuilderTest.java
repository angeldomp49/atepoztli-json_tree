package org.makechtec.software.json_tree.builders;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ArrayObjectLeafBuilderTest {

    @Test
    void build() {

        var item =
                ObjectLeafBuilder.builder()
                        .put("id", 1)
                        .put("name", "Jhon")
                        .put("hasPassed", false)
                        .build();

        var result =
                ArrayObjectLeafBuilder.builder()
                        .add(item)
                        .add(item)
                        .build()
                        .getLeafValue();

        var obj = new JSONArray(result);

        assertEquals("Jhon", obj.getJSONObject(0).getString("name"));
        assertEquals(1, obj.getJSONObject(0).getInt("id"));
        assertFalse(obj.getJSONObject(0).getBoolean("hasPassed"));
    }
}