/**
 * 
 */
package fr.calculatrice.grp12;

/**
*
* @author Groupe 12 : ANDRIANASOLO et BRUEZ
*/
public interface IAccumulateur {

	public void push(Double nombre);
	public void drop();
	public void swap();
	public void add();
	public void sub();
	public void mult();
	public void div();
	public void neg();
	public void backspace();
	public void accumuler(Character touche);
	public void reset();
	
}
