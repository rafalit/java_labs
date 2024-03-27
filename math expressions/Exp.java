public class Exp extends Node
{
    Node pow;

    public Exp(Node pow)
    {
        this.pow = pow;
    }

    @Override
    double evaluate()
    {
        return Math.exp(pow.evaluate());
    }

    public String toString()
    {
        StringBuilder b = new StringBuilder();
        b.append("Exp(");
        b.append(pow.toString()).append(")");
        return b.toString();
    }

    @Override
    public Node diff(Variable var) {
        return new Exp(pow);
    }

    @Override
    boolean isZero() {
        return false;
    }




}
