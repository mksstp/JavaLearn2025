package edu.hw10.Task1;

import edu.hw10.Task1.annotations.Max;
import edu.hw10.Task1.annotations.Min;

public record MyRecord(@Min(1) @Max(100) int myInt,
                       String myString,
                       Boolean myBoolean,
                       @Min(-100) @Max(100) Double myDouble) {
}
