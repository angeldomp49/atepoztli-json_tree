## Requirements ##

- Java 17+

___

## Dependency ##

### Maven Dependency ###

    <dependency>
        <groupId>org.makechtec.software</groupId>
        <artifactId>json_tree</artifactId>
        <version>2.0.0</version>
    </dependency>

### Gradle for groovy ###

    implementation 'org.makechtec.software:json_tree:2.0.0'

### Gradle for kotlin ###

    implementation ("org.makechtec.software:json_tree:2.0.0")

___

## Usage ##

Examples:

    var item =
                ObjectLeaftBuilder.builder()
                        .put("id", 1)
                        .put("name", "Jhon")
                        .put("hasPassed", false)
                        .build();

        var result =
                ArrayLeafBuilder.builder()
                        .add(item)
                        .add(item)
                        .build()
                        .getLeafValue();

        var obj = new JSONArray(result);

        assertEquals("Jhon", obj.getJSONObject(0).getString("name"));
        assertEquals(1, obj.getJSONObject(0).getInt("id"));
        assertFalse(obj.getJSONObject(0).getBoolean("hasPassed"));

___

## Documentation ##

For detailed usage examples and guides, see the documentation in the `docs` folder:

- [Value Retrieval](docs/retrieving-values.md) - Learn how to retrieve values from ObjectLeaf and Array leaves
- [Object Leaf Operations](docs/object-leaf-operations.md) - Learn how to merge objects and extract typed values
- [Validation](docs/validation/validation.md) - Learn how to validate JSON structures

___

### Validation ###