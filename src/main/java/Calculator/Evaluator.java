package Calculator;

import java.util.LinkedList;
import java.util.Stack;

public class Evaluator {
    private static Evaluator INSTANCE = null;
    private Evaluator(){

    }
    private LinkedList<String> infixToPostFix(String expression){
        char[] tokens = expression.toCharArray();
        LinkedList<String> exp = new LinkedList<>();
        Stack<String> ops = new Stack<>();
        for(int i = 0; i < tokens.length; i++){
            if(tokens[i] == ' '){
                continue;
            }
            if(tokens[i] == ',') continue;

            if(isDigit(tokens[i])){
                String number = String.valueOf(tokens[i++]);

                while(i < tokens.length && isDigit(tokens[i])) number = number.concat(String.valueOf(tokens[i++]));
                exp.addLast(number);
                i--;
            }else if(isLetter(tokens[i])){
                String fun = String.valueOf(tokens[i++]);
                while(i < tokens.length && isLetter(tokens[i])) fun = fun.concat(String.valueOf(tokens[i++]));
                ops.push(fun);
                i--;
            }
            else if(tokens[i] == '('){
                ops.push(String.valueOf(tokens[i]));
            }else if(tokens[i] == ')'){
                while(!ops.peek().equals("(")) {
                    exp.addLast(ops.pop());
                }
                ops.pop();
            }else{
                String token = String.valueOf(tokens[i]);
                while(!ops.isEmpty() && !hasPrecedence(ops.peek(), token)){

                    exp.addLast(ops.pop());
                }
                ops.push(token);
            }
        }
        while(!ops.isEmpty()) exp.addLast(ops.pop());
        return exp;
    }

    /**
     *
     * @param op1 - operator peek out of stack.
     * @param op2 - operator will be pushed to the stack.
     * @return {boolean} if higher precedence than true else false;
     */
    private boolean hasPrecedence(String op1, String op2){
        if(op1.equals("(") || op2.equals(")")) {
            return true;
        }else if(op1.equals("^") || op2.equals("^")){
            return true;
        }
        else return op1.equals("+") || op1.equals("-") && op2.equals("*") || op2.equals("/");
    }

    /**
     *
     * @param digit - char ex:- '1', '2' etc.
     * @return - true if it is digit or false if it is not.
     */
    private boolean isDigit(char digit){
        return (digit >= '0' && digit <= '9') || digit == '.';
    }

    /**
     *
     * @param l - char ex:- 'l', 'i' etc.
     * @return - true if it is letter or false if it is not.
     */
    private boolean isLetter(char l){
        return l >= 'a' && l <= 'z';
    }

    private boolean isNumber(String num){
        return isDigit(num.charAt(0));
    }
    private boolean isFun(String op){
        return isLetter(op.charAt(0));
    }
    private double postFixEvaluator(LinkedList<String> exp){
        for(int i = 0; i < exp.size(); i++){
            String token = exp.get(i);
            if(!isNumber(token)){
                if(isFun(token)){
                   double result = doFunOp(Double.parseDouble(exp.remove(--i)), token);
                   exp.remove(i);
                   exp.add(i, String.valueOf(result));
                }else{
                    double result = doOp(Double.parseDouble(exp.remove(--i)), Double.parseDouble(exp.remove(--i)),
                            token);
                    exp.remove(i);
                    exp.add(i, String.valueOf(result));
                }
            }
        }
        return Double.parseDouble(exp.getFirst());
    }

    public double evaluate(String exp){

        return postFixEvaluator(infixToPostFix(exp));
    }

    private double doOp(double val1, double val2, String op){
        switch (op){
            case "+":
                return val2 + val1;
            case "-":
                return val2 - val1;
            case "*":
                return val2 * val1;
            case "/":
                return val2/val1;
            case "^":
                return Math.pow(val2, val1);

        }

        return -1;
    }

    private double doFunOp(double val, String fun){
        switch (fun){
            case "sin":
                return Math.sin(val);
            case "cos":
                return Math.cos(val);
            case "tan":
                return Math.tan(val);
            case "log":
                return Math.log10(val);
            case "sqr":
                return Math.sqrt(val);
            case "fact":
                return factorial(val);
            case "sum":
                return val*(val+1)/2;

        }

        return -1;
    }

    private double factorial(double val){
        double result = 1;
        for(int i = 2; i <= val; i++){
            result *= i;
        }

        return result;
    }

    public static Evaluator getInstance(){
        return INSTANCE == null ? INSTANCE = new Evaluator() : INSTANCE;
    }

}


