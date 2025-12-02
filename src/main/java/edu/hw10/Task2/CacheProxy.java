package edu.hw10.Task2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class CacheProxy implements InvocationHandler {
    private final Object target;
    private final Map<String, Object> cache;
    private final String cacheDirectory;

    private CacheProxy(Object target, String cacheDirectory) {
        this.target = target;
        this.cache = new HashMap<>();
        this.cacheDirectory = cacheDirectory;
    }

    public static <T> T create(T target, Class<?> targetInterface, String cacheDirectory) {
        return (T) Proxy.newProxyInstance(
            targetInterface.getClassLoader(),
            new Class<?>[]{targetInterface},
            new CacheProxy(target, cacheDirectory)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Cache cacheAnnotation = method.getAnnotation(Cache.class);
        if (cacheAnnotation != null) {
            String cacheKey = method.getName() + args[0];
            if (cache.containsKey(cacheKey)) {
                return cache.get(cacheKey);
            } else {
                Object result = method.invoke(target, args);
                cache.put(cacheKey, result);

                if (cacheAnnotation.persist()) {
                    persistResult(cacheKey, result);
                }

                return result;
            }
        } else {
            return method.invoke(target, args);
        }
    }

    private void persistResult(String cacheKey, Object result) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(cacheDirectory + "/" + cacheKey))) {
            oos.writeObject(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
