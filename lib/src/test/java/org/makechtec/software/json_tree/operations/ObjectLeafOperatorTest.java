package org.makechtec.software.json_tree.operations;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.makechtec.software.json_tree.ObjectLeaf;
import org.makechtec.software.json_tree.builders.ObjectLeafBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        ObjectLeaf leaf1 = ObjectLeafBuilder.builder()
                .put("id", 1)
                .put("name", "Alice")
                .build();

        ObjectLeaf leaf2 = ObjectLeafBuilder.builder()
                .put("age", 25)
                .put("active", true)
                .build();

        ObjectLeaf merged = operator.merge(leaf1, leaf2);

        var nameResult = merged.asLeaf("name");
        var idResult = merged.asLeaf("id");
        var ageResult = merged.asLeaf("age");
        var activeResult = merged.asLeaf("active");

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
        ObjectLeaf leaf1 = ObjectLeafBuilder.builder()
                .put("status", "pending")
                .build();

        ObjectLeaf leaf2 = ObjectLeafBuilder.builder()
                .put("status", "approved")
                .build();

        ObjectLeaf merged = operator.merge(leaf1, leaf2);

        var statusResult = merged.asLeaf("status");

        assertTrue(statusResult.isPresent());
        assertEquals("\"approved\"", statusResult.get().getLeafValue());
    }

    @Test
    void merge_shouldHandleNestedObjects() {
        ObjectLeaf nestedLeaf = ObjectLeafBuilder.builder()
                .put("street", "123 Main St")
                .put("city", "Boston")
                .build();

        ObjectLeaf leaf1 = ObjectLeafBuilder.builder()
                .put("id", 1)
                .put("address", nestedLeaf)
                .build();

        ObjectLeaf leaf2 = ObjectLeafBuilder.builder()
                .put("name", "Alice")
                .build();

        ObjectLeaf result = operator.merge(leaf1, leaf2);

        JSONObject json = new JSONObject(result.getLeafValue());
        assertEquals(1, json.getInt("id"));
        assertEquals("Alice", json.getString("name"));
        assertTrue(json.has("address"));
    }

    @Test
    void merge_shouldHandleEmptyLeaves() {
        ObjectLeaf emptyLeaf1 = ObjectLeafBuilder.builder().build();
        ObjectLeaf emptyLeaf2 = ObjectLeafBuilder.builder().build();

        ObjectLeaf result = operator.merge(emptyLeaf1, emptyLeaf2);

        assertNotNull(result);
        assertEquals("{}", result.getLeafValue());
    }

    @Test
    void merge_shouldPreserveLastValueForDuplicateKeys() {
        ObjectLeaf leaf1 = ObjectLeafBuilder.builder()
                .put("key", "first")
                .build();

        ObjectLeaf leaf2 = ObjectLeafBuilder.builder()
                .put("key", "second")
                .build();

        ObjectLeaf leaf3 = ObjectLeafBuilder.builder()
                .put("key", "third")
                .build();

        ObjectLeaf result = operator.merge(leaf1, leaf2, leaf3);

        JSONObject json = new JSONObject(result.getLeafValue());
        assertEquals("third", json.getString("key"));
    }

    @Test
    void extractStringValue_shouldReturnStringForStringLeaf() {
        ObjectLeaf leaf = ObjectLeafBuilder.builder()
                .put("name", "John Doe")
                .build();

        var nameLeaf = leaf.asLeaf("name");
        assertTrue(nameLeaf.isPresent());

        var result = operator.extractStringValue(nameLeaf.get());

        assertTrue(result.isPresent());
        assertEquals("\"John Doe\"", result.get());
    }

    @Test
    void extractStringValue_shouldReturnEmptyForNonStringLeaf() {
        ObjectLeaf leaf = ObjectLeafBuilder.builder()
                .put("count", 42)
                .build();

        var countLeaf = leaf.asLeaf("count");
        assertTrue(countLeaf.isPresent());

        var result = operator.extractStringValue(countLeaf.get());

        assertTrue(result.isEmpty());
    }

    @Test
    void extractBooleanValue_shouldReturnBooleanForBooleanLeaf() {
        ObjectLeaf leaf = ObjectLeafBuilder.builder()
                .put("isActive", true)
                .build();

        var activeLeaf = leaf.asLeaf("isActive");
        assertTrue(activeLeaf.isPresent());

        var result = operator.extractBooleanValue(activeLeaf.get());

        assertTrue(result.isPresent());
        assertTrue(result.get());
    }

    @Test
    void extractBooleanValue_shouldReturnFalseForFalseBooleanLeaf() {
        ObjectLeaf leaf = ObjectLeafBuilder.builder()
                .put("isActive", false)
                .build();

        var activeLeaf = leaf.asLeaf("isActive");
        assertTrue(activeLeaf.isPresent());

        var result = operator.extractBooleanValue(activeLeaf.get());

        assertTrue(result.isPresent());
        assertFalse(result.get());
    }

    @Test
    void extractBooleanValue_shouldReturnEmptyForNonBooleanLeaf() {
        ObjectLeaf leaf = ObjectLeafBuilder.builder()
                .put("name", "test")
                .build();

        var nameLeaf = leaf.asLeaf("name");
        assertTrue(nameLeaf.isPresent());

        var result = operator.extractBooleanValue(nameLeaf.get());

        assertTrue(result.isEmpty());
    }

    @Test
    void extractLongValue_shouldReturnDoubleForNumberLeaf() {
        ObjectLeaf leaf = ObjectLeafBuilder.builder()
                .put("price", 99.99)
                .build();

        var priceLeaf = leaf.asLeaf("price");
        assertTrue(priceLeaf.isPresent());

        var result = operator.extractLongValue(priceLeaf.get());

        assertTrue(result.isPresent());
        assertEquals(99.99, result.get(), 0.001);
    }

    @Test
    void extractLongValue_shouldReturnIntegerAsDouble() {
        ObjectLeaf leaf = ObjectLeafBuilder.builder()
                .put("count", 42)
                .build();

        var countLeaf = leaf.asLeaf("count");
        assertTrue(countLeaf.isPresent());

        var result = operator.extractLongValue(countLeaf.get());

        assertTrue(result.isPresent());
        assertEquals(42.0, result.get(), 0.001);
    }

    @Test
    void extractLongValue_shouldReturnEmptyForNonNumberLeaf() {
        ObjectLeaf leaf = ObjectLeafBuilder.builder()
                .put("name", "test")
                .build();

        var nameLeaf = leaf.asLeaf("name");
        assertTrue(nameLeaf.isPresent());

        var result = operator.extractLongValue(nameLeaf.get());

        assertTrue(result.isEmpty());
    }

    @Test
    void merge_shouldHandleMixedTypesInMultipleLeaves() {
        ObjectLeaf leaf1 = ObjectLeafBuilder.builder()
                .put("id", 1)
                .put("name", "Product A")
                .build();

        ObjectLeaf leaf2 = ObjectLeafBuilder.builder()
                .put("price", 29.99)
                .put("inStock", true)
                .build();

        ObjectLeaf leaf3 = ObjectLeafBuilder.builder()
                .put("category", "Electronics")
                .put("quantity", 100)
                .build();

        ObjectLeaf result = operator.merge(leaf1, leaf2, leaf3);

        JSONObject json = new JSONObject(result.getLeafValue());
        assertEquals(1, json.getInt("id"));
        assertEquals("Product A", json.getString("name"));
        assertEquals(29.99, json.getDouble("price"), 0.001);
        assertTrue(json.getBoolean("inStock"));
        assertEquals("Electronics", json.getString("category"));
        assertEquals(100, json.getInt("quantity"));
    }
}


