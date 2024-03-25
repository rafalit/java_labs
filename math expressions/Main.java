import java.util.Locale;

public class Main {
    public static void main(String[] args)
    {
            Variable x = new Variable("x");
            Node exp = new Sum()
                .add(2.1,new Power(x,3))
                .add(new Power(x,2))
                .add(-2,x)
                .add(7);
            System.out.println(exp.toString());



            Variable x_ = new Variable("x");
            Node exp_ = new Sum()
                .add(new Power(x_,3))
                .add(-2,new Power(x_,2))
                .add(-1,x_)
                .add(2);
            System.out.println(exp_.toString());
            for(double v=-5;v<5;v+=0.1){
                x_.setValue(v);
                System.out.printf(Locale.US,"f(%f)=%f\n",v,exp_.evaluate());
            }

        Variable xx = new Variable("x");
        Variable yy = new Variable("y");
        Node circle = new Sum()
                .add(new Power(xx,2))
                .add(new Power(yy,2))
                .add(8,xx)
                .add(4,yy)
                .add(16);
        System.out.println(circle.toString());

        double xv = 100*(Math.random()-.5);
        double yv = 100*(Math.random()-.5);
        xx.setValue(xv);
        yy.setValue(yv);
        double fv = circle.evaluate();
        System.out.print(String.format("Punkt (%f,%f) leży %s koła %s",xv,yv,(fv<0?"wewnątrz":"na zewnątrz"),circle.toString()));
    }
}
