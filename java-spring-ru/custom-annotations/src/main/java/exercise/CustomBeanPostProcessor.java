package exercise;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// BEGIN
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {

    private Map<Object, String> inspectingBeans = new HashMap<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomBeanPostProcessor.class);


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(Inspect.class)) {
            inspectingBeans.put(bean, beanClass.getAnnotation(Inspect.class).level());
        }

        return bean;
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (inspectingBeans.containsKey(bean)) {
            Class<?> beanClass = bean.getClass();
            return Proxy.newProxyInstance(
                    beanClass.getClassLoader(),
                    beanClass.getInterfaces(),
                    (proxy, method, args) -> {
                        if (method.getName().equals("sum") || method.getName().equals("mult")) {
                            String loggerMessage = String.format("Was called method: %s() with arguments: %s",
                                                                    method.getName(),
                                                                    Arrays.toString(args));
                            if (inspectingBeans.get(bean).equalsIgnoreCase("info")) {
                                LOGGER.info(loggerMessage);
                            } else {
                                LOGGER.debug(loggerMessage);
                            }
                            return method.invoke(bean, args);
                        } else {
                            throw new UnsupportedOperationException("Unsupported method: " + method.getName());
                        }
                    });
        }
        return bean;

    }
}
// END
