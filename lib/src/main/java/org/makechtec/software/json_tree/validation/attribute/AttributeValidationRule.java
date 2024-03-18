package org.makechtec.software.json_tree.validation.attribute;

import org.json.JSONException;
import org.json.JSONObject;
import org.makechtec.software.json_tree.validation.ValidationRule;

import java.util.Objects;

public class AttributeValidationRule implements ValidationRule {

    private AttributeExpectedType expectedType;
    private boolean hasToBeNonNull = false;
    private boolean isRequired = false;
    private Object forbiddenValue;
    private String key = "";

    public static AttributeValidationRuleBuilder builder() {
        return new AttributeValidationRuleBuilder();
    }

    @Override
    public void applyTo(String json) throws AttributeValidationException, JSONException {

        var isPresentExpectedType = Objects.nonNull(expectedType);

        if (!isPresentExpectedType) {
            throw new AttributeValidationException("Expected attribute type is not present", AttributeValidationExceptionType.BAD_VALIDATION);
        }

        if (key.isEmpty()) {
            throw new AttributeValidationException(String.format("%s cannot be empty string", key), AttributeValidationExceptionType.BAD_VALIDATION);
        }


        var jsonObject = new JSONObject(json);

        try {
            var result = jsonObject.get(key);

            if (hasToBeNonNull && JSONObject.NULL.equals(result)) {
                throw new AttributeValidationException(String.format("Attribute %s is NULL but it's not allowed", key), AttributeValidationExceptionType.IS_NULL);
            } else if (JSONObject.NULL.equals(result)) {
                return;
            }
        } catch (JSONException e) {
            if (isRequired) {
                throw new AttributeValidationException(String.format("Attribute %s is not present but required", key), AttributeValidationExceptionType.IS_NOT_PRESENT);
            }
            return;
        }

        switch (expectedType) {
            case TYPE_BOOLEAN -> {
                try {
                    var result = jsonObject.getBoolean(key);

                    if (result == (boolean) forbiddenValue) {
                        throw new AttributeValidationException(String.format("Attribute %s is forbidden", key), AttributeValidationExceptionType.IS_FORBIDDEN_VALUE);
                    }

                } catch (JSONException e) {
                    throw new AttributeValidationException(String.format("Wrong type for attribute %s, must be %s", key, "boolean"), AttributeValidationExceptionType.IS_WRONG_TYPE);
                }
            }
            case TYPE_NUMBER -> {
                try {
                    var result = jsonObject.getNumber(key);

                    if (result.equals(forbiddenValue)) {
                        throw new AttributeValidationException(String.format("Attribute %s is forbidden", key), AttributeValidationExceptionType.IS_FORBIDDEN_VALUE);
                    }
                } catch (JSONException e) {
                    throw new AttributeValidationException(String.format("Wrong type for attribute %s, must be %s", key, "number"), AttributeValidationExceptionType.IS_WRONG_TYPE);
                }
            }
            case TYPE_STRING -> {
                try {
                    var result = jsonObject.getString(key);

                    if (result.equals(forbiddenValue)) {
                        throw new AttributeValidationException(String.format("Attribute %s is forbidden", key), AttributeValidationExceptionType.IS_FORBIDDEN_VALUE);
                    }
                } catch (JSONException e) {
                    throw new AttributeValidationException(String.format("Wrong type for attribute %s, must be %s", key, "string"), AttributeValidationExceptionType.IS_WRONG_TYPE);
                }
            }
            case TYPE_TREE -> {
                try {
                    jsonObject.getJSONObject(key);

                } catch (JSONException e) {
                    throw new AttributeValidationException(String.format("Wrong type for attribute %s, must be %s", key, "object tree"), AttributeValidationExceptionType.IS_WRONG_TYPE);
                }
            }
            case TYPE_ARRAY -> {
                try {
                    jsonObject.getJSONArray(key);

                } catch (JSONException e) {
                    throw new AttributeValidationException(String.format("Wrong type for attribute %s, must be %s", key, "array"), AttributeValidationExceptionType.IS_WRONG_TYPE);
                }
            }
        }

    }

    public static class AttributeValidationRuleBuilder {
        private final AttributeValidationRule rule;

        public AttributeValidationRuleBuilder() {
            this.rule = new AttributeValidationRule();
        }

        public AttributeValidationRule build() {
            return rule;
        }

        public AttributeValidationRuleBuilder isRequired() {
            this.rule.isRequired = true;
            return this;
        }

        public AttributeValidationRuleBuilder hasToBeNonNull() {
            this.rule.hasToBeNonNull = true;
            return this;
        }

        public AttributeValidationRuleBuilder key(String key) {
            this.rule.key = key;
            return this;
        }

        public AttributeValidationRuleBuilder expectedType(AttributeExpectedType expectedType) {
            this.rule.expectedType = expectedType;
            return this;
        }

        public AttributeValidationRuleBuilder forbiddenValue(Object value) {
            this.rule.forbiddenValue = value;
            return this;
        }
    }

}
