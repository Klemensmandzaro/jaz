package org.example;

import java.time.LocalDateTime;

@FunctionalInterface
public interface IProvideNextExecutionTime {
    LocalDateTime provideNextExecutionTime();

}
