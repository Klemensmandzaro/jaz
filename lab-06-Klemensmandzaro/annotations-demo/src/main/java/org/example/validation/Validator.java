package org.example.validation;

import org.example.rules.ICheckValidationRule;

import java.util.ArrayList;
import java.util.List;

public class Validator {
    private List<ICheckValidationRule> rules = new ArrayList<ICheckValidationRule>();


    public <T> ValidationResult<T> validate(T object) {
        var result = new ValidationResult<T>();
        result.setValidatedObject(object);
        rules.stream().forEach(rule -> rule.validate(result));
        return result;

    }

    public Validator addRule(ICheckValidationRule rule) {
        rules.add(rule);
        return this;
    }
}
