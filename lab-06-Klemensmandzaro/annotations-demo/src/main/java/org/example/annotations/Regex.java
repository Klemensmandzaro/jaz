package org.example.annotations;

public @interface Regex {
    String message() default "";
    String pattern();
}
