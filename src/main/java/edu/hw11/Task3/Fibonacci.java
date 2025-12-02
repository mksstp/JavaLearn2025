package edu.hw11.Task3;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import org.jetbrains.annotations.NotNull;
import static net.bytebuddy.jar.asm.Opcodes.ALOAD;
import static net.bytebuddy.jar.asm.Opcodes.F_SAME;
import static net.bytebuddy.jar.asm.Opcodes.IADD;
import static net.bytebuddy.jar.asm.Opcodes.ICONST_1;
import static net.bytebuddy.jar.asm.Opcodes.ICONST_2;
import static net.bytebuddy.jar.asm.Opcodes.IF_ICMPGT;
import static net.bytebuddy.jar.asm.Opcodes.ILOAD;
import static net.bytebuddy.jar.asm.Opcodes.INVOKEVIRTUAL;
import static net.bytebuddy.jar.asm.Opcodes.IRETURN;
import static net.bytebuddy.jar.asm.Opcodes.ISUB;

public enum Fibonacci implements ByteCodeAppender {
    INSTANCE;

    public static final String CLASS_NAME = "FibonacciClass";
    public static final String METHOD_NAME = "fib";
    public static final String VALUES_TYPES = "(I)I";

    @Override
    @SuppressWarnings("MagicNumber")
    public @NotNull Size apply(
        @NotNull MethodVisitor methodVisitor,
        Implementation.@NotNull Context context,
        @NotNull MethodDescription methodDescription) {
        methodVisitor.visitCode();
        Label label0 = new Label();
        methodVisitor.visitLabel(label0);
        methodVisitor.visitLineNumber(5, label0);
        methodVisitor.visitVarInsn(ILOAD, 1);
        methodVisitor.visitInsn(ICONST_1);
        Label label1 = new Label();
        methodVisitor.visitJumpInsn(IF_ICMPGT, label1);
        Label label2 = new Label();
        methodVisitor.visitLabel(label2);
        methodVisitor.visitLineNumber(6, label2);
        methodVisitor.visitVarInsn(ILOAD, 1);
        methodVisitor.visitInsn(IRETURN);
        methodVisitor.visitLabel(label1);
        methodVisitor.visitLineNumber(8, label1);
        methodVisitor.visitFrame(F_SAME, 0, null, 0, null);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitVarInsn(ILOAD, 1);
        methodVisitor.visitInsn(ICONST_1);
        methodVisitor.visitInsn(ISUB);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, CLASS_NAME, METHOD_NAME, VALUES_TYPES, false);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitVarInsn(ILOAD, 1);
        methodVisitor.visitInsn(ICONST_2);
        methodVisitor.visitInsn(ISUB);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, CLASS_NAME, METHOD_NAME, VALUES_TYPES, false);
        methodVisitor.visitInsn(IADD);
        methodVisitor.visitInsn(IRETURN);
        Label label3 = new Label();
        methodVisitor.visitLabel(label3);
        methodVisitor.visitLocalVariable("n", "I", null, label0, label3, 1);
        methodVisitor.visitMaxs(4, 2);
        methodVisitor.visitEnd();
        return new Size(4, 2);
    }
}
