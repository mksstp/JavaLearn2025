package edu.hw10.Task1;

import edu.hw10.Task1.annotations.Max;
import edu.hw10.Task1.annotations.Min;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

public class RandomObjectGenerator {
    private static final int DEFAULT_MIN_VALUE = -1000;
    private static final int DEFAULT_MAX_VALUE = 1000;

    public <T> T nextObject(Class<T> clazz, String factoryMethodName) {
        try {
            Method factoryMethod = findFactoryMethod(clazz, factoryMethodName);
            Object[] args = generateRandomArgs(factoryMethod.getParameters());
            return (T) factoryMethod.invoke(null, args);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private <T> Method findFactoryMethod(Class<T> clazz, String factoryMethodName) throws NoSuchMethodException {
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getName().equals(factoryMethodName)) {
                return method;
            }
        }
        throw new NoSuchMethodException(factoryMethodName);
    }

    private Object[] generateRandomArgs(Parameter[] parameters) {
        return Arrays.stream(parameters)
            .map(parameter -> generateValue(parameter.getType(), parameter.getAnnotations()))
            .toArray();
    }


    public <T> T nextObject(Class<T> clazz) {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        if (constructors.length > 0) {
            Constructor<?> firstConstructor = constructors[0];
            firstConstructor.setAccessible(true);
            return (T) createObject(firstConstructor);
        } else {
            throw new IllegalArgumentException("У класса должен быть хотя бы один конструктор");
        }
    }

    private <T> T createObject(Constructor<T> constructor) {
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        Annotation[][] parameterAnnotations = constructor.getParameterAnnotations();
        Object[] args = IntStream.range(0, parameterTypes.length)
            .mapToObj(i -> generateValue(parameterTypes[i], parameterAnnotations[i]))
            .toArray();

        try {
            return constructor.newInstance(args);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private Object generateValue(Class<?> type, Annotation[] annotations) {
        return switch (type.getSimpleName()) {
            case "int", "Integer" -> generateIntValue(annotations);
            case "String" -> generateStringValue();
            case "boolean", "Boolean" -> generateBooleanValue();
            case "double", "Double" -> generateDoubleValue(annotations);
            default -> throw new IllegalArgumentException("Неподдерживаемый тип: " + type);
        };
    }

    private int generateIntValue(Annotation[] annotations) {
        int minValue = getMinValue(annotations);
        int maxValue = getMaxValue(annotations);
        return new Random().nextInt((maxValue - minValue)) + minValue;
    }

    private String generateStringValue() {
        return UUID.randomUUID().toString();
    }

    private boolean generateBooleanValue() {
        return new Random().nextBoolean();
    }

    private double generateDoubleValue(Annotation[] annotations) {
        double minValue = getMinValue(annotations);
        double maxValue = getMaxValue(annotations);

        return new Random().nextDouble() * (maxValue - minValue) + minValue;
    }

    private int getMinValue(Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min) {
                return ((Min) annotation).value();
            }
        }
        return  DEFAULT_MIN_VALUE;
    }

    private int getMaxValue(Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            if (annotation instanceof Max) {
                return ((Max) annotation).value();
            }
        }
        return DEFAULT_MAX_VALUE;
    }
}
