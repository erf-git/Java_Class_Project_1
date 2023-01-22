package dendron.treenodes;

import java.io.PrintWriter;
import java.util.Map;

public class Print implements ActionNode{

    // Takes an expression node
    private ExpressionNode printee;

    // Print constructor
    public Print(ExpressionNode printee) {
        this.printee = printee;
    }

    /**
     * Will print out the variable in question's value when print is called
     * @param symTab the table where variable values are stored
     */
    @Override
    public void execute(Map<String, Integer> symTab) {
        System.out.println("=== " + this.printee.evaluate(symTab));
    }

    /**
     * Calls the infixDisplay of the printee
     */
    @Override
    public void infixDisplay() {
        System.out.print("PRINT: ");
        this.printee.infixDisplay();
    }

    @Override
    public void compile(PrintWriter out) {
        this.printee.compile(out);
        System.out.println("PRINT");
    }
}
