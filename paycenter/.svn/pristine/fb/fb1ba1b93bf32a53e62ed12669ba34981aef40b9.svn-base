package com.sf.jfinal.qs.core.plugins;

import com.sf.jfinal.qs.core.event.Action;
import com.jfinal.config.Routes;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.IPlugin;

import java.lang.reflect.Method;
import java.util.*;

public class ActionAwarePlugin implements IPlugin {
    private static final String SLASH = "/";
    private final Routes routes;
    private List<Action> actions = new ArrayList<>();
    private List<ActionListener> actionListeners = new ArrayList<>();

    public interface ActionListener {
        void onActionEvent(Action action);
        void onActionScanned(List<Action> actions);
    }

    public void addActionListener(ActionListener listener) {
        actionListeners.add(listener);
    }

    public ActionAwarePlugin(Routes routes) {
        this.routes = routes;
    }

    public boolean start() {
        Set<String> excludedMethod = buildExcludedMethod();
        //逐个访问所有注册的Controller，解析Controller及action上的所有Shiro注解。
        //并依据这些注解，actionKey提前构建好权限检查处理器。
        for (Map.Entry<String, Class<? extends Controller>> entry : routes.getEntrySet()) {
            Class<? extends Controller> controllerClass = entry.getValue();
            String controllerKey = entry.getKey();
            Method[] methods = controllerClass.getMethods();
            for (Method method : methods) {
                //排除掉Controller基类的所有方法，并且只关注没有参数的Action方法。
                if (!excludedMethod.contains(method.getName()) && method.getParameterTypes().length == 0) {
                    //若该方法上存在ClearShiro注解，则对该action不进行访问控制检查。
                    String actionKey = createActionKey(controllerClass, method, controllerKey);
                    Action action = new Action(controllerKey, actionKey, controllerClass, method, method.getName());
                    actions.add(action);
                    for (ActionListener listener : actionListeners) {
                        listener.onActionEvent(action);
                    }
                }
            }
        }
        for (ActionListener listener : actionListeners) {
            listener.onActionScanned(actions);
        }
        return true;
    }

    private Set<String> buildExcludedMethod() {
        Set<String> excludedMethod = new HashSet<String>();
        Method[] methods = Controller.class.getMethods();
        for (Method m : methods) {
            if (m.getParameterTypes().length == 0) {
                excludedMethod.add(m.getName());
            }
        }
        return excludedMethod;
    }

    private String createActionKey(Class<? extends Controller> controllerClass, Method method, String controllerKey) {
        String actionKey;
        String methodName = method.getName();
        ActionKey ak = method.getAnnotation(ActionKey.class);
        if (ak != null) {
            actionKey = ak.value().trim();
            if ("".equals(actionKey)) {
                String error = "%s(%s): The argument of ActionKey can not be blank!";
                throw new RuntimeException(String.format(error, controllerClass.getName(), methodName));
            }
            if (!actionKey.startsWith(SLASH)) {
                actionKey = SLASH + actionKey;
            }
        } else if (methodName.equals("index")) {
            actionKey = controllerKey;
        } else {
            actionKey = controllerKey.equals(SLASH) ? SLASH + methodName : controllerKey + SLASH + methodName;
        }
        return actionKey;
    }

    public boolean stop() {
        return true;
    }
}
