package Model;

import java.util.ArrayList;
import java.util.Collections;

//This class implements the required operation on polynomials.
//A polynomial is represented as an ArrayList of monomials.

public class Polynomial {
	
	private ArrayList<Monomial> polynomial = new ArrayList<Monomial>();
	
	public Polynomial(ArrayList<Monomial> polynomial) {
		this.polynomial = polynomial;
	}
	
	public Polynomial() {
	}
	
	//This method is used to add monomials to the polynomial, using the add function from java.util.ArrayList.
	public void addMonomial(Monomial monomial) {
		polynomial.add(monomial);
	}

	public ArrayList<Monomial> getPolynomial() {
		return polynomial;
	}

	public void setPolynomial(ArrayList<Monomial> polynomial) {
		this.polynomial = polynomial;
	}
	
	//Returns the rank of the polynomial by finding the monomial in the array list with the maximum degree.
	private int getRank() {
		int max = 0;
		for (Monomial i: polynomial) {
			if (i.getDegree() > max) {
				max = i.getDegree();
			}	
		}
		return max;
	}
	
	//used when in the polynomial terms of certain degrees smaller then the rank are missing
	private void completePolynomial(int rank) {
		boolean found;
		int i = 0;
		
		//searches terms of each degree in range [0, rank]
		while (i <= rank) {
			found = false;
			for (Monomial j: polynomial) {
				if (j.getDegree() == i) {
					found = true;
				}
			}
			//if no such term found, a monomial of that degree with 0 coefficient will be inserted
			if (found == false) {
				addMonomial(new Monomial(i, new Fraction(0, 1)));
			}
			i++;
		}
		Collections.sort(polynomial);
	}
	
	public Polynomial simplifyPolynomial() {
		Polynomial simplifiedPolynomial = new Polynomial();
		simplifiedPolynomial.completePolynomial(this.getRank());
		
		//add the terms with the same degrees of the polynomial in the simplifiedPolynomial 
		for (Monomial i: polynomial) { 
			simplifiedPolynomial.getPolynomial().get(i.getDegree()).setCoefficient(simplifiedPolynomial.getPolynomial().get(i.getDegree()).getCoefficient().addFraction(i.getCoefficient())); 
		}
		
		Polynomial finalPolynomial = new Polynomial();
		
		//eliminate the terms with coefficient = 0
		//by adding the terms with non zero coefficient in the final polynomial
		for (Monomial i: simplifiedPolynomial.getPolynomial()) {
			if (i.getCoefficient().getNumerator() != 0) {
				finalPolynomial.addMonomial(i);
			}		
		}
		return finalPolynomial;
	}
	
	public Polynomial addPolynomials(Polynomial polynomial) {
		int rankSum = Math.max(this.getRank(), polynomial.getRank());
		
		this.completePolynomial(rankSum);
		polynomial.completePolynomial(rankSum);
		
		Polynomial sum = new Polynomial();
		sum.completePolynomial(rankSum);
		
		//being complete, ordered and having no duplicates (terms with the same degree), the polynomials are added termwise
		for (Monomial i: this.getPolynomial()) {
			sum.getPolynomial().get(i.getDegree()).setCoefficient(i.getCoefficient().addFraction(polynomial.getPolynomial().get(i.getDegree()).getCoefficient())); 
		}
		return sum.simplifyPolynomial();
	}
	
	public Polynomial subtractPolynomials(Polynomial polynomial) {
		int rankDiff = Math.max(this.getRank(), polynomial.getRank());
		
		this.completePolynomial(rankDiff);
		polynomial.completePolynomial(rankDiff);
		
		Polynomial diff = new Polynomial();
		diff.completePolynomial(rankDiff);
		
		//being complete, ordered and having no duplicates (terms with the same degree), the polynomials are subtracted termwise
		for (Monomial i: this.getPolynomial()) {
			diff.getPolynomial().get(i.getDegree()).setCoefficient(i.getCoefficient().subtractFraction(polynomial.getPolynomial().get(i.getDegree()).getCoefficient())); 
		}
		return diff.simplifyPolynomial();
	}
	
	//the product of each two monomials from the two polynomials is added in the result in a double foreach loop
	public Polynomial multiplyPolynomials(Polynomial polynomial) {	
		Collections.sort(this.polynomial);
		Collections.sort(polynomial.getPolynomial());
		
		Polynomial product = new Polynomial();
		
		for (Monomial i: this.getPolynomial()) {
			for (Monomial j: polynomial.getPolynomial()) {
				product.addMonomial(new Monomial(i.getDegree() + j.getDegree(), i.getCoefficient().multiplyFraction(j.getCoefficient())));
			}
		}
		//the result is simplified because we want to add the terms with same degrees
		return product.simplifyPolynomial();
	}
	
	//The terms of the result are computed this way: (c*X^d)' = c*dX^(d-1) 
	public Polynomial differentiatePolynomial() {
		Collections.sort(this.polynomial);
		
		Polynomial result = new Polynomial();
		
		for (Monomial i: this.getPolynomial()) {
			//The terms with degree 0 in the initial polynomials are ignored because those are constants and 
			//the derivative of them equals to 0, and also because the algorithm will return a monomial with 
			//degree -1 instead of a null term
			if (this.getPolynomial().get(0).getDegree() != 0)
				result.addMonomial(new Monomial(i.getDegree() - 1, i.getCoefficient().multiplyScalar(i.getDegree())));
		}
		return result.simplifyPolynomial();
	}
	
	//The terms of the result are computed this way: ∫(c*X^d)dX = c/(d+1)*X(d+1)
	public Polynomial integratePolynomial() {
		Collections.sort(this.polynomial);
		
		Polynomial result = new Polynomial();
		
		for (Monomial i: this.getPolynomial()) {
			result.addMonomial(new Monomial(i.getDegree() + 1, i.getCoefficient().divideScalar((i.getDegree() + 1))));
		}
		return result.simplifyPolynomial();
	}
	
	public Polynomial[] dividePolynomials(Polynomial polynomial) {
		Collections.sort(this.polynomial);
		Collections.sort(polynomial.getPolynomial());
		
		//The array of polynomials will get the quotient and the remainder
		Polynomial[] result = new Polynomial[2];
		result[0] = new Polynomial();
		result[1] = new Polynomial();
		
		Polynomial quotient = new Polynomial();
		Polynomial remainder = new Polynomial();
		
		//The quotient will initially be empty, and the terms will be added at each iteration of the while loop
		remainder.setPolynomial(this.getPolynomial()); 
		//The remainder gets the value of the dividend 
		//At each iteration of the while loop from the remainder is subtracted the value of the term to be included in the quotient multiplied by the divisor
		
		//The while loop exits when the reminder has the rank smaller than the divisor or all of its terms are reduced and it becomes empty
		
		while (!(remainder.getPolynomial().isEmpty()) && (remainder.getRank() >= polynomial.getRank())) {
			Monomial leadR = new Monomial(remainder.getPolynomial().get(remainder.getPolynomial().size() - 1).getDegree(), remainder.getPolynomial().get(remainder.getPolynomial().size() - 1).getCoefficient());
			Monomial leadP = new Monomial(polynomial.getPolynomial().get(polynomial.getPolynomial().size() - 1).getDegree(), polynomial.getPolynomial().get(polynomial.getPolynomial().size() - 1).getCoefficient());
			
			Polynomial t = new Polynomial();
			t.addMonomial(new Monomial(leadR.getDegree() - leadP.getDegree(), leadR.getCoefficient().divideFraction(leadP.getCoefficient())));
			
			quotient = quotient.addPolynomials(t);
			remainder = remainder.subtractPolynomials(t.multiplyPolynomials(polynomial));
			}
		
		result[0].setPolynomial(quotient.getPolynomial());
		result[1].setPolynomial(remainder.getPolynomial());
		
		return result;
	}
	
	public String toString() {
		String s = new String();
		
		for (Monomial i: this.simplifyPolynomial().getPolynomial()) {
			//puts a "+" for the positive terms. The negative ones already have a "-".
			if (i.getCoefficientFloat() >= 0) {
				s = s + "+";
			}
			//displays the coefficient in the simplest form
			//if denominator = 1 then only the numerator is written
			if (i.getCoefficient().getDenominator() == 1) {
				if ((i.getCoefficient().getNumerator() != 1) || (i.getCoefficient().getNumerator() == 1 && i.getDegree() == 0)) {
					s = s + i.getCoefficient().getNumerator();
				}
				if (i.getCoefficient().getNumerator() == -1 && i.getDegree() != 0) {
					s = s.substring(0, s.length() - 1);
				}
			}
			else {
				s = s + i.getCoefficient().getNumerator() + "/" + i.getCoefficient().getDenominator();
			}
			
			if (i.getDegree() == 1) {
				s = s + "X";
			}
			else if (i.getDegree() != 0) {
				s = s + "X^" + i.getDegree();
			}
		}
		
		if (s.length() == 0) {
			s = "0";
		}
		//if the polynomial starts with a positive term, the first character which should be "+" is not displayed
		if (s.substring(0, 1).equals("+")) {
			return s.substring(1);
		}
		else {
			return s;
		}
	}
}
