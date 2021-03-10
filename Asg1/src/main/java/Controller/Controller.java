package Controller;

import View.*;
import Model.*;

import java.awt.event.*;
import java.util.regex.*;

public class Controller {

	private View view;
	
	public Controller(View view) {
		this.view = view;
        view.setVisible(true);
		
		view.addAddListener(new AddListener());
		view.addSubtractListener(new SubtractListener());
		view.addMultiplyListener(new MultiplyListener());
		view.addDivideListener(new DivideListener());
		view.addDifferentiateListener(new DifferentiateListener());
		view.addIntegrateListener(new IntegrateListener());
		view.addClearListener(new ClearListener());
		view.addResetListener(new ResetListener());
	}
	
	private boolean isValid(String s) {
		Pattern p = Pattern.compile("(\\A[+-]?\\d*[xX]\\^\\d+)|(\\A[+-]?\\d*[xX])|(\\A[+-]?\\d+[xX]?)|([+-]\\d*[xX]\\^\\d+)|([+-]\\d+[xX]?)|([+-]\\d*[xX])");
		Matcher m = p.matcher(s);
		
		s.replaceAll(" ", "");
		String aux = new String();

		while (m.find()) {
			aux = aux.concat(s.substring(m.start(), m.end()));
		}
		return s.equals(aux);
	}
	
	public Polynomial createPolynomial(String s) {
		Polynomial polynomial = new Polynomial();
		Pattern p1 = Pattern.compile("(\\A[+-]?\\d*[xX]\\^\\d+)|(\\A[+-]?\\d*[xX]?)|([+-]\\d*[xX]\\^\\d+)|([+-]\\d+[xX]?)|([+-]\\d*[xX])");
		Pattern p2 = Pattern.compile("([+-]?\\d*)([xX](\\^(\\d+))?)?");
	
		Matcher m1 = p1.matcher(s);
		
		while (m1.find()) {
			String aux = new String();
			aux = s.substring(m1.start(), m1.end());
			
			Matcher m2 = p2.matcher(aux);
			Fraction coefficient;
			int degree;
			
			m2.find();
			if (m2.group(1).equals("+") || m2.group(1).equals("")) {
				coefficient = new Fraction(1, 1);
			}
			else if (m2.group(1).contentEquals("-")) { 
				coefficient = new Fraction(-1, 1);
			}
			else {
				coefficient = new Fraction(Integer.parseInt(m2.group(1)), 1);
			}
			
			if (m2.group(4) == null && m2.group(2) != null) { 
				degree = 1;
			}
			else if (m2.group(4) == null && m2.group(2) == null) {
				degree = 0;
			}
			else {
				degree = Integer.parseInt(m2.group(4));
			}
			polynomial.addMonomial(new Monomial(degree, coefficient));;
		}
		return polynomial.simplifyPolynomial();
	}
	
	class AddListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			view.resetResult();
	
			try {
				String polynomial1 = view.getInputPolynomial1();
				String polynomial2 = view.getInputPolynomial2();
				
				if (isValid(polynomial1) && isValid(polynomial2)) {
					Polynomial poly1 = createPolynomial(polynomial1);
					Polynomial poly2 = createPolynomial(polynomial2);
					
					view.setPolynomial1(poly1.toString());
					view.setPolynomial2(poly2.toString());
					Polynomial sum = poly1.addPolynomials(poly2);
					view.setResult(sum.toString());
				}
				else if (!isValid(polynomial1) && isValid(polynomial2)) {
					view.showError("First polynomial is invalid!");
				}
				else if (isValid(polynomial1) && !isValid(polynomial2)) {
					view.showError("Second polynomial is invalid!");
				}
				else {
					view.showError("The entered polynomials are invalid!");
				}
			}
			catch (Exception exception) {	
			}
		}
	}
	
	class SubtractListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			view.resetResult();
	
			try {
				String polynomial1 = view.getInputPolynomial1();
				String polynomial2 = view.getInputPolynomial2();
				
				if (isValid(polynomial1) && isValid(polynomial2)) {
					Polynomial poly1 = createPolynomial(polynomial1);
					Polynomial poly2 = createPolynomial(polynomial2);
					
					view.setPolynomial1(poly1.toString());
					view.setPolynomial2(poly2.toString());
					Polynomial difference = poly1.subtractPolynomials(poly2);
					view.setResult(difference.toString());
				}
				else if (!isValid(polynomial1) && isValid(polynomial2)) {
					view.showError("First polynomial is invalid!");
				}
				else if (isValid(polynomial1) && !isValid(polynomial2)) {
					view.showError("Second polynomial is invalid!");
				}
				else {
					view.showError("The entered polynomials are invalid!");
				}
			}
			catch (Exception exception) {	
			}
		}
	}
	
	class MultiplyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			view.resetResult();
	
			try {
				String polynomial1 = view.getInputPolynomial1();
				String polynomial2 = view.getInputPolynomial2();
				
				if (isValid(polynomial1) && isValid(polynomial2)) {
					Polynomial poly1 = createPolynomial(polynomial1);
					Polynomial poly2 = createPolynomial(polynomial2);
					
					view.setPolynomial1(poly1.toString());
					view.setPolynomial2(poly2.toString());
					Polynomial result = poly1.multiplyPolynomials(poly2);
					view.setResult(result.toString());
				}
				else if (!isValid(polynomial1) && isValid(polynomial2)) {
					view.showError("First polynomial is invalid!");
				}
				else if (isValid(polynomial1) && !isValid(polynomial2)) {
					view.showError("Second polynomial is invalid!");
				}
				else {
					view.showError("The entered polynomials are invalid!");
				}
			}
			catch (Exception exception) {	
			}
		}
	}
	
	class DivideListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			view.resetResult();
	
			try {
				String polynomial1 = view.getInputPolynomial1();
				String polynomial2 = view.getInputPolynomial2();
				
				if (isValid(polynomial1) && isValid(polynomial2)) {
					Polynomial poly1 = createPolynomial(polynomial1);
					Polynomial poly2 = createPolynomial(polynomial2);
					
					view.setPolynomial1(poly1.toString());
					view.setPolynomial2(poly2.toString());
					if (view.getInputPolynomial2().equals("0")) {
						view.showError("Division by 0(zero) cannot be performed!");	
					}
					else {
						Polynomial[] result = poly1.dividePolynomials(poly2);
						view.setResult(result[0].toString());
						view.setRemainder(result[1].toString());
					}
				}
				else if (!isValid(polynomial1) && isValid(polynomial2)) {
					view.showError("First polynomial is invalid!");
				}
				else if (isValid(polynomial1) && !isValid(polynomial2)) {
					view.showError("Second polynomial is invalid!");
				}
				else {
					view.showError("The entered polynomials are invalid!");
				}
			}
			catch (Exception exception) {	
			}
		}
	}
	
	class DifferentiateListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			view.resetResult();
			view.setPolynomial2("");
	
			try {
				String polynomial1 = view.getInputPolynomial1();
				
				if (isValid(polynomial1)) {
					Polynomial poly1 = createPolynomial(polynomial1);
					
					view.setPolynomial1(poly1.toString());
					Polynomial result = poly1.differentiatePolynomial();
					view.setResult(result.toString());
				}
				else {
					view.showError("Polynomial is invalid!");
				}
			}
			catch (Exception exception) {	
			}
		}
	}
	
	class IntegrateListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			view.resetResult();
			view.setPolynomial2("");
	
			try {
				String polynomial1 = view.getInputPolynomial1();
				
				if (isValid(polynomial1)) {
					Polynomial poly1 = createPolynomial(polynomial1);
					
					view.setPolynomial1(poly1.toString());
					Polynomial result = poly1.integratePolynomial();
					view.setResult(result.toString());
				}
				else {
					view.showError("Polynomial is invalid!");
				}
			}
			catch (Exception exception) {	
			}
		}
	}
	
	class ClearListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			view.clear();
		}
	}
	
	class ResetListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			view.reset();
		}
	}
}
