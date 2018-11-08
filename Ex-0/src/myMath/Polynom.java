package myMath;

import java.util.ArrayList;
import java.util.Iterator;



import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author ahmad 
 * @author ashwaq 
 *
 */
public class Polynom implements Polynom_able{


	/** 
	 * return an empty (zero) polynom
	 *  
	 */
	public Polynom () {
		polynom=new ArrayList<Monom>();
	}


	/**
	 * a Copy-constructor performing a deep-copy of an other Polynom and initiate a new one with the same Monoms.
	 * @param p1 Polynom_able, the other Polynom to perform deep-copy on.
	 */
	public Polynom(Polynom_able p1){
		this.polynom = new ArrayList<>();
		Iterator<Monom> Itr = p1.iteretor();
		while(Itr.hasNext()){
			Monom toAdd = new Monom(Itr.next());
			this.add(toAdd);
		}
	}
	/**
	 * construction that bulid's Polynom from String
	 * @param s
	 */
	public Polynom(String s ) {
		polynom = new ArrayList<Monom>() ;
		Polynom th =init_from_string(s);
		Iterator<Monom> iter = th.iteretor();
		while(iter.hasNext()) {
			this.add(iter.next());
		}
	}
	/**
	 * function convert to integer 
	 * @param s
	 * @return Polynom
	 */
	private static Polynom init_from_string(String s) {
		if (s==null) {
			throw new RuntimeException("wrong input for Monom Constractor ");
		}
		String []  par=s.split(" ");
		Polynom a =new Polynom();
		for (int i=0;i<par.length;i++) {
			String t=par[i];
			if (!t.contains("+")||t.length()>1) {
				Monom v =new Monom(t);
				a.add(v);
			}
		}
		return a;

	}
	/**
	 * This function computes the value of this polynom at f(x), as a sum of monoms.
	 * @param x this Strig  is a variable for polynom
	 */
	@Override
	public double f(double x) {
		// TODO Auto-generated method stub
		double ans = 0;
		Iterator<Monom> iter = this.iteretor();
		while(iter.hasNext()) {
			ans += iter.next().f(x);

		}
		return ans;
	}
	/**
	 * Add p1 to this Polynom
	 * @param p1
	 */
	@Override
	public void add(Polynom_able p1) {
		// TODO Auto-generated method stub
		Iterator<Monom> itr =p1.iteretor();
		while (itr.hasNext()) {
			Monom mo =itr.next();
			add(mo);	
		}


	}
	/**
	 * Add m1 to this Polynom
	 * @param m1 Monom
	 */
	@Override
	public void add(Monom m1) {
		// TODO Auto-generated method stub
		Iterator<Monom> iter =this.iteretor();
		boolean found =true ;
		while (iter.hasNext()) {
			Monom m =iter.next() ;
			if (m.get_power()==m1.get_power()) {
				m.add(m1);
				found =false ;

			}
		}

		if(found ) {
			polynom.add(new Monom (m1));

			this.polynom.sort(COMP);
		}

	}
	/**
	 * Subtract p1 from this Polynom
	 * multiply by -1, then perform add method.
	 * @param p1
	 */
	@Override
	public void substract(Polynom_able p1) {
		// TODO Auto-generated method stub
		Polynom_able mi1=new Polynom("-1");
		Polynom_able p=p1.copy();
		p.multiply(mi1);
		add(p);
	}



	@Override
	/**
	 * Multiply this Polynom by p1
	 * @param p1
	 */
	public void multiply(Polynom_able p1) {

		Polynom ans = new Polynom();
		Iterator<Monom> iter = this.iteretor();
		while(iter.hasNext()) {
			Monom m = iter.next();
			Iterator<Monom>  m1 = p1.iteretor();
			while(m1.hasNext()) {
				Monom m2=m1.next();
				Monom m3 = new Monom(m2);
				m3.multiply(m);
				ans.add(m3);
			}
		}
		this.polynom=ans.polynom;
	}


	/**
	 * Test if this Polynom is logically equals to p1.
	 * @param p1
	 * @return true iff this pulynom represents the same function ans p1
	 */

	@Override

	public boolean equals(Polynom_able p1) {

		polynom.sort(COMP);
		((Polynom)p1).polynom.sort(COMP);
		if( ((Polynom)p1).polynom.size() !=polynom.size())
			return  false;

		Iterator<Monom>n1 = this.iteretor();
		Iterator<Monom>n2 = p1.iteretor();
		while(n1.hasNext()){
			Monom m1 = n1.next();
			Monom m2 = n2.next();
			if(  m1.get_power() !=m2.get_power()||m1.get_coefficient() != m2.get_coefficient())
				return false;
		}

		return true;
	}

	/**
	 * Test if this is the Zero Polynom
	 * @return
	 */
	@Override
	public boolean isZero() {
		boolean ans = false;
		if(this.polynom.size() ==0) {
			ans = true;
		}
		return ans;
	}/**
	 *this method  use to Print the Polynom 
	 * 
	 */
	public String toString() {
		String a = "";
		Iterator<Monom> iterator = this.iteretor();
		while (iterator.hasNext()) {
			Monom b = iterator.next();
			a = a + b;
			if (iterator.hasNext())
				a += "+";
		}
		return a.replaceAll("\\+-", "-");
	}
	/**
	 * Compute a value x' (x0<=x'<=x1) for with |f(x')| < eps
	 * assuming (f(x0)*f(x1)<=0, returns f(x2) such that:
	 * *	(i) x0<=x2<=x2 && (ii) f(x2)<eps
	 * @param x0 starting point
	 * @param x1 end point
	 * @param eps step (positive) value
	 * @return
	 */
	@Override
	public double root(double x0, double x1, double eps) {
		// TODO Auto-generated method stub

		double t0=this.f(x0);
		double t1=this.f(x1);
		if (t0*t1>0)
			throw new RuntimeException("Error");
		double delta_x = Math.abs(x0-x1);
		double delta_y = Math.abs(t0-t1);
		if (delta_x>eps || delta_y>eps) {
			double x_mid = (x0+x1)/2;
			double y_mid = this.f(x_mid);
			double dir = t0*y_mid;
			if(dir<0) {
				return root(x0,x_mid, eps);
			}
			else {
				return root(x_mid, x1, eps);
			}
		}
		return x0;	
	}



	/**
	 *a copy Method performing a deep-copy of the CURRENT polynom, and initiate a new one with the same	 * just copy the contractor
	 */
	@Override
	public Polynom_able copy() {
		// TODO Auto-generated method stub
		Polynom_able out = new Polynom();
		Iterator<Monom> run = this.iteretor();
		while (run.hasNext()) {
			Monom copy = run.next();
			out.add(new Monom(copy.get_coefficient(), copy.get_power()));
		}
		return out;
	}
	/**
	 * This method makes a derivative of monom
	 * @param 
	 */

	@Override
	public Polynom_able derivative() {
		Polynom nP = new Polynom();
		for (Monom m : this.polynom) {
			m.derivative();
			nP.add(m);
		}
		return nP;
	}
/**
 * this method return the area in domain 
 * @param x0 = this the start in the domain 
 * @param x1= this the end in the domain 
 * @param eps= אחוז קירוב  
 * 
 * 
 * 
 */
	@Override
	public double area(double x0, double x1, double eps) {
		// TODO Auto-generated method stub

		
		double S=0;

		while(x0<=x1) {
			S+=(this.f(x0)*eps);
			x0+=eps;
		}

		return S;

	}
	/**
	 * this functoin help us Moving in the Polynom from Monom to other Monom .
	 */
	@Override
	public Iterator<Monom> iteretor() {
		// TODO Auto-generated method stub
		return this.polynom.iterator();
	}
	// ************** Private *******************
	private ArrayList<Monom> polynom;
	private static final Monom_Comperator COMP = new Monom_Comperator();



}
