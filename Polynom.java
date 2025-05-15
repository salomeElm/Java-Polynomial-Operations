import java.util.*;

/**
 * Represents a polynomial composed of multiple PolyTerm objects.
 */
public class Polynom {

	private ArrayList<PolyTerm> poly; // List of polynomial terms

    /**
     * Constructs a polynomial from arrays of coefficients and exponents.
     * Sorts the terms from highest to lowest exponent.
     */
	public Polynom(double[] coefficients, int[] exponents) throws IllegalArgumentException {
		if(coefficients.length != exponents.length)
			throw new IllegalArgumentException("The array of the coefficients and the array of the exponents must have the same length.");

		poly = new ArrayList<PolyTerm>();
		for(int i=0; i<coefficients.length; i++) {
			poly.add(new PolyTerm(coefficients[i],exponents[i]));
		}

		//Sort the terms by exponent in descending order
		poly.sort((t1, t2) -> Integer.compare(t2.getExponent(), t1.getExponent()));
	}

    /**
     * Private constructor used internally to create a polynomial from a list of terms.
     */
	private Polynom(ArrayList<PolyTerm> poly) {
		this.poly = poly;
	}

	
    /**
     * Adds this polynomial to another polynomial.
     */
	public Polynom plus(Polynom other) {
		ArrayList<PolyTerm> sumPoly= new ArrayList<PolyTerm>(); // List of terms representing the resulting polynomial

		int i=0 , j=0;
		while(i < this.poly.size() && j < other.poly.size()) {
			PolyTerm term1 = this.poly.get(i); 
			PolyTerm term2 = other.poly.get(j); 

			// If both terms have the same exponent, add their coefficients:
			if(term1.getExponent() == term2.getExponent()) {
				sumPoly.add(new PolyTerm(term1.getCoefficient()+term2.getCoefficient(),term1.getExponent()));
				i++;
				j++;
			}
			//If this term has a higher exponent, add it as is:
			else if(term1.getExponent() > term2.getExponent()) {
				sumPoly.add(term1);
				i++;
			}
			//If the other term has a higher exponent, add it as is:
			else {
				sumPoly.add(term2);
				j++;
			}
		}

		// If there are remaining terms in this polynomial, add them
		while(i<this.poly.size()) {
			sumPoly.add(this.poly.get(i));
			i++;
		}

		// If there are remaining terms in the other polynomial, add them
		while(j<other.poly.size()) {
			sumPoly.add(other.poly.get(j));
			j++;
		}

		return new Polynom(sumPoly);
	}

	/**
	 * Subtracts another polynomial from this one.
	 */
	public Polynom minus(Polynom other) {
		ArrayList<PolyTerm> diffPoly = new ArrayList<PolyTerm>(); 

		int i=0 , j=0;
		while(i < this.poly.size() && j < other.poly.size()) {
			PolyTerm term1 = this.poly.get(i); 
			PolyTerm term2 = other.poly.get(j); 

			// If both terms have the same exponent, subtract their coefficients:
			if(term1.getExponent() == term2.getExponent()) {
				diffPoly.add(new PolyTerm(term1.getCoefficient()-term2.getCoefficient(),term1.getExponent()));
				i++;
				j++;
			}
			// If this term's exponent is greater, copy it as is:
			else if(term1.getExponent() > term2.getExponent()) {
				diffPoly.add(term1);
				i++;
			}
			//If the other's exponent is greater, add it with a negative coefficient
			else {
				diffPoly.add(new PolyTerm(-term2.getCoefficient(),term2.getExponent()));
				j++;
			}
		}

		// If there are remaining terms in this polynomial, copy them
		while(i<this.poly.size()) {
			diffPoly.add(this.poly.get(i));
			i++;
		}

		// If there are remaining terms in the other polynomial, add them with negative coefficients
		while(j<other.poly.size()) {
			diffPoly.add(new PolyTerm(-other.poly.get(j).getCoefficient(),other.poly.get(j).getExponent()));
			j++;
		}

		return new Polynom(diffPoly);
	}

	/**
	 * Returns the derivative of this polynomial.
	 */
	public Polynom derivative() {
		ArrayList<PolyTerm> derivative = new ArrayList<PolyTerm>(); //רשימה של איברים שמייצגים את פולינום הנגזרת
		double coefficients;
		int exponents;

		for(int i=0; i<this.poly.size(); i++) {
			coefficients = this.poly.get(i).getCoefficient();
			exponents = this.poly.get(i).getExponent();
			if(exponents>0) //Only include terms with exponent > 0 (derivative of constant is 0)
				derivative.add(new PolyTerm(coefficients*exponents,exponents-1));
		}

		return new Polynom(derivative);
	}

	/**
	 * Returns a string representation of the polynomial.
	 */
	public String toString() {
		double coefficients;
		int exponents;
		String str = "";

		for(int i=0; i<this.poly.size(); i++) {
			coefficients = this.poly.get(i).getCoefficient();
			exponents = this.poly.get(i).getExponent();

			//If coefficient is zero, skip the term
			if(coefficients == 0) {
				if(i == this.poly.size()-1 && str == "") //If it's the last term and nothing was printed, it's the zero polynomial
					str += 0;
				continue;
			}
			
			if(coefficients>0 && i!=0) //Handle positive sign (except for the first term)
				str += "+";
			
			// Handle coefficients:
			// Print the coefficient unless it's 1 or -1 (print just '-')
			// The handling of the constant term (when exponent is 0) will be done later.
			if(coefficients != 1 && exponents != 0) {
				if(coefficients == -1) 
					str += "-";
				else
					str += coefficients;
			}

			// Handle exponents:
			if(exponents == 1) // For exponent 1, just print 'x'
				str += "x";
			else if(exponents==0) // For constant term (when exponent is 0), just print the coefficient.
				str += coefficients;
			else 
				str += "x^" + exponents;
		}
		return str;
	}

	/**
	 * Compares this polynomial with another object for equality.
	 */
	public boolean equals(Object obj) {
		if(!(obj instanceof Polynom))
			return false;

		Polynom other = (Polynom)obj;

		//Check if both have the same number of terms
		if(this.poly.size() != other.poly.size())
			return false;

		PolyTerm term1;
		PolyTerm term2;

		for(int i =0; i<this.poly.size(); i++) {
			term1 = this.poly.get(i);
			term2 = other.poly.get(i);
			//Compare both coefficient and exponent
			if(term1.getCoefficient() != term2.getCoefficient() 
					|| term1.getExponent() != term2.getExponent())
				return false;
		}
		return true;
	}

}
