package edu.basics4;

public class ValidationError extends Error {
    ValidationError(String message) {
        super(message);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ValidationError)) {
            return false;
        }

        return this.getMessage().equals(
            ((ValidationError) obj).getMessage()
        );
    }

    @Override
    public int hashCode() {
        return this.getMessage().hashCode();
    }
}
