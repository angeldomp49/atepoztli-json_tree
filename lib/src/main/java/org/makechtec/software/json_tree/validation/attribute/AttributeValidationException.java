package org.makechtec.software.json_tree.validation.attribute;

public class AttributeValidationException extends Exception {

    private final AttributeValidationExceptionType type;

    public AttributeValidationException(String message, AttributeValidationExceptionType type) {
        super(message);
        this.type = type;
    }

    public AttributeValidationExceptionType getType() {
        return type;
    }

}
