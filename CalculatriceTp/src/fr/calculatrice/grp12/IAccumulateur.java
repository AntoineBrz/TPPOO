/**
 * 
 */
package fr.calculatrice.grp12;
import java.beans.PropertyChangeSupport;

/**
 * @author grp12
 *
 */
public interface IAccumulateur {

	public PropertyChangeSupport getpcs();
	public void push(Double nombre);
	public void drop();
	public void swap();
	public void add();
	public void sub();
	public void mult();
	public void div();
	public void neg();
	public void backspace();
//	public void accumuler(Character character);
//	public void reset();
	
}
