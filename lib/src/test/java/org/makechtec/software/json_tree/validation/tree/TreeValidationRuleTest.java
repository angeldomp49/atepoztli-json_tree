package org.makechtec.software.json_tree.validation.tree;

import org.junit.jupiter.api.Test;
import org.makechtec.software.json_tree.validation.attribute.AttributeExpectedType;
import org.makechtec.software.json_tree.validation.attribute.AttributeValidationException;
import org.makechtec.software.json_tree.validation.attribute.AttributeValidationExceptionType;
import org.makechtec.software.json_tree.validation.attribute.AttributeValidationRule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TreeValidationRuleTest {

    @Test
    void validateIsNotPresent() {

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

        var exception = assertThrows(AttributeValidationException.class, () -> treeRule.applyTo(json));

        assertEquals(AttributeValidationExceptionType.IS_NOT_PRESENT, exception.getType());

    }

    @Test
    void validateIsNull() {

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

        var exception = assertThrows(AttributeValidationException.class, () -> treeRule.applyTo(json));

        assertEquals(AttributeValidationExceptionType.IS_NULL, exception.getType());

    }

    @Test
    void validateIsWrongType() {

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

        var exception = assertThrows(AttributeValidationException.class, () -> treeRule.applyTo(json));

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

}