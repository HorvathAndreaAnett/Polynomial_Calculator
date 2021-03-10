package Model;

//This class implements the mathematical operations on fractions.
//Since the coefficients of the polynomials will be stored as Fractions, we need to be able to perform 
//operations on this type.

public class Fraction {
	
	private int numerator;
	private int denominator;
	
	public int getNumerator() {
		return numerator;
	}
	
	public int getDenominator() {
		return denominator;
	}
	
	public void setNumerator(int numerator) {
		this.numerator = numerator;
	}
	
	public void setDenominator(int denominator) {
		this.denominator = denominator;
	}

	public Fraction(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	public Fraction() {
	}
	
	public Fraction multiplyFraction(Fraction fraction) {
		Fraction result = new Fraction();
		result.numerator = this.numerator * fraction.numerator;
		result.denominator = this.denominator * fraction.denominator;
		return result.simplifyFraction();
	}
	
	public Fraction multiplyScalar(int scalar) {
		Fraction result = new Fraction();
		result.numerator = this.numerator * scalar;
		result.denominator = this.denominator;
		return result.simplifyFraction();
	}
	
	public Fraction divideFraction(Fraction fraction) {
		Fraction result = new Fraction();
		result.numerator = this.numerator * fraction.denominator;
		result.denominator = this.denominator * fraction.numerator;
		return result.simplifyFraction();
	}
	
	public Fraction divideScalar(int scalar) {
		Fraction result = new Fraction();
		result.numerator = this.numerator;
		result.denominator = this.denominator * scalar;
		return result.simplifyFraction();
	}
	
	public Fraction addFraction(Fraction fraction) {
		Fraction result = new Fraction();
		result.numerator = (this.numerator * fraction.denominator) + (fraction.numerator * this.denominator);
		result.denominator = this.denominator * fraction.denominator;
		return result.simplifyFraction();
	}
	
	public Fraction subtractFraction(Fraction fraction) {
		Fraction result = new Fraction();
		result.numerator = (this.numerator * fraction.denominator) - (fraction.numerator * this.denominator);
		result.denominator = this.denominator * fraction.denominator;
		return result.simplifyFraction();
	}
	
	//This method is optional, but following the calculus is easier on a simplified fraction.
	//The method uses a simple algorithm for finding the greatest common divisor between the numerator and the 
	//denominator and divides both of them by the found value of the gcd (geratest common divisor).
	
	private Fraction simplifyFraction() {
        int largest;
        Fraction result = new Fraction();
        if (this.numerator > this.denominator) {
            largest = this.numerator;
        }
        else {
            largest = this.denominator;
        }
        
        int gcd = 0;
        for (int i = largest; i >= 2; i--) {
            if (this.numerator % i == 0 && this.denominator % i == 0) {
                gcd = i;
                break;
            }
        }
        if (gcd != 0) {
            result.setNumerator(this.numerator / gcd);
            result.setDenominator(this.denominator / gcd);
        }
        else {
        	result.setNumerator(this.numerator);
        	result.setDenominator(this.denominator);
        }
        return result;
    }
}
