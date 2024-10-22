package org.example.queries.calculations;

import org.example.model.Person;
import org.example.queries.search.Funcs;
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
        Funcs function = parameters.getFunction();

        switch (function) {
            case SUM:
                return data.stream()
                        .mapToDouble(person -> fieldGetter.apply(person).doubleValue())
                        .sum();

            case MIN:
                return data.stream()
                        .mapToDouble(person -> fieldGetter.apply(person).doubleValue())
                        .min()
                        .orElse(0);

            case MAX:
                return data.stream()
                        .mapToDouble(person -> fieldGetter.apply(person).doubleValue())
                        .max()
                        .orElse(0);

            case AVERAGE:
                return data.stream()
                        .mapToDouble(person -> fieldGetter.apply(person).doubleValue())
                        .average()
                        .orElse(0);

            default:
                return 0;
        }
    }

    @Override
    public String getFieldName() {
        return this.fieldName;
    }
}
