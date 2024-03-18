package org.makechtec.software.json_tree.validation;

import org.json.JSONException;
import org.makechtec.software.json_tree.validation.attribute.AttributeValidationException;

public interface ValidationRule {

    void applyTo(String json) throws AttributeValidationException, JSONException;

}
