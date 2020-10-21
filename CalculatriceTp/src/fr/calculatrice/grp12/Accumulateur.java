package fr.calculatrice.grp12;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;


public class Accumulateur implements IAccumulateur {

	Double result;
	Pile pile;
	PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	}
	
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
	    this.pcs.removePropertyChangeListener(listener);
	}


	
	@Override
	public void push() {
//		pile.push(result);
	}

	@Override
	public void drop() {
		pile.drop();
	}

	@Override
	public void swap() {
		pile.swap();
	}

	
	/*CALCUL*/
	@Override
	public void add() {
		Double oldResult = this.result;
		Double result = (double)pile.pop() + (double)pile.pop();
		pcs.firePropertyChange("valeur", oldResult, result);
	}


	@Override
	public void sub() {
		Double oldResult = this.result;
		Double result = (double)pile.pop() - (double)pile.pop();
		pcs.firePropertyChange("valeur", oldResult, result);
	}

	@Override
	public void mult() {
		Double oldResult = this.result;
		Double result = (double)pile.pop() * (double)pile.pop();
		pcs.firePropertyChange("valeur", oldResult, result);
	}

	@Override
	public void div() {
		Double oldResult = this.result;
		Double result = (double)pile.pop() / (double)pile.pop();
		pcs.firePropertyChange("valeur", oldResult, result);
	}

	@Override
	public void neg() {
	}

	@Override
	public void backspace() {
		pile.pop();
	}

	@Override
	public void accumuler(Character character) {
		pile.push(character);
	}

	@Override
	public void reset() {
		pile.drop();
	}

}
