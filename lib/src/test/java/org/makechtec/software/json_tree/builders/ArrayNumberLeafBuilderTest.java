package org.makechtec.software.json_tree.builders;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayNumberLeafBuilderTest {

    @Test
    void build() {
        // Given/When
        var result = ArrayNumberLeafBuilder.builder()
                .add(10)
                .add(20.5)
                .add(30L)
                .build()
                .getLeafValue();

        var array = new JSONArray(result);

        // Then
        assertEquals(3, array.length());
        assertEquals(10, array.getInt(0));
        assertEquals(20.5, array.getDouble(1));
        assertEquals(30L, array.getLong(2));
    }

    @Test
    void asLeaf_shouldReturnNumberAtIndex() {
        // Given
        var arrayLeaf = ArrayNumberLeafBuilder.builder()
                .add(42)
                .add(3.14)
                .add(100L)
                .build();

        // When
        var result0 = arrayLeaf.asLeaf(0, Number.class);
        var result1 = arrayLeaf.asLeaf(1, Number.class);
        var result2 = arrayLeaf.asLeaf(2, Number.class);

        // Then
        assertTrue(result0.isPresent());
        assertEquals(42, result0.get());

        assertTrue(result1.isPresent());
        assertEquals(3.14, result1.get());

        assertTrue(result2.isPresent());
        assertEquals(100L, result2.get());
    }

    @Test
    void asLeaf_shouldReturnIntegerWhenCastToInteger() {
        // Given
        var arrayLeaf = ArrayNumberLeafBuilder.builder()
                .add(42)
                .add(99)
                .build();

        // When
        var result = arrayLeaf.asLeaf(0, Integer.class);

        // Then
        assertTrue(result.isPresent());
        assertEquals(42, result.get());
        assertTrue(result.get() instanceof Integer);
    }

    @Test
    void asLeaf_shouldReturnDoubleWhenCastToDouble() {
        // Given
        var arrayLeaf = ArrayNumberLeafBuilder.builder()
                .add(3.14159)
                .add(2.71828)
                .build();

        // When
        var result = arrayLeaf.asLeaf(1, Double.class);

        // Then
        assertTrue(result.isPresent());
        assertEquals(2.71828, result.get());
        assertTrue(result.get() instanceof Double);
    }

    @Test
    void asLeaf_shouldThrowIndexOutOfBoundsForInvalidIndex() {
        // Given
        var arrayLeaf = ArrayNumberLeafBuilder.builder()
                .add(1)
                .add(2)
                .build();

        // When/Then
        assertThrows(IndexOutOfBoundsException.class, () ->
                arrayLeaf.asLeaf(10, Number.class));
    }

    @Test
    void asLeaf_shouldThrowClassCastExceptionForWrongType() {
        // Given
        var arrayLeaf = ArrayNumberLeafBuilder.builder()
                .add(42)
                .build();

        // When/Then
        assertThrows(ClassCastException.class, () ->
                arrayLeaf.asLeaf(0, String.class));
    }

    @Test
    void isEmpty_shouldReturnTrueForEmptyArray() {
        // Given
        var arrayLeaf = ArrayNumberLeafBuilder.builder().build();

        // When/Then
        assertTrue(arrayLeaf.isEmpty());
    }

    @Test
    void isEmpty_shouldReturnFalseForNonEmptyArray() {
        // Given
        var arrayLeaf = ArrayNumberLeafBuilder.builder()
                .add(0)
                .build();

        // When/Then
        assertFalse(arrayLeaf.isEmpty());
    }
}

