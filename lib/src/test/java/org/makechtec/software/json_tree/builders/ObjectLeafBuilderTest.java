package org.makechtec.software.json_tree.builders;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ObjectLeafBuilderTest {

    @Test
    void build() {

        var result =
                ObjectLeafBuilder.builder()
                        .put("id", 1)
                        .put("name", "Jhon")
                        .put("hasPassed", false)
                        .build()
                        .getLeafValue();

        var obj = new JSONObject(result);

        assertEquals("Jhon", obj.getString("name"));
        assertEquals(1, obj.getInt("id"));
        assertFalse(obj.getBoolean("hasPassed"));

    }
}