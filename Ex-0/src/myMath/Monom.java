
package myMath;
/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{

	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}

	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}
	// ***************** add your code below **********************
	public Monom (String s ) {
		Monom mo =init_string (s);
		this.set_coefficient(mo.get_coefficient());
		this.set_power(mo.get_power());
	}
	/**
	 * @return the _coefficient
	 */
	public double get_coefficient() {
		return _coefficient;
	}
	/**
	 * @return the _power
	 */
	public int get_power() {
		return _power;
	}
	/**
	 * 
	 * @param s
	 * @return  returns a string representing the specified object.
	 */
	public String toString() {
		if (this.get_power()==1)
			return (this.get_coefficient() + "x");
		else if (this.get_power()==0)
			return this.get_coefficient() + "";
		else
			return this.get_coefficient() + "x^" + this.get_power();
	}
	/**
	 * @param  x
	 * @return the value of this monom at point x.
	 */
	public double f (double x ) {

		return (get_coefficient()*Math.pow(x,get_power()));

	}
	/**
	 * This method makes a derivative of monom
	 * @param  
	 * @param 
	 */

	public void derivative ()
	{
		set_coefficient(this._coefficient*this.get_power());
		set_power(this.get_power()-1);
	
	}

	/**
	 * The method checks if the  monom is good
	 *  (if the power <0 and the coefficient =0 so this monom it's not good ) 
	 * @return value boolean 
	 */

	public boolean Is_valid (){

		if(this.get_power()<0)
		{
			return false ;

		}
		if (this.get_coefficient()==0) {
			return false ;

		}
		return true ;
	}
	/**
	 * this method multiply  2 monoms 
	 * @param m
	 */
	public void multiply(Monom m) {
		this.set_coefficient(this.get_coefficient()*m.get_coefficient());
		this.set_power(this.get_power()+m.get_power());
	} 


	/**
	 * this method adding   2 monoms 
	 * @param m
	 */

	public void add (Monom m) {
		if (m.get_power()!=this.get_power()) {
			throw new RuntimeException("ERR : can not add 2 monoms with different powers");
		}
		this.set_coefficient(m.get_coefficient()+this.get_coefficient());
	}

	/**
	 * this function substract from our Monom(in this class) other Monom that we enter , it well work only if two Monom's have the same power 
	 * @param m
	
	public void substract (Monom m) {
		if (m.get_power()!=this.get_power()) {
			throw new RuntimeException("ERR : can not sub 2 monoms with different powers");
		}
		else {
			set_coefficient(this.get_coefficient()-m.get_coefficient());
		}
	}
 */
	/**
	 * 
	 * @param m
	 * @return boolean value if the 2 monoms equals or not equals 
	 */
	public boolean equals(Monom m) {
		if (m!=null) {
			if (this.get_coefficient()==m.get_coefficient()&&this.get_power()==m.get_power()) {
				return true ;
			} 
		}
		return false ;

	}
	//****************** Private Methods and Data *****************

	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int p) {
		this._power = p;
	}

	private double _coefficient; // 
	private int _power; 
	/**
	 * 
	 * @param s
	 * @return the monom if 
	 */
	private static Monom init_string (String s ) {
		if(s==null) {throw new RuntimeException("Wrong parameter for the Monom Constractor - should not be NULL!!!");}
		double  coef = 1;
		int pow = 0;
		Monom ans = null;
		if(s.contains("x")) {
			int ind = s.indexOf("x");
			String co = s.substring(0, ind);

			try{
				double c = Double.parseDouble(co);
				coef = c;
			}
			catch(Exception e) {coef = 1;}
			if(s.length()>ind+2) {
				String po = s.substring(ind+2);
				try{
					int p = Integer.parseInt(po);
					pow = p;
				}
				catch(Exception e) {pow = 0;}
			}
		}
		else {  // just number ==> power = 0;
			coef = Double.parseDouble(s);
		}

		ans = new Monom(coef, pow);	
		return ans;


	}
}
