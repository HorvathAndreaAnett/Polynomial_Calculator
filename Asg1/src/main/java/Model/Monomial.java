package Model;

//This class implements the essential component of the polynomial.
//The polynomial will be represented as an array list of monomials.
//The structure of a monomial is: coefficient*X^degree.
//The class implements the interface Comparable to make possible the ordering of the monomials in a polynomial (lines 40-42).

public class Monomial implements Comparable<Monomial> {
	
	private int degree;
	private Fraction coefficient;
	
	public Monomial(int degree, Fraction coefficient) {
		this.degree = degree;
		this.coefficient = coefficient;
	}
	
	public int getDegree() {
		return this.degree;
	}
	
	public Fraction getCoefficient() {
		return this.coefficient;
	}
	
	//Used to have access to the real value of the fraction.	
	public int getCoefficientFloat() {
		return this.getCoefficient().getNumerator() / this.getCoefficient().getDenominator();
	}
	
	public void setDegree(int degree) {
		this.degree = degree;
	}
	
	public void setCoefficient(Fraction coefficient) {
		this.coefficient = coefficient;
	}

	//Orders the monomials in increasing order of their degrees.
	public int compareTo(Monomial o) {
		return this.degree - o.getDegree();
	}
}
