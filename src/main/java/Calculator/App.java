// TODO: Implement Evaluator.java with better error handling.

package Calculator;
import javax.swing.*;

public class App {

    public static void main(String[] args) {
		JFrame frame = new JFrame("Calc");
		frame.setBounds(0, 0, 480, 480);
		new CalcUi(frame).create();
	}

}
