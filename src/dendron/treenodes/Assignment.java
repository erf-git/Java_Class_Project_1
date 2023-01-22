package dendron.treenodes;

import java.io.PrintWriter;
import java.util.Map;

public class Assignment implements ActionNode{

    private String identity;
    private ExpressionNode assignmentee;

    // Assignment constructor
    public Assignment(String identity, ExpressionNode assignmentee) {
        this.identity = identity;
        this.assignmentee = assignmentee;
    }

    @Override
    public void execute(Map<String, Integer> symTab) {
        symTab.put(this.identity, this.assignmentee.evaluate(symTab));
    }

    @Override
    public void infixDisplay() {
        System.out.print(this.identity + " := ");
        this.assignmentee.infixDisplay();
    }

    @Override
    public void compile(PrintWriter out) {
        this.assignmentee.compile(out);
        System.out.println("STORE " + this.identity);
    }
}
