package org.makechtec.software.json_tree.builders;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.makechtec.software.json_tree.primitives.BooleanJSONLeaf;
import org.makechtec.software.json_tree.primitives.NumberJSONLeaf;
import org.makechtec.software.json_tree.primitives.StringJSONLeaf;

import static org.junit.jupiter.api.Assertions.*;

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
        // Given
        var objectLeaf = ObjectLeafBuilder.builder()
                .put("id", 42)
                .put("name", "John")
                .build();

        // When
        var result = objectLeaf.asLeaf("id", NumberJSONLeaf.class);

        // Then
        assertTrue(result.isPresent());
        assertEquals("42", result.get().getLeafValue());
    }

    @Test
    void asLeaf_shouldReturnStringJSONLeafForStringField() {
        // Given
        var objectLeaf = ObjectLeafBuilder.builder()
                .put("id", 1)
                .put("name", "Alice")
                .put("city", "New York")
                .build();

        // When
        var result = objectLeaf.asLeaf("name", StringJSONLeaf.class);

        // Then
        assertTrue(result.isPresent());
        assertEquals("\"Alice\"", result.get().getLeafValue());
    }

    @Test
    void asLeaf_shouldReturnBooleanJSONLeafForBooleanField() {
        // Given
        var objectLeaf = ObjectLeafBuilder.builder()
                .put("id", 1)
                .put("isActive", true)
                .put("hasPassed", false)
                .build();

        // When
        var resultTrue = objectLeaf.asLeaf("isActive", BooleanJSONLeaf.class);
        var resultFalse = objectLeaf.asLeaf("hasPassed", BooleanJSONLeaf.class);

        // Then
        assertTrue(resultTrue.isPresent());
        assertEquals("true", resultTrue.get().getLeafValue());
        
        assertTrue(resultFalse.isPresent());
        assertEquals("false", resultFalse.get().getLeafValue());
    }

    @Test
    void asLeaf_shouldThrowExceptionForNonExistentKey() {
        // Given
        var objectLeaf = ObjectLeafBuilder.builder()
                .put("id", 1)
                .build();

        // When/Then
        assertThrows(NullPointerException.class, () -> 
                objectLeaf.asLeaf("nonExistentKey", StringJSONLeaf.class));
    }

    @Test
    void asLeaf_shouldThrowClassCastExceptionForWrongType() {
        // Given
        var objectLeaf = ObjectLeafBuilder.builder()
                .put("id", 1)
                .build();

        // When/Then
        assertThrows(ClassCastException.class, () -> 
                objectLeaf.asLeaf("id", StringJSONLeaf.class));
    }
}