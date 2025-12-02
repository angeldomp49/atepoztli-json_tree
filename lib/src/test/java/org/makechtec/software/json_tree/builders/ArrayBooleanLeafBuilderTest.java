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
        var result = ArrayBooleanLeafBuilder.builder()
                .add(true)
                .add(false)
                .add(true)
                .build()
                .getLeafValue();

        var array = new JSONArray(result);

        assertEquals(3, array.length());
        assertTrue(array.getBoolean(0));
        assertFalse(array.getBoolean(1));
        assertTrue(array.getBoolean(2));
    }

    @Test
    void asLeaf_shouldReturnBooleanAtIndex() {
        var arrayLeaf = ArrayBooleanLeafBuilder.builder()
                .add(true)
                .add(false)
                .add(true)
                .build();

        var result0 = arrayLeaf.asLeaf(0);
        var result1 = arrayLeaf.asLeaf(1);
        var result2 = arrayLeaf.asLeaf(2);

        assertTrue(result0.isPresent());
        assertEquals("true", result0.get().getLeafValue());

        assertTrue(result1.isPresent());
        assertEquals("false", result1.get().getLeafValue());

        assertTrue(result2.isPresent());
        assertEquals("true", result2.get().getLeafValue());
    }

    @Test
    void asLeaf_shouldThrowIndexOutOfBoundsForInvalidIndex() {
        var arrayLeaf = ArrayBooleanLeafBuilder.builder()
                .add(true)
                .build();

        assertThrows(IndexOutOfBoundsException.class, () ->
                arrayLeaf.asLeaf(5));
    }

    @Test
    void isEmpty_shouldReturnTrueForEmptyArray() {
        var arrayLeaf = ArrayBooleanLeafBuilder.builder().build();

        assertTrue(arrayLeaf.isEmpty());
    }

    @Test
    void isEmpty_shouldReturnFalseForNonEmptyArray() {
        var arrayLeaf = ArrayBooleanLeafBuilder.builder()
                .add(false)
                .build();

        assertFalse(arrayLeaf.isEmpty());
    }
}
