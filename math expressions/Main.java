import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        buildAndPrint();
        buildAndEvaluate();
        defineCircle();
        diffPoly();
        diffCircle();
        logCalculator();
        expCalculator();
        cosCalculator();
        sinCalculator();
    }

    static void buildAndPrint() {
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(2.1, new Power(x, 3))
                .add(new Power(x, 2))
                .add(-2, x)
                .add(7);
        System.out.println(exp.toString());
    }

    static void buildAndEvaluate() {
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(new Power(x, 3))
                .add(-2, new Power(x, 2))
                .add(-1, x)
                .add(2);
        for (double v = -5; v < 5; v += 0.1) {
            x.setValue(v);
            Locale usLocale = Locale.US;
            System.out.printf(Locale.US, "f(%f)=%f\n", v, exp.evaluate());
        }
    }

    static void defineCircle() {
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Node circle = new Sum()
                .add(new Power(x, 2))
                .add(new Power(y, 2))
                .add(8, x)
                .add(4, y)
                .add(16);
        System.out.println(circle.toString());

        double xv = 100 * (Math.random() - .5);
        double yv = 100 * (Math.random() - .5);
        x.setValue(xv);
        y.setValue(yv);
        double fv = circle.evaluate();
        System.out.print(String.format("Punkt (%f,%f) leży %s koła %s", xv, yv, (fv < 0 ? "wewnątrz" : "na zewnątrz"), circle.toString()));
    }

    static void diffPoly() {
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(2, new Power(x, 3))
                .add(new Power(x, 2))
                .add(-2, x)
                .add(7);
        System.out.print("exp=");
        System.out.println(exp.toString());

        Node d = exp.diff(x);
        System.out.print("d(exp)/dx=");
        System.out.println(d.toString());
    }

    static void diffCircle() {
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Node circle = new Sum()
                .add(new Power(x, 2))
                .add(new Power(y, 2))
                .add(8, x)
                .add(4, y)
                .add(16);
        System.out.print("f(x,y)=");
        System.out.println(circle.toString());

        Node dx = circle.diff(x);
        System.out.print("d f(x,y)/dx=");
        System.out.println(dx.toString());
        System.out.print("d f(x,y)/dy=");
        Node dy = circle.diff(y);
        System.out.println(dy.toString());
    }

    static void logCalculator() {
        Node base = new Constant(4);
        Node arg = new Constant(16);

        Log log = new Log(base, arg);

        double result = log.evaluate();

        System.out.println("Log Expression: " + log);
        System.out.println("Result: " + result);

    }

    static void expCalculator() {
        Node arg = new Constant(5);
        Exp exp = new Exp(arg);
        System.out.println("Exp Expression: " + exp);
        System.out.println("Exp: " + exp.evaluate());
    }

    static void cosCalculator()
    {
        Cos cos1 = new Cos(45);

        System.out.println("Cos Expression: " + "cos = " + cos1.evaluate());

        System.out.println(cos1);
    }

    static void sinCalculator()
    {
        Sin sin1 = new Sin(90);

        System.out.println("Sin Expression: " + "sin = " + sin1.evaluate());

        System.out.println(sin1);
    }
}



