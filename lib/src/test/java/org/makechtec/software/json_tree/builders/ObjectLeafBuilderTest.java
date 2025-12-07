package org.makechtec.software.json_tree.builders;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void asLeaf_shouldReturnNumberJSONLeafForNumberField() {
        var objectLeaf = ObjectLeafBuilder.builder()
                .put("id", 42)
                .put("name", "John")
                .build();

        var result = objectLeaf.asLeaf("id");

        assertTrue(result.isPresent());
        assertEquals("42", result.get().getLeafValue());
    }

    @Test
    void asLeaf_shouldReturnStringJSONLeafForStringField() {
        var objectLeaf = ObjectLeafBuilder.builder()
                .put("id", 1)
                .put("name", "Alice")
                .put("city", "New York")
                .build();

        var result = objectLeaf.asLeaf("name");

        assertTrue(result.isPresent());
        assertEquals("Alice", result.get().getLeafValue());
    }

    @Test
    void asLeaf_shouldReturnBooleanJSONLeafForBooleanField() {
        var objectLeaf = ObjectLeafBuilder.builder()
                .put("id", 1)
                .put("isActive", true)
                .put("hasPassed", false)
                .build();

        var resultTrue = objectLeaf.asLeaf("isActive");
        var resultFalse = objectLeaf.asLeaf("hasPassed");

        assertTrue(resultTrue.isPresent());
        assertEquals("true", resultTrue.get().getLeafValue());

        assertTrue(resultFalse.isPresent());
        assertEquals("false", resultFalse.get().getLeafValue());
    }

    @Test
    void asLeaf_shouldThrowExceptionForNonExistentKey() {
        var objectLeaf = ObjectLeafBuilder.builder()
                .put("id", 1)
                .build();

        assertThrows(NullPointerException.class, () ->
                objectLeaf.asLeaf("nonExistentKey"));
    }
}

