package org.makechtec.software.json_tree.builders;

import org.junit.jupiter.api.Test;
import org.makechtec.software.json_tree.validation.attribute.AttributeExpectedType;
import org.makechtec.software.json_tree.validation.attribute.AttributeValidationException;
import org.makechtec.software.json_tree.validation.attribute.AttributeValidationExceptionType;
import org.makechtec.software.json_tree.validation.attribute.AttributeValidationRule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AttributeValidationRuleTest {

    @Test
    public void testValidationTypeSuccess() throws AttributeValidationException {
        var validator = AttributeValidationRule.builder()
                .key("accountId")
                .expectedType(AttributeExpectedType.TYPE_NUMBER)
                .build();

        var json = """
                {
                    "accountId": 500
                }
                """;

        validator.applyTo(json);


    }

    @Test
    public void testValidationTypeWrong() {
        var validator = AttributeValidationRule.builder()
                .key("accountId")
                .expectedType(AttributeExpectedType.TYPE_NUMBER)
                .build();

        var json2 = """
                {
                    "accountId": "some"
                }
                """;

        var exception = assertThrows(AttributeValidationException.class, () -> validator.applyTo(json2));

        assertEquals(exception.getType(), AttributeValidationExceptionType.IS_WRONG_TYPE);

    }

    @Test
    public void testValidationIsPresent() {
        var validator = AttributeValidationRule.builder()
                .key("accountId")
                .expectedType(AttributeExpectedType.TYPE_NUMBER)
                .isRequired()
                .build();

        var json2 = """
                {
                    "contactId": 7576675
                }
                """;

        var exception = assertThrows(AttributeValidationException.class, () -> validator.applyTo(json2));

        assertEquals(exception.getType(), AttributeValidationExceptionType.IS_NOT_PRESENT);

    }

    @Test
    public void testValidationIsNull() {
        var validator = AttributeValidationRule.builder()
                .key("contactId")
                .expectedType(AttributeExpectedType.TYPE_NUMBER)
                .hasToBeNonNull()
                .build();

        var json2 = """
                {
                    "contactId": null
                }
                """;

        var exception = assertThrows(AttributeValidationException.class, () -> validator.applyTo(json2));

        assertEquals(exception.getType(), AttributeValidationExceptionType.IS_NULL);

    }

    @Test
    public void testValidationIsForbiddenValue() {
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

        var exception = assertThrows(AttributeValidationException.class, () -> validator.applyTo(json2));

        assertEquals(exception.getType(), AttributeValidationExceptionType.IS_FORBIDDEN_VALUE);

    }

}
