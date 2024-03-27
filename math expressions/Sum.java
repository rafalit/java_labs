import java.util.ArrayList;
import java.util.List;

public class Sum extends Node {

    List<Node> args = new ArrayList<>();

    Sum(){}

    Sum(Node n1, Node n2) {
        args.add(!n1.isZero() ? n1 : null);
        args.add(!n2.isZero() ? n2 : null);
    }

    Sum add(Node n){
        if(n.isZero()){
            return this;
        }
        args.add(n);
        return this;
    }

    Sum add(double c){
        if(c != 0.0){
            args.add(new Constant(c));
        }

        return this;
    }

    Sum add(double c, Node n) {
        if(c != 0.0){
            Node mul = new Prod(c,n);
            args.add(mul);
        }

        return this;
    }

    @Override
    double evaluate() {
        double result =0;
        for(Node node : args){
            result += node.evaluate();
        }
        return sign*result;
    }

    @Override
    int getSign() {
        return super.getSign();
    }

    @Override
    int getArgumentsCount(){return args.size();}
    @Override
    public String toString(){
        StringBuilder b = new StringBuilder();
        if (sign < 0) {
            b.append("-");
        }
        for (int i = 0; i < args.size(); i++) {

            if (i != 0) {
                b.append(" + ");
            }
            b.append(args.get(i).toString());
        }
        return b.toString();
    }

    @Override
    public Node diff(Variable var) {
        Sum r = new Sum();
        for (Node n : args) {
            r.add(n.diff(var).simplify());
        }
        return r;
    }

    @Override
    boolean isZero() {
        return args.isEmpty();
    }

    @Override
    public Node simplify() {
        Sum simplifiedSum = new Sum();

        for (Node arg : this.args) {
            Node simplified = arg.simplify();
            if(!simplified.isZero()){
                simplifiedSum.add(simplified);
            }
        }

        if (simplifiedSum.args.size() > 1) {
            return simplifiedSum;
        } else if (simplifiedSum.args.isEmpty()) {
            return new Constant(0);
        } else {
            return simplifiedSum.args.get(0);
        }
    }

}
