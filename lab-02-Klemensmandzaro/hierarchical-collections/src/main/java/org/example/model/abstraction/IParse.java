package org.example.model.abstraction;

public interface IParse<TResult> {
    TResult parse(String input);
}
