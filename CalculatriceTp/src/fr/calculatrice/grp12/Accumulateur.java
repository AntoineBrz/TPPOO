package fr.calculatrice.grp12;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;


public class Accumulateur implements IAccumulateur {

	
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
		pile.clear();
	}

	@Override
	public void swap() {
		pile.swap();
	}

	
	/*CALCUL*/
	
	public void add() {
		Double oldResult = this.pile.lastElement();
		Double result = (double)pile.pop() + (double)pile.pop();
		pile.add(result);
		pcs.firePropertyChange("valeur", oldResult, result);
	}


	@Override
	public void sub() {
		Double oldResult = this.pile.lastElement();
		Double result = (double)pile.pop() - (double)pile.pop();
		pile.add(result);
		pcs.firePropertyChange("valeur", oldResult, result);
	}

	@Override
	public void mult() {
		Double oldResult = this.pile.lastElement();
		Double result = (double)pile.pop() * (double)pile.pop();
		pile.add(result);
		pcs.firePropertyChange("valeur", oldResult, result);
	}

	@Override
	public void div() {
		Double oldResult =this.pile.lastElement();
		Double result = (double)pile.pop() / (double)pile.pop();
		pile.add(result);
		pcs.firePropertyChange("valeur", oldResult, result);
	}

	@Override
	public void neg() {
		Double oldResult=this.pile.pop();;
		Double result=-oldResult;
		pile.add(result);
		pcs.firePropertyChange("valeur",oldResult,result);
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


	@Override
	public PropertyChangeSupport getpcs() {
		// TODO Auto-generated method stub
		return this.pcs;
	}
	
	
}
