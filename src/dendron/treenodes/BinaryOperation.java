package dendron.treenodes;

import java.io.PrintWriter;
import java.sql.SQLOutput;
import java.util.Map;

import static dendron.Errors.Type.DIVIDE_BY_ZERO;

public class BinaryOperation implements ExpressionNode{

    private String operator;
    private ExpressionNode leftChild;
    private ExpressionNode rightChild;

    // BinaryOperation constructor
    public BinaryOperation(String operator, ExpressionNode leftChild, ExpressionNode rightChild) {
        this.operator = operator;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    /**
     * Recursive infix display
     */
    @Override
    public void infixDisplay() {
        System.out.print("( ");
        this.leftChild.infixDisplay();
        System.out.print(this.operator + " ");
        this.rightChild.infixDisplay();
        System.out.print(") ");
    }

    @Override
    public void compile(PrintWriter out) {
        this.leftChild.compile(out);
        this.rightChild.compile(out);
        switch (this.operator) {
            case "+": System.out.println("ADD"); break;
            case "-": System.out.println("SUB"); break;
            case "*": System.out.println("MUL"); break;
            case "/": System.out.println("DIV"); break;
            default: System.out.println("UNKNOWN BINARY OPERATOR"); break;
        }
    }

    /**
     * Depending on what operator is in action it will either +, -, *, or / the children nodes.
     * It will recursively call the children's evaluate.
     *
     * @param symTab symbol table, if needed, to fetch variable values
     * @return
     */
    @Override
    public int evaluate(Map<String, Integer> symTab) {

        switch (this.operator) {
            case "+": return this.leftChild.evaluate(symTab) + this.rightChild.evaluate(symTab);
            case "-": return this.leftChild.evaluate(symTab) - this.rightChild.evaluate(symTab);
            case "*": return this.leftChild.evaluate(symTab) * this.rightChild.evaluate(symTab);
            case "/":
                if (this.rightChild.evaluate(symTab) == 0) {
                    dendron.Errors.report(DIVIDE_BY_ZERO, this.rightChild.evaluate(symTab));
                } else {
                    return this.leftChild.evaluate(symTab) / this.rightChild.evaluate(symTab);
                }
            default: return 0;
        }
    }
}
