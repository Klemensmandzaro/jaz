package org.example.rules;

import org.example.annotations.NotNull;
import org.example.annotations.Range;
import org.example.validation.ValidationResult;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RangeValidationRule implements ICheckValidationRule{
    @Override
    public <T> void validate(ValidationResult<T> validationResult) {
        Class clazz = validationResult.getValidatedObject().getClass();
        var obj = validationResult.getValidatedObject();
        Stream.of(clazz.getDeclaredFields())
                .filter(f->f.isAnnotationPresent(Range.class))
                .collect(Collectors.toMap(f->f, f->f.getAnnotation(Range.class)))
                .forEach((field,annotation) ->
                {try{
                    field.setAccessible(true);
                    if((Integer) field.get(obj) < annotation.min() || (Integer) field.get(obj) > annotation.max())
                    {
                        validationResult.setValid(false);
                        validationResult.getNotValidFields().putIfAbsent(field.getName(), new ArrayList<>());
                        validationResult.getNotValidFields().get(field.getName())
                                .add(annotation.message().formatted( annotation.min(), annotation.max()));
                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
                });

    }
}
