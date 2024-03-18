package org.makechtec.software.json_tree.validation.tree;

import org.json.JSONException;
import org.json.JSONObject;
import org.makechtec.software.json_tree.validation.ValidationRule;
import org.makechtec.software.json_tree.validation.attribute.AttributeValidationException;
import org.makechtec.software.json_tree.validation.attribute.AttributeValidationExceptionType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TreeValidationRule implements ValidationRule {

    private final Set<ValidationRule> validationRules = new HashSet<>();
    private String key = "";
    private boolean isRequired = false;
    private boolean hasToBeNonNull = false;

    public static TreeValidationRuleBuilder builder() {
        return new TreeValidationRuleBuilder();
    }

    public void addRules(ValidationRule... rules) {
        validationRules.addAll(Arrays.asList(rules));
    }

    @Override
    public void applyTo(String json) throws AttributeValidationException, JSONException {

        var subTree = json;
        if (!key.isEmpty()) {

            var tree = new JSONObject(json);

            try {
                var result = tree.get(key);

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

            try {
                tree.getJSONObject(key);
            } catch (JSONException e) {
                throw new AttributeValidationException(String.format("Wrong type for attribute %s, must be %s", key, "object"), AttributeValidationExceptionType.IS_WRONG_TYPE);
            }

            subTree = tree.getJSONObject(key).toString();
        }

        for (var rule : validationRules) {
            rule.applyTo(subTree);
        }
    }

    public static class TreeValidationRuleBuilder {

        private final TreeValidationRule rule;

        public TreeValidationRuleBuilder() {
            rule = new TreeValidationRule();
        }

        public TreeValidationRule build() {
            return rule;
        }

        public TreeValidationRuleBuilder key(String key) {
            rule.key = key;
            return this;
        }

        public TreeValidationRuleBuilder hasToBeNonNull() {
            rule.hasToBeNonNull = true;
            return this;
        }

        public TreeValidationRuleBuilder isRequired() {
            rule.isRequired = true;
            return this;
        }

        public TreeValidationRuleBuilder addRules(ValidationRule... rules) {
            this.rule.addRules(rules);
            return this;
        }

    }

}
