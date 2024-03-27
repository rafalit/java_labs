public class Cos extends Node {
    double base;

    public Cos(double a) {
        base = Math.toRadians(a);
    }

    double evaluate() {
        return Math.cos(base);
    }

    public String toString() {
        return "cos(" + Math.toDegrees(base) + ")";
    }

    @Override
    Node diff(Variable var) {
        return new Sin(base).minus();
    }

    @Override
    boolean isZero() {
        return Math.abs(base) < 1e-9;
    }
}