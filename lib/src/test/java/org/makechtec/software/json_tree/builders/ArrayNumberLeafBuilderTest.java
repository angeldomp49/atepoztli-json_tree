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
        var result = ArrayNumberLeafBuilder.builder()
                .add(10)
                .add(20.5)
                .add(30L)
                .build()
                .getLeafValue();

        var array = new JSONArray(result);

        assertEquals(3, array.length());
        assertEquals(10, array.getInt(0));
        assertEquals(20.5, array.getDouble(1));
        assertEquals(30L, array.getLong(2));
    }

    @Test
    void asLeaf_shouldReturnNumberAtIndex() {
        var arrayLeaf = ArrayNumberLeafBuilder.builder()
                .add(42)
                .add(3.14)
                .add(100L)
                .build();

        var result0 = arrayLeaf.asLeaf(0);
        var result1 = arrayLeaf.asLeaf(1);
        var result2 = arrayLeaf.asLeaf(2);

        assertTrue(result0.isPresent());
        assertEquals("42", result0.get().getLeafValue());

        assertTrue(result1.isPresent());
        assertEquals("3.14", result1.get().getLeafValue());

        assertTrue(result2.isPresent());
        assertEquals("100", result2.get().getLeafValue());
    }

    @Test
    void asLeaf_shouldReturnIntegerWhenCastToInteger() {
        var arrayLeaf = ArrayNumberLeafBuilder.builder()
                .add(42)
                .add(99)
                .build();

        var result = arrayLeaf.asLeaf(0);

        assertTrue(result.isPresent());
        assertEquals("42", result.get().getLeafValue());
    }

    @Test
    void asLeaf_shouldReturnDoubleWhenCastToDouble() {
        var arrayLeaf = ArrayNumberLeafBuilder.builder()
                .add(3.14159)
                .add(2.71828)
                .build();

        var result = arrayLeaf.asLeaf(1);

        assertTrue(result.isPresent());
        assertEquals("2.71828", result.get().getLeafValue());
    }

    @Test
    void asLeaf_shouldThrowIndexOutOfBoundsForInvalidIndex() {
        var arrayLeaf = ArrayNumberLeafBuilder.builder()
                .add(1)
                .add(2)
                .build();

        assertThrows(IndexOutOfBoundsException.class, () ->
                arrayLeaf.asLeaf(10));
    }

    @Test
    void isEmpty_shouldReturnTrueForEmptyArray() {
        var arrayLeaf = ArrayNumberLeafBuilder.builder().build();

        assertTrue(arrayLeaf.isEmpty());
    }

    @Test
    void isEmpty_shouldReturnFalseForNonEmptyArray() {
        var arrayLeaf = ArrayNumberLeafBuilder.builder()
                .add(0)
                .build();

        assertFalse(arrayLeaf.isEmpty());
    }
}

