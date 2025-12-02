package org.makechtec.software.json_tree.builders;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.makechtec.software.json_tree.primitives.StringJSONLeaf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayStringLeafBuilderTest {

    @Test
    void build() {
        // Given/When
        var result = ArrayStringLeafBuilder.builder()
                .add("Alice")
                .add("Bob")
                .add("Charlie")
                .build()
                .getLeafValue();

        var array = new JSONArray(result);

        // Then
        assertEquals(3, array.length());
        assertEquals("Alice", array.getString(0));
        assertEquals("Bob", array.getString(1));
        assertEquals("Charlie", array.getString(2));
    }

    @Test
    void asLeaf_shouldReturnStringAtIndex() {
        // Given
        var arrayLeaf = ArrayStringLeafBuilder.builder()
                .add("Hello")
                .add("World")
                .add("Test")
                .build();

        // When
        var result0 = arrayLeaf.asLeaf(0, StringJSONLeaf.class);
        var result1 = arrayLeaf.asLeaf(1, StringJSONLeaf.class);
        var result2 = arrayLeaf.asLeaf(2, StringJSONLeaf.class);

        // Then
        assertTrue(result0.isPresent());
        assertEquals("\"Hello\"", result0.get().getLeafValue());

        assertTrue(result1.isPresent());
        assertEquals("\"World\"", result1.get().getLeafValue());

        assertTrue(result2.isPresent());
        assertEquals("\"Test\"", result2.get().getLeafValue());
    }

    @Test
    void asLeaf_shouldReturnEmptyString() {
        // Given
        var arrayLeaf = ArrayStringLeafBuilder.builder()
                .add("")
                .add("non-empty")
                .build();

        // When
        var result = arrayLeaf.asLeaf(0, StringJSONLeaf.class);

        // Then
        assertTrue(result.isPresent());
        assertEquals("\"\"", result.get().getLeafValue());
    }

    @Test
    void asLeaf_shouldThrowIndexOutOfBoundsForInvalidIndex() {
        // Given
        var arrayLeaf = ArrayStringLeafBuilder.builder()
                .add("test")
                .build();

        // When/Then
        assertThrows(IndexOutOfBoundsException.class, () ->
                arrayLeaf.asLeaf(5, StringJSONLeaf.class));
    }

    @Test
    void asLeaf_shouldThrowClassCastExceptionForWrongType() {
        // Given
        var arrayLeaf = ArrayStringLeafBuilder.builder()
                .add("test")
                .build();

        // When/Then
        assertThrows(ClassCastException.class, () ->
                arrayLeaf.asLeaf(0, Integer.class));
    }

    @Test
    void isEmpty_shouldReturnTrueForEmptyArray() {
        // Given
        var arrayLeaf = ArrayStringLeafBuilder.builder().build();

        // When/Then
        assertTrue(arrayLeaf.isEmpty());
    }

    @Test
    void isEmpty_shouldReturnTrueForArrayWithOnlyEmptyStrings() {
        // Given
        var arrayLeaf = ArrayStringLeafBuilder.builder()
                .add("")
                .add("")
                .build();

        // When/Then
        assertTrue(arrayLeaf.isEmpty());
    }

    @Test
    void isEmpty_shouldReturnFalseForNonEmptyArray() {
        // Given
        var arrayLeaf = ArrayStringLeafBuilder.builder()
                .add("test")
                .build();

        // When/Then
        assertFalse(arrayLeaf.isEmpty());
    }

    @Test
    void isEmpty_shouldReturnFalseWhenAtLeastOneStringIsNotEmpty() {
        // Given
        var arrayLeaf = ArrayStringLeafBuilder.builder()
                .add("")
                .add("test")
                .add("")
                .build();

        // When/Then
        assertFalse(arrayLeaf.isEmpty());
    }
}

