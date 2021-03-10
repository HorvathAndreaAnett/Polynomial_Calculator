package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class View extends JFrame{

	public static final String INITIAL_POLYNOMIAL_1 = "x^2+3x^3+x^5";
	public static final String INITIAL_POLYNOMIAL_2 = "1+2x+4x^2";
	
	private JTextField inputPolynomial1Tf = new JTextField(50);
	private JTextField inputPolynomial2Tf = new JTextField(50);
	private JTextField resultTf = new JTextField(50);
	private JTextField remainderTf = new JTextField(50);
	private JButton addBtn = new JButton("Add");
	private JButton subtractBtn = new JButton("Subtract");
	private JButton multiplyBtn = new JButton("Multiply");
	private JButton divideBtn = new JButton("Divide");
	private JButton integrateBtn = new JButton("Integrate");
	private JButton differentiateBtn = new JButton("Differentiate");
	private JButton clearBtn = new JButton("Clear");
	private JButton resetBtn = new JButton("Reset");
	
	public View() {
		
		inputPolynomial1Tf.setText(INITIAL_POLYNOMIAL_1);
		inputPolynomial2Tf.setText(INITIAL_POLYNOMIAL_2);
		
		resultTf.setEditable(false);
		remainderTf.setEditable(false);
		
		this.setSize(600, 300);
		
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		JPanel panel6 = new JPanel();
		
		panel1.setLayout(new FlowLayout());
		panel1.add(new JLabel("First polynomial: "));
		panel1.add(inputPolynomial1Tf);
		
		panel2.setLayout(new FlowLayout());
		panel2.add(new JLabel("Second polynomial: "));
		panel2.add(inputPolynomial2Tf);
		
		panel3.setLayout(new FlowLayout());
		panel3.add(addBtn);
		panel3.add(subtractBtn);
		panel3.add(multiplyBtn);
		panel3.add(divideBtn);
		panel3.add(differentiateBtn);
		panel3.add(integrateBtn);
		
		panel4.setLayout(new FlowLayout());
		panel4.add(clearBtn);
		panel4.add(resetBtn);
		
		panel5.setLayout(new FlowLayout());
		panel5.add(new JLabel("Result: "));
		panel5.add(resultTf);
		
		panel6.setLayout(new FlowLayout());
		panel6.add(new JLabel("Remainder: "));
		panel6.add(remainderTf);
		
		JPanel p = new JPanel();
   		p.add(panel1);
   		p.add(panel2);
   		p.add(panel3);
   		p.add(panel4);
   		p.add(panel5);
   		p.add(panel6);
   		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        
   		this.setContentPane(p);
		this.pack();
		
		this.setTitle("Polynomial Calculator");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void reset() {
		resultTf.setText(null);
		remainderTf.setText(null);
		inputPolynomial1Tf.setText(INITIAL_POLYNOMIAL_1);
		inputPolynomial2Tf.setText(INITIAL_POLYNOMIAL_2);
	}
	
	public void resetResult() {
		resultTf.setText(null);
		remainderTf.setText(null);
	}
	
	public void clear() {
		resultTf.setText(null);
		remainderTf.setText(null);
		inputPolynomial1Tf.setText(null);
		inputPolynomial2Tf.setText(null);
	}
	
	public String getInputPolynomial1() {
		return inputPolynomial1Tf.getText();
	}
	
	public String getInputPolynomial2() {
		return inputPolynomial2Tf.getText();
	}
	
	public void setPolynomial1(String s) {
		inputPolynomial1Tf.setText(s);
	}
	
	public void setPolynomial2(String s) {
		inputPolynomial2Tf.setText(s);
	}
	
	public void setResult(String s) {
		resultTf.setText(s);
	}
	
	public void setRemainder(String s) {
		remainderTf.setText(s);
	}
	
	public void showError(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage);
	}
	
	public void addAddListener(ActionListener addAl) {
		addBtn.addActionListener(addAl);
	}
	
	public void addSubtractListener(ActionListener subAl) {
		subtractBtn.addActionListener(subAl);
	}
	
	public void addMultiplyListener(ActionListener mulAl) {
		multiplyBtn.addActionListener(mulAl);
	}
	
	public void addDivideListener(ActionListener divAl) {
		divideBtn.addActionListener(divAl);
	}
	
	public void addDifferentiateListener(ActionListener diffAl) {
		differentiateBtn.addActionListener(diffAl);
	}
	
	public void addIntegrateListener(ActionListener intAl) {
		integrateBtn.addActionListener(intAl);
	}
	
	public void addClearListener(ActionListener clrAl) {
		clearBtn.addActionListener(clrAl);
	}
	
	public void addResetListener(ActionListener rstAl) {
		resetBtn.addActionListener(rstAl);
	}
	
}
