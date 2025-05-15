/**
 * Represents a single term in a polynomial, with a coefficient and an exponent.
 */
public class PolyTerm {
	private double coefficient; 
	private int exponent; 
	
    /**
     * Constructs a polynomial term with the given coefficient and exponent.
     */
	public PolyTerm(double coefficients, int exponents) {
		this.coefficient = coefficients;
		this.exponent = exponents;
	}
	
    /**
     * Returns the coefficient of this term.
     */
	public double getCoefficient() {
		return coefficient;
	}
	
    /**
     * Returns the exponent of this term.
     */
	public int getExponent() {
		return exponent;
	}
}
