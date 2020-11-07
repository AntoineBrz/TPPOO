/**
 * 
 */
package fr.calculatrice.grp12;

import java.util.Stack;

/**
 * @author grp12
 *
 */
@SuppressWarnings("serial")
public class Pile extends Stack<Double> {

	/**
	 * 
	*/
	public Pile() {
		this.add(0.);
	}
		
	public void swap() {
		Double numberOnTop = this.pop();
		Double numberToTop = this.pop();
		this.add(numberOnTop);
		this.add(numberToTop);
	}
	
}
