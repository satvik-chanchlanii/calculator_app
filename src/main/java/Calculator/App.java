/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package Calculator;
import javax.swing.*;

public class App {

    public static void main(String[] args) {
	    double result = Evaluator.getInstance().evaluate("2+(3/(4-2)) - log(10)");

	    System.out.println(result);
	    
	}

}
