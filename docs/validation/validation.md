## Tree validation ##

### USAGE ###

You should define one attribute validation rule when they are type of number, string or boolean, whether one is a object
with more attributes, you should to define a tree validation rule with nested attribute validation rules. All keys
without
rules will be ignored.

When you like to valide tree validation rule just call the applyTo method. Rule is stateless so you can call applyTo
method
whenever times for different json strings.

### Examples ###

    @Test
    void validateIsNotPresent() throws AttributeValidationException {

        var treeRule =
            TreeValidationRule.builder()
                    .addRules(
                            TreeValidationRule.builder()
                                    .key("contact")
                                    .isRequired()
                                    .hasToBeNonNull()
                                    .addRules(
                                            AttributeValidationRule.builder()
                                                    .key("accountId")
                                                    .expectedType(AttributeExpectedType.TYPE_NUMBER)
                                                    .isRequired()
                                                    .hasToBeNonNull()
                                                    .forbiddenValue(0)
                                                    .build(),
                                            AttributeValidationRule.builder()
                                                    .key("contactId")
                                                    .expectedType(AttributeExpectedType.TYPE_NUMBER)
                                                    .isRequired()
                                                    .hasToBeNonNull()
                                                    .forbiddenValue(0)
                                                    .build()
                                    )
                                    .build(),
                            AttributeValidationRule.builder()
                                    .key("action")
                                    .isRequired()
                                    .expectedType(AttributeExpectedType.TYPE_TREE)
                                    .build(),
                            AttributeValidationRule.builder()
                                    .key("buttons")
                                    .isRequired()
                                    .expectedType(AttributeExpectedType.TYPE_ARRAY)
                                    .build()
                    )
                    .build();

        var json = """
                {
                    "action": {
                        "name": "whatsapp",
                        "id": 50
                    },
                    "buttons": [
                        {
                            "id": 5,
                            "title": "Option 1"
                        }
                    ]
                }
                """;

        var exception = assertThrows(AttributeValidationException.class, () -> treeRule.applyTo(json) );

        assertEquals(AttributeValidationExceptionType.IS_NOT_PRESENT, exception.getType());

    }

    @Test
    void validateIsNull() throws AttributeValidationException {

        var treeRule =
                TreeValidationRule.builder()
                        .addRules(
                                TreeValidationRule.builder()
                                        .key("contact")
                                        .isRequired()
                                        .hasToBeNonNull()
                                        .addRules(
                                                AttributeValidationRule.builder()
                                                        .key("accountId")
                                                        .expectedType(AttributeExpectedType.TYPE_NUMBER)
                                                        .isRequired()
                                                        .hasToBeNonNull()
                                                        .forbiddenValue(0)
                                                        .build(),
                                                AttributeValidationRule.builder()
                                                        .key("contactId")
                                                        .expectedType(AttributeExpectedType.TYPE_NUMBER)
                                                        .isRequired()
                                                        .hasToBeNonNull()
                                                        .forbiddenValue(0)
                                                        .build()
                                        )
                                        .build(),
                                AttributeValidationRule.builder()
                                        .key("action")
                                        .isRequired()
                                        .expectedType(AttributeExpectedType.TYPE_TREE)
                                        .build(),
                                AttributeValidationRule.builder()
                                        .key("buttons")
                                        .isRequired()
                                        .expectedType(AttributeExpectedType.TYPE_ARRAY)
                                        .build()
                        )
                        .build();

        var json = """
                {
                    "contact": null,
                    "action": {
                        "name": "whatsapp",
                        "id": 50
                    },
                    "buttons": [
                        {
                            "id": 5,
                            "title": "Option 1"
                        }
                    ]
                }
                """;

        var exception = assertThrows(AttributeValidationException.class, () -> treeRule.applyTo(json) );

        assertEquals(AttributeValidationExceptionType.IS_NULL, exception.getType());

    }

    @Test
    void validateIsWrongType() throws AttributeValidationException {

        var treeRule =
                TreeValidationRule.builder()
                        .addRules(
                                TreeValidationRule.builder()
                                        .key("contact")
                                        .isRequired()
                                        .hasToBeNonNull()
                                        .addRules(
                                                AttributeValidationRule.builder()
                                                        .key("accountId")
                                                        .expectedType(AttributeExpectedType.TYPE_NUMBER)
                                                        .isRequired()
                                                        .hasToBeNonNull()
                                                        .forbiddenValue(0)
                                                        .build(),
                                                AttributeValidationRule.builder()
                                                        .key("contactId")
                                                        .expectedType(AttributeExpectedType.TYPE_NUMBER)
                                                        .isRequired()
                                                        .hasToBeNonNull()
                                                        .forbiddenValue(0)
                                                        .build()
                                        )
                                        .build(),
                                AttributeValidationRule.builder()
                                        .key("action")
                                        .isRequired()
                                        .expectedType(AttributeExpectedType.TYPE_TREE)
                                        .build(),
                                AttributeValidationRule.builder()
                                        .key("buttons")
                                        .isRequired()
                                        .expectedType(AttributeExpectedType.TYPE_ARRAY)
                                        .build()
                        )
                        .build();

        var json = """
                {
                    "contact": 454,
                    "action": {
                        "name": "whatsapp",
                        "id": 50
                    },
                    "buttons": [
                        {
                            "id": 5,
                            "title": "Option 1"
                        }
                    ]
                }
                """;

        var exception = assertThrows(AttributeValidationException.class, () -> treeRule.applyTo(json) );

        assertEquals(AttributeValidationExceptionType.IS_WRONG_TYPE, exception.getType());

    }

    @Test
    void validateIsNotPresentButOK() throws AttributeValidationException {

        var treeRule =
                TreeValidationRule.builder()
                        .addRules(
                                TreeValidationRule.builder()
                                        .key("contact")
                                        .addRules(
                                                AttributeValidationRule.builder()
                                                        .key("accountId")
                                                        .expectedType(AttributeExpectedType.TYPE_NUMBER)
                                                        .isRequired()
                                                        .hasToBeNonNull()
                                                        .forbiddenValue(0)
                                                        .build(),
                                                AttributeValidationRule.builder()
                                                        .key("contactId")
                                                        .expectedType(AttributeExpectedType.TYPE_NUMBER)
                                                        .isRequired()
                                                        .hasToBeNonNull()
                                                        .forbiddenValue(0)
                                                        .build()
                                        )
                                        .build(),
                                AttributeValidationRule.builder()
                                        .key("action")
                                        .isRequired()
                                        .expectedType(AttributeExpectedType.TYPE_TREE)
                                        .build(),
                                AttributeValidationRule.builder()
                                        .key("buttons")
                                        .isRequired()
                                        .expectedType(AttributeExpectedType.TYPE_ARRAY)
                                        .build()
                        )
                        .build();

        var json = """
                {
                    "action": {
                        "name": "whatsapp",
                        "id": 50
                    },
                    "buttons": [
                        {
                            "id": 5,
                            "title": "Option 1"
                        }
                    ]
                }
                """;

        treeRule.applyTo(json);

    }

    @Test
    void validateIsNullButOK() throws AttributeValidationException {

        var treeRule =
                TreeValidationRule.builder()
                        .addRules(
                                TreeValidationRule.builder()
                                        .key("contact")
                                        .isRequired()
                                        .addRules(
                                                AttributeValidationRule.builder()
                                                        .key("accountId")
                                                        .expectedType(AttributeExpectedType.TYPE_NUMBER)
                                                        .isRequired()
                                                        .hasToBeNonNull()
                                                        .forbiddenValue(0)
                                                        .build(),
                                                AttributeValidationRule.builder()
                                                        .key("contactId")
                                                        .expectedType(AttributeExpectedType.TYPE_NUMBER)
                                                        .isRequired()
                                                        .hasToBeNonNull()
                                                        .forbiddenValue(0)
                                                        .build()
                                        )
                                        .build(),
                                AttributeValidationRule.builder()
                                        .key("action")
                                        .isRequired()
                                        .expectedType(AttributeExpectedType.TYPE_TREE)
                                        .build(),
                                AttributeValidationRule.builder()
                                        .key("buttons")
                                        .isRequired()
                                        .expectedType(AttributeExpectedType.TYPE_ARRAY)
                                        .build()
                        )
                        .build();

        var json = """
                {
                    "contact": null,
                    "action": {
                        "name": "whatsapp",
                        "id": 50
                    },
                    "buttons": [
                        {
                            "id": 5,
                            "title": "Option 1"
                        }
                    ]
                }
                """;

        treeRule.applyTo(json);

    }

## Attribute Validation ##

The rules above are availables:

- attribute is required, that means key should be present no matter it's null,
- attribute is not null, that means when key is present value must not be null,
- attribute is specific type, allowed options are: boolean, string, number,
- attribute has forbidden specific value, that means it cannot be equals to passed forbidden value

If one of those conditions are configured and broken, applyTo method will throw an _AttributeValidationException_ with
a message and corresponding type.

### USAGE ###

        var validator = AttributeValidationRule.builder()
            .key("contactId")
            .expectedType(AttributeExpectedType.TYPE_NUMBER)
            .forbiddenValue(67)
            .build();

        var json2 = """
                {
                    "contactId": 67
                }
                """;

        var exception = assertThrows(AttributeValidationException.class, () -> {
            validator.applyTo(json2);
        } );

        assertEquals(exception.getType(), AttributeValidationExceptionType.IS_FORBIDDEN_VALUE);
