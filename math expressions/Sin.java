public class Sin extends Node {
    double base;

    public Sin(double a) {
        base = Math.toRadians(a);
    }

    double evaluate() {
        return Math.sin(base);
    }

    public String toString() {
        return "sin(" + Math.toDegrees(base) + ")";
    }

    @Override
    Node diff(Variable var) {
        return new Cos(base);
    }

    @Override
    boolean isZero() {
        return Math.abs(base) < 1e-9;
    }
}