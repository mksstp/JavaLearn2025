package edu.hw11;

import edu.hw11.Task3.FibonacciImpl;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.description.modifier.Visibility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    @DisplayName("Вычисление Фибонначи")
    void fibonnaciNumbers() {
        // given
        Class<?> dynamicType = new ByteBuddy(ClassFileVersion.JAVA_V21)
            .subclass(Object.class)
            .name("FibonacciClass")
            .defineMethod("fib", int.class, Visibility.PUBLIC)
            .withParameter(int.class, "n")
            .intercept(FibonacciImpl.INSTANCE)
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();
        int answer;

        // when
        try {
            Object instance = dynamicType.getConstructor().newInstance();
            answer = (int) dynamicType.getMethod("fib", int.class).invoke(instance, 10);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // then
        assertThat(answer).isEqualTo(55);
    }
}
