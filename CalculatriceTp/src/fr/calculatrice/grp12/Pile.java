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

	}
	
	
	public void drop() {
		while(true) {
			try {
				this.pop();
			} catch(Exception e) {
				break;
			}
		}
	}
	
	public void swap() {
		Double numberOnTop = this.pop();
		Double numberToTop = this.pop();
		this.add(numberOnTop);
		this.add(numberToTop);
	}
	
	
}
