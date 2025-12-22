package edu.basics2;

public sealed interface Expr {
    double evaluate();

    record Constant(double value) implements Expr {
        @Override
        public double evaluate() {
            return value;
        }
    }

    record Negate(Expr value) implements Expr {

        @Override
        public double evaluate() {
            return (-1 * value.evaluate());
        }
    }

    record Exponent(Expr exp, Expr degree) implements Expr {
        public Exponent(Expr exp, double degree) {
            this(exp, new Constant(degree));
        }

        @Override
        public double evaluate() {
            return Math.pow(exp.evaluate(), degree.evaluate());
        }
    }

    record Addition(Expr summand, Expr added) implements Expr {

        @Override
        public double evaluate() {
            return (summand.evaluate() + added().evaluate());
        }
    }

    record Multiplication(Expr multipliable, Expr multiplier) implements Expr {

        @Override
        public double evaluate() {
            return (multipliable.evaluate() * multiplier.evaluate());
        }
    }
}
