package edu.basics2.Task4;

public record CallingInfo(String className, String methodName) {

    public static CallingInfo callingInfo() {
        StackTraceElement currTrace = Thread.currentThread().getStackTrace()[2];
        String fullClassName = currTrace.getClassName();
        String className = fullClassName.substring(fullClassName.lastIndexOf('.') + 1);
        String methodName = currTrace.getMethodName();
        return new CallingInfo(className, methodName);
    }
}
