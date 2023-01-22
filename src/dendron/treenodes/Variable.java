package dendron.treenodes;

import dendron.Errors;

import java.io.PrintWriter;
import java.util.Map;

public class Variable implements ExpressionNode{

    private String value;

    // Variable constructor
    public Variable(String value) {
        this.value = value;
    }

    @Override
    public void infixDisplay() {
        System.out.print(this.value + " ");
    }

    @Override
    public void compile(PrintWriter out) {
        System.out.println("LOAD " + this.value);
    }

    /**
     * Get's the value of the variable in question by looking at the symTab.
     *
     * @param symTab symbol table, if needed, to fetch variable values
     * @return
     */
    @Override
    public int evaluate(Map<String, Integer> symTab) {
        if (symTab.get(this.value) == null) {
            dendron.Errors.report(Errors.Type.UNINITIALIZED, this.value);
        }
        return symTab.put(this.value, symTab.get(this.value));
    }
}
