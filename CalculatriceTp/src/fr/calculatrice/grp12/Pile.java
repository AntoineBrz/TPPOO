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
public class Pile extends Stack<Character> {

	/**
	 * 
	 */
	public Pile() {

	}
	
	
	public void drop() {
		this.pop();
	}
	
	public void swap() {
		Character numberOnTop = this.pop();
		Character numberToTop = this.pop();
		this.add(numberOnTop);
		this.add(numberToTop);
	}
	
}
