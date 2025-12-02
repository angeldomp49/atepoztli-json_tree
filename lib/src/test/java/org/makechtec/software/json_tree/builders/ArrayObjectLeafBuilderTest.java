package org.makechtec.software.json_tree.builders;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.makechtec.software.json_tree.ObjectLeaf;

import static org.junit.jupiter.api.Assertions.*;

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
        // Given
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

        // When
        var result = arrayLeaf.asLeaf(0, ObjectLeaf.class);

        // Then
        assertTrue(result.isPresent());
        assertEquals(item1, result.get());
        
        var json = new JSONObject(result.get().getLeafValue());
        assertEquals(1, json.getInt("id"));
        assertEquals("Alice", json.getString("name"));
    }

    @Test
    void asLeaf_shouldReturnSecondElementAtIndex1() {
        // Given
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

        // When
        var result = arrayLeaf.asLeaf(1, ObjectLeaf.class);

        // Then
        assertTrue(result.isPresent());
        assertEquals(item2, result.get());
        
        var json = new JSONObject(result.get().getLeafValue());
        assertEquals(2, json.getInt("id"));
        assertEquals("Bob", json.getString("name"));
    }

    @Test
    void asLeaf_shouldThrowIndexOutOfBoundsForInvalidIndex() {
        // Given
        var item = ObjectLeafBuilder.builder()
                .put("id", 1)
                .build();

        var arrayLeaf = ArrayObjectLeafBuilder.builder()
                .add(item)
                .build();

        // When/Then
        assertThrows(IndexOutOfBoundsException.class, () -> 
                arrayLeaf.asLeaf(5, ObjectLeaf.class));
    }
}