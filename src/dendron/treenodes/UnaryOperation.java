package dendron.treenodes;

import java.io.PrintWriter;
import java.util.Map;

public class UnaryOperation implements ExpressionNode {

    private String operator;
    private ExpressionNode expr;

    // UnaryOperation constructor
    public UnaryOperation(String operator, ExpressionNode expr) {
        this.operator = operator;
        this.expr = expr;
    }

    @Override
    public void infixDisplay() {
        System.out.print(this.operator + " ");
        this.expr.infixDisplay();
    }

    @Override
    public void compile(PrintWriter out) {
        this.expr.compile(out);

        switch (this.operator) {
            case "_": System.out.println("NEG"); break;
            case "%": System.out.println("SQRT"); break;
            default: System.out.println("UNKNOWN UNARY OPERATOR"); break;
        }
    }

    /**
     * Depending on what operator is in action it will either negative or square-root the children nodes.
     * It will recursively call the children's evaluate.
     *
     * @param symTab symbol table, if needed, to fetch variable values
     * @return
     */
    @Override
    public int evaluate(Map<String, Integer> symTab) {

        switch (this.operator) {
            case "_": return Math.negateExact(this.expr.evaluate(symTab));
            case "%": return (int) Math.sqrt(this.expr.evaluate(symTab));
            default: return 0;
        }
    }
}
