package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import myMath.Monom;

class MonomTest {

	@Test
	void testMonomDoubleInt() {
		double a = Math.random()*1000 ;
		int _pow = (int)Math.random()*1000 ;
		Monom m = new Monom(a , _pow);
		if(m.get_coefficient() != a || m.get_power() != _pow)
			fail("Eror , uncorect construction");
	}

	@Test
	void testMonomMonom() {
		double a = Math.random()*1000 ;
		int _pow = (int)Math.random()*1000 ;
		Monom m1 = new Monom(a , _pow);
		if(m1.get_coefficient() != a || m1.get_power() != _pow)
			fail("Eror , uncorrect construction");
	}

	@Test
	void testMonomString() {
		double a = Math.random()*1000 ;
		int _pow = (int)Math.random()*1000 ;
		String _str = "" + a + "x^" + _pow ;
		Monom m = new Monom(_str);
		if(m.get_coefficient() != a || m.get_power() != _pow)
			fail("Eror , uncorrect construction");
	}

	@Test
	void testAdd() {
		double a1 = Math.random()*1000 ;
		double a2 = Math.random()*1000 ;
		int _pow = (int)Math.random()*1000 ;
		Monom m1 = new Monom(a1 , _pow);
		Monom m2 = new Monom(a2 , _pow);
		m1.add(m2);
		if(m1.get_coefficient() != (a1 + a2))
			fail("Eror , adding mistake");
	}

	

	@Test
	void testMultiply() {
		double a1 = Math.random()*1000 ;
		double a2 = Math.random()*1000 ;
		int _pow1 = (int)Math.random()*1000 ;
		int _pow2 = (int)Math.random()*1000 ;
		Monom m1 = new Monom(a1 , _pow1);
		Monom m2 = new Monom(a2 , _pow2);
		m1.multiply(m2);
		if(m1.get_coefficient() != (a1 * a2) || m1.get_power() != (_pow1 + _pow2) )
			fail("Eror , Multiply mistake");
	}

	@Test
	void testDerivative() {
		double a = Math.random()*1000 ;
		int _pow = (int)Math.random()*1000 ;
		Monom m = new Monom(a , _pow);
		m.derivative();
		if(m.get_coefficient() != (a * _pow) || m.get_power() != (_pow - 1))
			fail("Eror , uncorrect Derivative");
	}

	@Test
	void testF() {
		double a = Math.random()*100 ;
		int _pow = (int)Math.random()*100 ;
		Monom m = new Monom(a , _pow);
		int _x = (int)Math.random()*100 ;
		if((m.f(_x)) != (a * Math.pow(_x, _pow)) )
			fail("Eror , uncorrect Y value");
	}

	@Test
	void testToString() {
		double a=Math.random()*100+1;
		int _pow=(int)(Math.random()*10);
		Monom m=new Monom(a,_pow);
		if(_pow==0) {
			if(!m.toString().equals(""+m.get_coefficient()+"x^"+m.get_power()))
				fail("ERR:the power or the coef are not equals");
		}
		else if(_pow==1) {
			if(!m.toString().equals(""+m.get_coefficient()+"x"))
				fail("ERR:the power or the coef are not equals");
		}
		else if(_pow==0) 
			if(!m.toString().equals(""+m.get_coefficient()))
				fail("ERR:the power or the coef are not equals");

	}
}


