package cms350p1v3;
import java.util.Stack;
import java.util.EmptyStackException;
import java.util.StringTokenizer;

public class EvaltheInput {
    private Stack<Integer> stkoperands = new Stack<>();
    private Stack<String> stkoperators = new Stack<>();

    public int evaluate(String str) throws DivideByZero {

        StringTokenizer tokens = new StringTokenizer(str, "[+\\-*/() ]", true);

        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken();
            System.out.println(token + " ");


            if (token.matches("[\\d]+")) { // tech. the same "[0-9]+"
                // There may be more than one digits in number
                stkoperands.push(Integer.parseInt(token));
            }

            else if (token.equals("("))
                stkoperators.push(token);

            // Closing brace encountered, have to solve up to the (
            else if(token.equals(")")) {
                while (!stkoperators.peek().equals("(")) {
                    System.out.println(stkoperands.push(calcOps(stkoperators.pop(), stkoperands.pop(), stkoperands.pop())));
                }
                stkoperators.pop();
            }

                        //if it is an operator

            else if (token.matches("[+\\-*/]")) {
                while (!stkoperators.empty() && isPrecedent(token, stkoperators.peek()))
                    System.out.println("from Stack: "+stkoperands.push(calcOps(stkoperators.pop(), stkoperands.pop(),stkoperands.pop())));
                // Push current token to 'ops'.
                stkoperators.push(token);

            }
        }
        // evaluate to remaining values
        while (!stkoperators.empty()){
            stkoperands.push(calcOps( stkoperators.pop(), stkoperands.pop(), stkoperands.pop()));
        }



        // Top of 'values' contains result, return it

        System.out.println("Result: "+stkoperands.peek());
        return stkoperands.pop();
    }

    public int calcOps(String op, int b, int a) throws DivideByZero {

        switch (op) {

            case "+":
                return a + b;

            case "-":
                return a - b;

            case "*":
                return a * b;

            default:
                if (b == 0) {
                    throw new DivideByZero("Cannot divide by zero");
                }
                return a / b;
        }

    }

    public static boolean isPrecedent(String op1, String op2)

    {

        if (op2.matches("[()]"))
            return false;
        else
            return (!(op1.matches("[*/]") && (op2.matches("[+\\-]"))));
    }


    public static void main(String[] args) throws DivideByZero {
        EvaltheInput ev = new EvaltheInput();
        //calculate and precedence
        System.out.println(ev.evaluate("5+2*6"));
        System.out.println("all operators at once expected 7");
        System.out.println(ev.evaluate("(4+3*(3-1)/2)"));
        System.out.println("all operators at once with space issues expected 7");
        System.out.println(ev.evaluate("( 4 +3*( 3- 1)/ 2)"));
        System.out.println("difficult precedence case expected 4");
        System.out.println(ev.evaluate("((3+4)*5/5-3)"));
        System.out.println("division by 0 expect pop up saying Divide by 0");
		System.out.println(ev.evaluate("(3/0"));
		System.out.println("Unevaluatable expression expect error");
		System.out.println(ev.evaluate("(1+2(3+"));
		
        
    }
}
