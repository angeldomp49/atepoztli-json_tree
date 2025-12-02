package org.makechtec.software.json_tree.builders;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayBooleanLeafBuilderTest {

    @Test
    void build() {
        // Given/When
        var result = ArrayBooleanLeafBuilder.builder()
                .add(true)
                .add(false)
                .add(true)
                .build()
                .getLeafValue();

        var array = new JSONArray(result);

        // Then
        assertEquals(3, array.length());
        assertTrue(array.getBoolean(0));
        assertFalse(array.getBoolean(1));
        assertTrue(array.getBoolean(2));
    }

    @Test
    void asLeaf_shouldReturnBooleanAtIndex() {
        // Given
        var arrayLeaf = ArrayBooleanLeafBuilder.builder()
                .add(true)
                .add(false)
                .add(true)
                .build();

        // When
        var result0 = arrayLeaf.asLeaf(0, Boolean.class);
        var result1 = arrayLeaf.asLeaf(1, Boolean.class);
        var result2 = arrayLeaf.asLeaf(2, Boolean.class);

        // Then
        assertTrue(result0.isPresent());
        assertEquals(true, result0.get());

        assertTrue(result1.isPresent());
        assertEquals(false, result1.get());

        assertTrue(result2.isPresent());
        assertEquals(true, result2.get());
    }

    @Test
    void asLeaf_shouldThrowIndexOutOfBoundsForInvalidIndex() {
        // Given
        var arrayLeaf = ArrayBooleanLeafBuilder.builder()
                .add(true)
                .build();

        // When/Then
        assertThrows(IndexOutOfBoundsException.class, () ->
                arrayLeaf.asLeaf(5, Boolean.class));
    }

    @Test
    void asLeaf_shouldThrowClassCastExceptionForWrongType() {
        // Given
        var arrayLeaf = ArrayBooleanLeafBuilder.builder()
                .add(true)
                .build();

        // When/Then
        assertThrows(ClassCastException.class, () ->
                arrayLeaf.asLeaf(0, String.class));
    }

    @Test
    void isEmpty_shouldReturnTrueForEmptyArray() {
        // Given
        var arrayLeaf = ArrayBooleanLeafBuilder.builder().build();

        // When/Then
        assertTrue(arrayLeaf.isEmpty());
    }

    @Test
    void isEmpty_shouldReturnFalseForNonEmptyArray() {
        // Given
        var arrayLeaf = ArrayBooleanLeafBuilder.builder()
                .add(false)
                .build();

        // When/Then
        assertFalse(arrayLeaf.isEmpty());
    }
}

