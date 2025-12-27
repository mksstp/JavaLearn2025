package edu.basics4;

import java.util.HashSet;
import java.util.Set;

public class Validator {
    private Validator() {
    }

    public static Set<ValidationError> getErrors(Animal a) {
        Set<ValidationError> errors = new HashSet<>();

        if (a.height() <= 0) {
            errors.add(new ValidationError("Animal can't have negative or zero height"));
        }
        if (a.weight() <= 0) {
            errors.add(new ValidationError("Animal can't have negative or zero weight"));
        }
        if (a.age() < 0) {
            errors.add(new ValidationError("Animal can't have negative age"));
        }

        return errors;
    }
}
