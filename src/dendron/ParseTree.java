package dendron;

import dendron.treenodes.*;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dendron.Errors.Type.*;

/**
 * Operations that are done on a Dendron code parse tree.
 *
 * @author YOUR NAME HERE
 */
public class ParseTree {

    private Program program;

    private Map<String, Integer> symTab;

    private Map<String, Integer> printTab;

    /**
     * Parse the entire list of program tokens. The program is a
     * sequence of actions (statements), each of which modifies something
     * in the program's set of variables. The resulting parse tree is
     * stored internally.
     * @param tokens the token list (Strings). This list may be destroyed
     *                by this constructor.
     */
    public ParseTree( List< String > tokens ) {
        // TODO

        this.program = new Program();
        this.symTab = new HashMap<>();
        this.printTab = new HashMap<>();

        //System.out.println("tokens: " + tokens);

        while (tokens.size() > 0) {
            String action = tokens.remove(0);

            if (action.equals("#")) {
                // Create a print node

                try {
                    Print print = new Print(Parse(tokens));
                    this.program.addAction(print);

                } catch (IndexOutOfBoundsException e) {
                    dendron.Errors.report(PREMATURE_END, this.program);
                }


            } else if (action.equals(":=")) {
                // Create an assignment node

                try {
                    Assignment assignment = new Assignment(tokens.remove(0), Parse(tokens));
                    this.program.addAction(assignment);

                } catch (IndexOutOfBoundsException e) {
                    dendron.Errors.report(PREMATURE_END, this.program);
                }


            } else {
                // This is an error
                dendron.Errors.report(ILLEGAL_VALUE, this.program);
            }
        }
    }

    private ExpressionNode Parse(List<String> tokens) {

        String token = tokens.remove(0);

        if (token.matches("^[a-zA-Z].*")) {
            // Found a variable
            return new Variable(token);

        } else if (token.matches("-?\\d+")) {
            // Found a constant
            return new Constant(Integer.parseInt(token));

        } else if (token.equals("_") || token.equals("%")) {
            // Found a unary operation
            return new UnaryOperation(token, Parse(tokens));

        } else if (token.equals("+") || token.equals("-") || token.equals("*")  || token.equals("/")) {
            // Found a unary operation
            return new BinaryOperation(token, Parse(tokens), Parse(tokens));

        } else {
            // This is an error
            dendron.Errors.report(ILLEGAL_VALUE, token);
            return null;
        }
    }

    /**
     * Print the program the tree represents in a more typical
     * infix style, and with one statement per line.
     * @see ActionNode#infixDisplay()
     */
    public void displayProgram() {
        // TODO

        System.out.println("Displaying program with expressions in infix notation:\n");
        this.program.infixDisplay();
    }

    /**
     * Run the program represented by the tree directly
     * @see ActionNode#execute(Map)
     */
    public void interpret() {
        // TODO

        System.out.println("\nInterpreting the parse tree...");
        this.program.execute(this.symTab);
    }

    /**
     * Build the list of machine instructions for
     * the program represented by the tree.
     *
     * @param out where to print the Soros instruction list
     */
    public void compileTo( PrintWriter out ) {
        // TODO

        //System.out.println("\nCompiling Program...");
        this.program.compile(out);
    }
}
