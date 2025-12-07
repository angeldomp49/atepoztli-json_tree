package org.makechtec.software.json_tree.builders;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayStringLeafBuilderTest {

    @Test
    void build() {
        var result = ArrayStringLeafBuilder.builder()
                .add("Alice")
                .add("Bob")
                .add("Charlie")
                .build()
                .getLeafValue();

        var array = new JSONArray(result);

        assertEquals(3, array.length());
        assertEquals("Alice", array.getString(0));
        assertEquals("Bob", array.getString(1));
        assertEquals("Charlie", array.getString(2));
    }

    @Test
    void asLeaf_shouldReturnStringAtIndex() {
        var arrayLeaf = ArrayStringLeafBuilder.builder()
                .add("Hello")
                .add("World")
                .add("Test")
                .build();

        var result0 = arrayLeaf.asLeaf(0);
        var result1 = arrayLeaf.asLeaf(1);
        var result2 = arrayLeaf.asLeaf(2);

        assertTrue(result0.isPresent());
        assertEquals("Hello", result0.get().getLeafValue());

        assertTrue(result1.isPresent());
        assertEquals("World", result1.get().getLeafValue());

        assertTrue(result2.isPresent());
        assertEquals("Test", result2.get().getLeafValue());
    }

    @Test
    void asLeaf_shouldReturnEmptyString() {
        var arrayLeaf = ArrayStringLeafBuilder.builder()
                .add("")
                .add("non-empty")
                .build();

        var result = arrayLeaf.asLeaf(0);

        assertTrue(result.isPresent());
        assertEquals("", result.get().getLeafValue());
    }

    @Test
    void asLeaf_shouldThrowIndexOutOfBoundsForInvalidIndex() {
        var arrayLeaf = ArrayStringLeafBuilder.builder()
                .add("test")
                .build();

        assertThrows(IndexOutOfBoundsException.class, () ->
                arrayLeaf.asLeaf(5));
    }

    @Test
    void isEmpty_shouldReturnTrueForEmptyArray() {
        var arrayLeaf = ArrayStringLeafBuilder.builder().build();

        assertTrue(arrayLeaf.isEmpty());
    }

    @Test
    void isEmpty_shouldReturnTrueForArrayWithOnlyEmptyStrings() {
        var arrayLeaf = ArrayStringLeafBuilder.builder()
                .add("")
                .add("")
                .build();

        assertTrue(arrayLeaf.isEmpty());
    }

    @Test
    void isEmpty_shouldReturnFalseForNonEmptyArray() {
        var arrayLeaf = ArrayStringLeafBuilder.builder()
                .add("test")
                .build();

        assertFalse(arrayLeaf.isEmpty());
    }

    @Test
    void isEmpty_shouldReturnFalseWhenAtLeastOneStringIsNotEmpty() {
        var arrayLeaf = ArrayStringLeafBuilder.builder()
                .add("")
                .add("test")
                .add("")
                .build();

        assertFalse(arrayLeaf.isEmpty());
    }
}

