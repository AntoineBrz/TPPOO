/**
 * 
 */
package fr.calculatrice.grp12;

import java.util.Stack;

/**
*
* @author Groupe 12 : ANDRIANASOLO et BRUEZ
*/
@SuppressWarnings("serial")
public class Pile extends Stack<Double> {

	/**
	 * La classe qui garde en mémoire les nombres utilisés
	 * pour les calculs
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
