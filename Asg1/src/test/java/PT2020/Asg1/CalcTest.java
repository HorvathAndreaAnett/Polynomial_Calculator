package PT2020.Asg1;

import Model.*;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import Controller.*;
import View.*;

import org.junit.Test;

public class CalcTest {
    
	View view = new View();
    Controller controller = new Controller(view);

	@Test
	public void testAdition()
    {
		Polynomial poly1 = controller.createPolynomial("x^2+3x^3+x^5");
		Polynomial poly2 = controller.createPolynomial("1+2x+4x^2");
		String sum = "1+2X+5X^2+3X^3+X^5";
		assertEquals(sum, poly1.addPolynomials(poly2).toString());
    }
	
	@Test
	public void testSubtraction()
    {
		Polynomial poly1 = controller.createPolynomial("x^2+3x^3+x^5");
		Polynomial poly2 = controller.createPolynomial("1+2x+4x^2");
		String difference = "-1-2X-3X^2+3X^3+X^5";
		assertEquals(difference, poly1.subtractPolynomials(poly2).toString());
    }
	
	@Test
	public void testMultiplication()
    {
		Polynomial poly1 = controller.createPolynomial("x^2+3x^3+x^5");
		Polynomial poly2 = controller.createPolynomial("1+2x+4x^2");
		String product = "X^2+5X^3+10X^4+13X^5+2X^6+4X^7";
		assertEquals(product, poly1.multiplyPolynomials(poly2).toString());
    }
	
	@Test
	public void testQuotient()
    {
		Polynomial poly1 = controller.createPolynomial("x^2+3x^3+x^5");
		Polynomial poly2 = controller.createPolynomial("1+2x+4x^2");
		String quotient = "-3/32+3/4X+-1/8X^2+1/4X^3";
		assertEquals(quotient, poly1.dividePolynomials(poly2)[0].toString());
    }
	
	@Test
	public void testRemainder()
    {
		Polynomial poly1 = controller.createPolynomial("x^2+3x^3+x^5");
		Polynomial poly2 = controller.createPolynomial("1+2x+4x^2");
		String remainder = "3/32+-9/16X";
		assertEquals(remainder, poly1.dividePolynomials(poly2)[1].toString());
    }
	
	@Test
	public void testDifferentiation()
    {
		Polynomial poly1 = controller.createPolynomial("x^2+3x^3+x^5");
		String result = "2X+9X^2+5X^4";
		assertEquals(result, poly1.differentiatePolynomial().toString());
    }
    
	@Test
	public void testIntegration()
    {
		Polynomial poly1 = controller.createPolynomial("x^2+3x^3+x^5");
		String result = "1/3X^3+3/4X^4+1/6X^6";
		assertEquals(result, poly1.integratePolynomial().toString());
    }
}
