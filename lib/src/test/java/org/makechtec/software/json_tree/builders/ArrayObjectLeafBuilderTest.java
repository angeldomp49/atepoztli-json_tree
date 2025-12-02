package org.makechtec.software.json_tree.builders;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void asLeaf_shouldReturnObjectLeafAtIndex() {
        var item1 = ObjectLeafBuilder.builder()
                .put("id", 1)
                .put("name", "Alice")
                .build();

        var item2 = ObjectLeafBuilder.builder()
                .put("id", 2)
                .put("name", "Bob")
                .build();

        var arrayLeaf = ArrayObjectLeafBuilder.builder()
                .add(item1)
                .add(item2)
                .build();

        var result = arrayLeaf.asLeaf(0);

        assertTrue(result.isPresent());
        assertEquals(item1, result.get());

        var json = new JSONObject(result.get().getLeafValue());
        assertEquals(1, json.getInt("id"));
        assertEquals("Alice", json.getString("name"));
    }

    @Test
    void asLeaf_shouldReturnSecondElementAtIndex1() {
        var item1 = ObjectLeafBuilder.builder()
                .put("id", 1)
                .put("name", "Alice")
                .build();

        var item2 = ObjectLeafBuilder.builder()
                .put("id", 2)
                .put("name", "Bob")
                .build();

        var arrayLeaf = ArrayObjectLeafBuilder.builder()
                .add(item1)
                .add(item2)
                .build();

        var result = arrayLeaf.asLeaf(1);

        assertTrue(result.isPresent());
        assertEquals(item2, result.get());

        var json = new JSONObject(result.get().getLeafValue());
        assertEquals(2, json.getInt("id"));
        assertEquals("Bob", json.getString("name"));
    }

    @Test
    void asLeaf_shouldThrowIndexOutOfBoundsForInvalidIndex() {
        var item = ObjectLeafBuilder.builder()
                .put("id", 1)
                .build();

        var arrayLeaf = ArrayObjectLeafBuilder.builder()
                .add(item)
                .build();

        assertThrows(IndexOutOfBoundsException.class, () ->
                arrayLeaf.asLeaf(5));
    }
}

