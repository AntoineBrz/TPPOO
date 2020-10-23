package fr.calculatrice.grp12;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;


public class Accumulateur implements IAccumulateur {

	Double result;
	Pile pile = new Pile();
	PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	
	public Accumulateur() {
		
	}
	
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	}
	
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
	    this.pcs.removePropertyChangeListener(listener);
	}

	
	@Override
	public void push(Double nombre) {
		pile.push(nombre);
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

	public Pile getPile() {
		return this.pile;
	}
	
	public void setPile(Pile pile) {
		this.pile = pile;
	}
	
	
}
