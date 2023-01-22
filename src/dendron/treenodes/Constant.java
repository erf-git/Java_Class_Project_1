package dendron.treenodes;

import java.io.PrintWriter;
import java.util.Map;

public class Constant implements ExpressionNode{

    private int value;

    // Constant constructor
    public Constant(int value) {
        this.value = value;
    }

    @Override
    public void infixDisplay() {
        System.out.print(this.value + " ");
    }

    @Override
    public void compile(PrintWriter out) {
        System.out.println("PUSH " + this.value);
    }

    /**
     * No need to look anything up, this is just a number
     *
     * @param symTab symbol table, if needed, to fetch variable values
     * @return
     */
    @Override
    public int evaluate(Map<String, Integer> symTab) {
        return this.value;
    }
}
