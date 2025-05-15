import java.util.*;

/**
 * Demonstrates usage of the Polynom class.
 * Reads two polynomials from the user and performs various operations.
 */
public class PolynomMain {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("In this program, we will perform various operations on two polynomials.");
		System.out.println("*******************");
		
		// First polynomial input
		System.out.println("How many terms does the first polynomial have?");
		int size1 = scan.nextInt();
		double[] coefficients1 = new double[size1];
		int[] exponents1 = new int[size1];
		
		for(int i=0; i<size1; i++) {
			System.out.println("Enter the coefficient of term " + (i+1)+ ": ");
			coefficients1[i] = scan.nextDouble();
			System.out.println("Enter the exponent of term " + (i+1)+ ": ");
			exponents1[i] = scan.nextInt();
		}
		
		// Second polynomial input
		System.out.println("*******************");
		System.out.println("How many terms does the second polynomial have?");
		int size2 = scan.nextInt();
		double[] coefficients2 = new double[size2];
		int[] exponents2 = new int[size2];
		
		for(int i=0; i<size2; i++) {
			System.out.println("Enter the coefficient of term " + (i+1)+ ": ");
			coefficients2[i] = scan.nextDouble();
			System.out.println("Enter the exponent of term " + (i+1)+ ": ");
			exponents2[i] = scan.nextInt();
		}
		
		Polynom poly1 = new Polynom(coefficients1,exponents1);
		Polynom poly2 = new Polynom(coefficients2,exponents2);
		
		
		//למחוק!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		/*double[] coefficients3 = {1,2,3};
		int[] exponents3 = {4,5,6};
		double[] coefficients4 = {-1,-15};
		int[] exponents4 = {2,0};
		
		Polynom poly1 = new Polynom(coefficients3,exponents3);
		Polynom poly2 = new Polynom(coefficients4,exponents4);*/
		
		
		System.out.println("*******************");
		System.out.println("The first polynomial is: " + poly1.toString());
		System.out.println("The second polynomial is: " + poly2.toString());
		System.out.println("*******************");
		System.out.println("(" + poly1 +")+(" + poly2 + ") = "+ poly1.plus(poly2));
		System.out.println("*******************");
		System.out.println("(" + poly1 +")-(" + poly2 + ") = "+ poly1.minus(poly2));
		System.out.println("*******************");
		System.out.println("(" + poly1 + ")' = " + poly1.derivative());
		System.out.println("(" + poly2 + ")' = " + poly2.derivative());
		System.out.println("*******************");
		if(poly1.equals(poly2))
			System.out.println("The two polynomials are equal.");
		else
			System.out.println("The two polynomials are different.");
	}
}
