package org.example.rules;

import org.example.validation.ValidationResult;

public interface ICheckValidationRule {
   <T> void validate(ValidationResult<T> validationResult);
}
