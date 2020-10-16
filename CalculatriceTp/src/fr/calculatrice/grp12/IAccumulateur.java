/**
 * 
 */
package fr.calculatrice.grp12;

/**
 * @author grp12
 *
 */
public interface IAccumulateur {

	public void push(Double nombre);
	public void drop();
	public void swap();
	
	public void add(Double lOperande, Double rOperande);
	public void sub(Double lOperande, Double rOperande);
	public void mult(Double lOperande, Double rOperande);
	public void div(Double lOperande, Double rOperande);
	public void neg(Double nombre);
	
	public void backspace();
	public void accumuler(Character character);
	public void reset();
	
}
