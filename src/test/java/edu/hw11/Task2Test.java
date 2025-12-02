package edu.hw11;

import edu.hw11.Task2.MySumImpl;
import java.lang.reflect.Method;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Произведение вместо суммы")
    void multiplicationInsteadOfSum() {
        // given
        TypeDescription typeDescription = TypePool.Default.ofSystemLoader()
            .describe("edu.hw11.Task2.ArithmeticUtils")
            .resolve();
        Class<?> dynamicType = new ByteBuddy()
            .redefine(typeDescription, ClassFileLocator.ForClassLoader.ofSystemLoader())
            .method(ElementMatchers.named("sum"))
            .intercept(MethodDelegation.to(MySumImpl.class))
            .make()
            .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.INJECTION)
            .getLoaded();
        int answer;

        // when
        try {
            Method sumMethod = dynamicType.getMethod("sum", int.class, int.class);
            answer = (int) sumMethod.invoke(null, 5, 3);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // then
        assertThat(answer).isEqualTo(15);
    }
}
