package org.makechtec.software.json_tree.operations;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.makechtec.software.json_tree.ObjectLeaf;
import org.makechtec.software.json_tree.builders.ObjectLeafBuilder;
import org.makechtec.software.json_tree.primitives.BooleanJSONLeaf;
import org.makechtec.software.json_tree.primitives.NumberJSONLeaf;
import org.makechtec.software.json_tree.primitives.StringJSONLeaf;

import static org.junit.jupiter.api.Assertions.*;

class ObjectLeafOperatorTest {

    private ObjectLeafOperator operator;

    @BeforeEach
    void setUp() {
        operator = new ObjectLeafOperator();
    }

    @Test
    void merge_shouldMergeTwoObjectLeaves() {
        // Given
        ObjectLeaf leaf1 = ObjectLeafBuilder.builder()
                .put("id", 1)
                .put("name", "John")
                .build();

        ObjectLeaf leaf2 = ObjectLeafBuilder.builder()
                .put("age", 30)
                .put("city", "New York")
                .build();

        // When
        ObjectLeaf result = operator.merge(leaf1, leaf2);

        // Then
        JSONObject json = new JSONObject(result.getLeafValue());
        assertEquals(1, json.getInt("id"));
        assertEquals("John", json.getString("name"));
        assertEquals(30, json.getInt("age"));
        assertEquals("New York", json.getString("city"));
    }

    @Test
    void merge_shouldOverwriteDuplicateKeys() {
        // Given
        ObjectLeaf leaf1 = ObjectLeafBuilder.builder()
                .put("id", 1)
                .put("name", "John")
                .build();

        ObjectLeaf leaf2 = ObjectLeafBuilder.builder()
                .put("id", 2)
                .put("age", 30)
                .build();

        // When
        ObjectLeaf result = operator.merge(leaf1, leaf2);

        // Then
        JSONObject json = new JSONObject(result.getLeafValue());
        assertEquals(2, json.getInt("id")); // Should be overwritten by leaf2
        assertEquals("John", json.getString("name"));
        assertEquals(30, json.getInt("age"));
    }

    @Test
    void merge_shouldHandleNullArray() {
        // When
        ObjectLeaf result = operator.merge((ObjectLeaf[]) null);

        // Then
        assertNotNull(result);
        assertEquals("{}", result.getLeafValue());
    }

    @Test
    void merge_shouldHandleNullElementsInArray() {
        // Given
        ObjectLeaf leaf1 = ObjectLeafBuilder.builder()
                .put("id", 1)
                .build();

        ObjectLeaf leaf3 = ObjectLeafBuilder.builder()
                .put("name", "Jane")
                .build();

        // When
        ObjectLeaf result = operator.merge(leaf1, null, leaf3);

        // Then
        JSONObject json = new JSONObject(result.getLeafValue());
        assertEquals(1, json.getInt("id"));
        assertEquals("Jane", json.getString("name"));
    }

    @Test
    void merge_shouldReturnEmptyObjectWhenNoLeavesProvided() {
        // When
        ObjectLeaf result = operator.merge();

        // Then
        assertNotNull(result);
        assertEquals("{}", result.getLeafValue());
    }

    @Test
    void merge_shouldHandleSingleLeaf() {
        // Given
        ObjectLeaf leaf = ObjectLeafBuilder.builder()
                .put("id", 1)
                .put("name", "John")
                .build();

        // When
        ObjectLeaf result = operator.merge(leaf);

        // Then
        JSONObject json = new JSONObject(result.getLeafValue());
        assertEquals(1, json.getInt("id"));
        assertEquals("John", json.getString("name"));
    }

    @Test
    void merge_shouldMergeMultipleLeaves() {
        // Given
        ObjectLeaf leaf1 = ObjectLeafBuilder.builder()
                .put("field1", "value1")
                .build();

        ObjectLeaf leaf2 = ObjectLeafBuilder.builder()
                .put("field2", "value2")
                .build();

        ObjectLeaf leaf3 = ObjectLeafBuilder.builder()
                .put("field3", "value3")
                .build();

        ObjectLeaf leaf4 = ObjectLeafBuilder.builder()
                .put("field4", "value4")
                .build();

        // When
        ObjectLeaf result = operator.merge(leaf1, leaf2, leaf3, leaf4);

        // Then
        JSONObject json = new JSONObject(result.getLeafValue());
        assertEquals("value1", json.getString("field1"));
        assertEquals("value2", json.getString("field2"));
        assertEquals("value3", json.getString("field3"));
        assertEquals("value4", json.getString("field4"));
    }

    @Test
    void merge_shouldHandleDifferentDataTypes() {
        // Given
        ObjectLeaf leaf1 = ObjectLeafBuilder.builder()
                .put("stringField", "text")
                .put("numberField", 42)
                .build();

        ObjectLeaf leaf2 = ObjectLeafBuilder.builder()
                .put("booleanField", true)
                .put("decimalField", 3.14)
                .build();

        // When
        ObjectLeaf result = operator.merge(leaf1, leaf2);

        // Then
        JSONObject json = new JSONObject(result.getLeafValue());
        assertEquals("text", json.getString("stringField"));
        assertEquals(42, json.getInt("numberField"));
        assertTrue(json.getBoolean("booleanField"));
        assertEquals(3.14, json.getDouble("decimalField"), 0.001);
    }

    @Test
    void asLeaf_shouldRetrieveFieldFromMergedObjectLeaf() {
        // Given
        ObjectLeaf leaf1 = ObjectLeafBuilder.builder()
                .put("id", 1)
                .put("name", "Alice")
                .build();

        ObjectLeaf leaf2 = ObjectLeafBuilder.builder()
                .put("age", 25)
                .put("active", true)
                .build();

        ObjectLeaf merged = operator.merge(leaf1, leaf2);

        // When
        var nameResult = merged.asLeaf("name", StringJSONLeaf.class);
        var idResult = merged.asLeaf("id", NumberJSONLeaf.class);
        var ageResult = merged.asLeaf("age", NumberJSONLeaf.class);
        var activeResult = merged.asLeaf("active", BooleanJSONLeaf.class);

        // Then
        assertTrue(nameResult.isPresent());
        assertEquals("\"Alice\"", nameResult.get().getLeafValue());

        assertTrue(idResult.isPresent());
        assertEquals("1", idResult.get().getLeafValue());

        assertTrue(ageResult.isPresent());
        assertEquals("25", ageResult.get().getLeafValue());

        assertTrue(activeResult.isPresent());
        assertEquals("true", activeResult.get().getLeafValue());
    }

    @Test
    void asLeaf_shouldRetrieveOverwrittenFieldValue() {
        // Given
        ObjectLeaf leaf1 = ObjectLeafBuilder.builder()
                .put("status", "pending")
                .build();

        ObjectLeaf leaf2 = ObjectLeafBuilder.builder()
                .put("status", "approved")
                .build();

        ObjectLeaf merged = operator.merge(leaf1, leaf2);

        // When
        var statusResult = merged.asLeaf("status", StringJSONLeaf.class);

        // Then
        assertTrue(statusResult.isPresent());
        assertEquals("\"approved\"", statusResult.get().getLeafValue());
    }
}

