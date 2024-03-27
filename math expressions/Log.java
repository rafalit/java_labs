public class Log extends Node
{
    private Node base;
    private Node arg;

    public Log(Node arg)
    {
        this.arg = arg;
        this.base = new Constant(Math.E);
    }
    public Log(Node base, Node arg)
    {
        this.base=base;
        this.arg=arg;
    }

    @Override
    double evaluate()
    {
        double pods = base.evaluate();
        double wyk = arg.evaluate();
        return Math.log(wyk)/Math.log(pods);
    }

    @Override
    public String toString()
    {
        StringBuilder b = new StringBuilder();
        b.append("Log_");
        b.append(base.toString()).append("(").append(arg.toString()).append(")");
        return b.toString();
    }

    @Override
    public Node diff(Variable var) {
        return new Prod(new Constant(1.0), new Prod(new Power(arg, -1), new Log(base)));
    }

    @Override
    boolean isZero() {
        return false;
    }



}
