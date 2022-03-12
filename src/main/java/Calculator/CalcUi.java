package Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalcUi implements ActionListener {
    private final JFrame mFrame;
    private final JTextPane textPane = new JTextPane();
    private final Evaluator evaluator = Evaluator.getInstance();

    public CalcUi(JFrame frame) {
        mFrame = frame;
        mFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mFrame.setLayout(new BorderLayout());
    }

    public void create() {
        String[] buttonTexts = {"C", "+/-", "%", "DEL", "7", "8", "9", "\u00F7",
        "4", "5", "6", "x", "1", "2", "3", "-", "0", ".", "=", "+"};
        JPanel buttonsPanel = new JPanel(new GridLayout(5, 4, 10, 10));

        JButton button;

        Font buttonFont = new Font("SansSerif", Font.BOLD, 20);

        for (String buttonText : buttonTexts) {
            button = new JButton(buttonText);
            button.setFont(buttonFont);
            button.addActionListener(this);
            buttonsPanel.add(button);
        }

        textPane.setFont(new Font("SansSerif", Font.PLAIN, 20));
        textPane.setEditable(true);
        mFrame.add(textPane, BorderLayout.PAGE_START);
        mFrame.add(buttonsPanel, BorderLayout.CENTER);
        mFrame.setVisible(true);
    }

    private void updateText(String text) {
        String result = textPane.getText();
        result += text;
        textPane.setText(result);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String temp = ((JButton) e.getSource()).getText();
        if (Character.isDigit(temp.charAt(0))) {
            updateText(temp);
            return;
        }

        switch (temp) {
            case "C":
                textPane.setText("");
                break;

            case "DEL":
                // TODO: Implement it

            case "+/-":
                // TODO: Implement it
                break;

            case "=":
                textPane.setText(Double.toString(evaluator.evaluate(textPane.getText().replaceAll("\u00F7", "/")
                        .replaceAll("x", "*"))));
                break;
            default:
                updateText(temp);
        }
    }
}
