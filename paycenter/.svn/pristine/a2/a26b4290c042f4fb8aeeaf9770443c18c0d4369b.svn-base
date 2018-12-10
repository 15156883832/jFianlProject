package com.sf.jfinal.qs.core.event;

import com.jfinal.core.Controller;

import java.lang.reflect.Method;

public class Action {
    private final Class<? extends Controller> controllerClass;
    private final String controllerKey;
    private final String actionKey;
    private final Method method;
    private final String methodName;

    public Action(String controllerKey, String actionKey, Class<? extends Controller> controllerClass, Method method, String methodName) {
        this.controllerKey = controllerKey;
        this.actionKey = actionKey;
        this.controllerClass = controllerClass;
        this.method = method;
        this.methodName = methodName;
    }

    public Class<? extends Controller> getControllerClass() {
        return controllerClass;
    }

    public String getControllerKey() {
        return controllerKey;
    }

    public String getActionKey() {
        return actionKey;
    }

    public Method getMethod() {
        return method;
    }

    public String getMethodName() {
        return methodName;
    }
}
