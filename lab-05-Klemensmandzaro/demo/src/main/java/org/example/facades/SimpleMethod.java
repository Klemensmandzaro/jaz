package org.example.facades;


import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class SimpleMethod implements IMethodFacade{
    private Method method;

    public SimpleMethod(Method method) {
        this.method = method;
    }


    @Override
    public boolean isPublic() {
        return Modifier.isPublic(method.getModifiers());

    }

    @Override
    public boolean paramsCountEquals(int n) {
        return (method.getParameterCount() == n);
    }

    @Override
    public boolean startsWith(String prefix) {
        return (method.getName().startsWith(prefix));
    }

    @Override
    public boolean isVoid() {
        return method.getReturnType().equals(Void.TYPE);
    }

    @Override
    public boolean isSetter() {
        return (method.getName().startsWith("set") && method.getParameterTypes().length == 1 && Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(Void.TYPE));
    }

    @Override
    public boolean isGetter() {
        return ((method.getName().startsWith("get") || method.getName().startsWith("is")) && method.getParameterTypes().length == 0 && Modifier.isPublic(method.getModifiers()) && !method.getReturnType().equals(Void.TYPE));
    }

    @Override
    public String getFieldName() {
        //wywalić get, set lub is
        return method;
    }

    @Override
    public Method getUnderlyingMethod() {
        return null;
    }
}
