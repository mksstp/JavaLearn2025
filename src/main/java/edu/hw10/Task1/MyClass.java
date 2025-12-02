package edu.hw10.Task1;

import edu.hw10.Task1.annotations.Max;
import edu.hw10.Task1.annotations.Min;
import edu.hw10.Task1.annotations.NotNull;

public class MyClass {
    @NotNull
    private final int myInt;
    private final String myString;
    private final Boolean myBoolean;
    private final Double myDouble;

    MyClass(@Min(-100) @Max(100)int myInt,
        @NotNull String myString,
        Boolean myBoolean,
        @Min(-100) @Max(100)
        Double myDouble) {
        this.myInt = myInt;
        this.myString = myString;
        this.myBoolean = myBoolean;
        this.myDouble = myDouble;
    }

    public static MyClass create(@Min(-100) @Max(100) int iNumber, @Min(-100) @Max(100) double dNumber) {
        return new MyClass(iNumber, "fabricString", Boolean.TRUE, dNumber);
    }

    int getMyInt() {
        return myInt;
    }

    String getMyString() {
        return myString;
    }

    Boolean getMyBoolean() {
        return myBoolean;
    }

    Double getMyDouble() {
        return myDouble;
    }
}
