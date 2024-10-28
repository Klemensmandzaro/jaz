package org.example.facades;


import java.lang.reflect.Method;

public interface IMethodFacade {
    boolean isPublic();
    boolean paramsCountEquals(int n);
    boolean startsWith(String prefix);
    boolean isVoid();
    boolean isSetter();
    boolean isGetter();
    String getFieldName();
    Method getUnderlyingMethod();
}
