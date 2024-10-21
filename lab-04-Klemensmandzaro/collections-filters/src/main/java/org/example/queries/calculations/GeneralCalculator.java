package org.example.queries.calculations;

import org.example.model.Person;
import org.example.queries.search.FunctionsParameters;

import java.util.List;
import java.util.function.Function;

public class GeneralCalculator implements ICalculate{
    String fieldName;
    Function<Person, Number> fieldGetter;

    public GeneralCalculator(String fieldName, Function<Person, Number> fieldGetter) {
        this.fieldName = fieldName;
        this.fieldGetter = fieldGetter;
    }

    @Override
    public double calculate(FunctionsParameters parameters, List<Person> data) {
        return 0;
    }

    @Override
    public String getFieldName() {
        return this.fieldName;
    }
}
