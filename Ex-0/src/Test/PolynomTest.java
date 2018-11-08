package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import myMath.Monom;
import myMath.Polynom;

class PolynomTest {


	@Test
	void testPolynom() {
		Polynom p = new Polynom();
		if(!p.isZero())
			fail("Eror , Empty Polynom");
	}


	@Test
	void testPolynomString() {
		Polynom p=new Polynom();		
		Polynom p1=new Polynom();
		p.add(new Monom(3,8));
		p.add(new Monom(5,3));
		p1.add(new Monom(3,8));
		p1.add(new Monom(5,3));
		if(!p.equals(p1))
			fail("Eror , uncorrect constractor");
	}



	@Test
	void testF() {
		double a = Math.random()*1000 ;
		int _pow = (int)Math.random()*1000 ;
		Monom m1 = new Monom(a , _pow);
		int _x = (int)Math.random()*1000 ;
		Polynom p = new Polynom();
		p.add(m1);
		if(p.f(_x) != (a * Math.pow(_x,_pow)))
			fail("Eror , value of Y is uncorrect");
	}

	@Test
	void testAddPolynom_able() {
		Polynom p1=new Polynom();
		p1.add(new Monom(2, 3));
		p1.add(new Monom(6, 2));
		p1.add(new Monom(7, 1));
		p1.add(new Monom(10, 0));
		Polynom p2=new Polynom();
		p1.add(new Monom(4, 2));
		p1.add(new Monom(2, 1));
		p1.add(new Monom(2, 0));
		p1.add(p2);
		Polynom s= new Polynom();
		p1.add(new Monom(2, 3));
		p1.add(new Monom(10, 2));
		p1.add(new Monom(9, 1));
		p1.add(new Monom(12, 0));

		if(!s.equals(p2))
			fail("Not yet implemented");
	}

	@Test
	void testAddMonom() {
		Polynom p1=new Polynom();
		p1.add(new Monom(2,2));
		p1.add(new Monom(2,1));
		p1.add(new Monom(2,0));
		Monom p2=new Monom(2,2);
		p1.add(p2);
		Polynom p3=new Polynom();
		p3.add(new Monom(4,2));
		p3.add(new Monom(2,1));
		p3.add(new Monom(2,0));
		if(!p3.equals(p1))
			fail("Eror , fail adding Monom");
	}

	@Test
	void testSubstractPolynom_able() {
		Polynom p1=new Polynom();
		p1.add(new Monom(2, 3));
		p1.add(new Monom(6, 2));
		p1.add(new Monom(7, 1));
		p1.add(new Monom(10, 0));
		Polynom p2=new Polynom();
		p1.add(new Monom(4, 2));
		p1.add(new Monom(2, 1));
		p1.add(new Monom(2, 0));
		p1.substract(p2);
		Polynom s= new Polynom();
		p1.add(new Monom(2, 3));
		p1.add(new Monom(2, 2));
		p1.add(new Monom(5, 1));
		p1.add(new Monom(8, 0));

		if(!s.equals(p2))
			fail("Not yet implemented");
	}

	@Test
	void testMultiply() {
		Polynom p1=new Polynom();
		p1.add(new Monom(2, 3));
		p1.add(new Monom(10, 0));
		Polynom p2=new Polynom();
		p1.add(new Monom(4, 2));
		p1.add(new Monom(2, 0));
		p1.add(p2);
		Polynom s= new Polynom();
		p1.add(new Monom(8, 5));
		p1.add(new Monom(6, 3));
		p1.add(new Monom(5, 1));
		p1.add(new Monom(20, 0));

		if(!s.equals(p2))
			fail("Not yet implemented");
	}

	@Test
	void testEqualsPolynom_able() {
		Polynom p1=new Polynom();
		p1.add(new Monom(2,2));
		p1.add(new Monom(2,0));
		Polynom p2=new Polynom();
		p2.add(new Monom(2,2));
		p2.add(new Monom(2,0));
		if(!p1.equals(p2))
			fail("Eror , equal function not working");
	}

	@Test
	void testIsZero() {
		Polynom p = new Polynom() ;
		if(!p.isZero())
			fail("Eror , checking if Polynom empty eror");
	}

	@Test
	void testRoot() {
		Polynom p1=new Polynom();
		p1.add(new Monom(1,2));
		p1.add(new Monom(-6,1));
		p1.add(new Monom(5,0));
	
		double root=p1.root(0, 5, 0.01);
		if(Math.abs(p1.f(root))>=0.01)
			fail("Eror , uncorrect Root");
	}


	@Test
	void testDerivative() {
		Polynom p1=new Polynom();
		p1.add(new Monom(2,2));
		p1.add(new Monom(2,1));
		Polynom p2=new Polynom();
		p2.add(new Monom(4,1));
		p2.add(new Monom(2,0));
		if(!p1.derivative().equals(p2))
			fail("Eror , uncorrect Derivative");
	}

	@Test
	void testArea() {
		/*Polynom p1=new Polynom();
		p1.add(new Monom(1,2));
		p1.add(new Monom(6,1));
		p1.add(new Monom(5,0));
		        double expected = -32/3;
				if(expected!=(p1.area(-5, -1, 0.00))) {
					fail("Not yet implemented");
					
*/
		    }
	}



