package fr.calculatrice.grp12;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.beans.PropertyChangeListener;


public class Accumulateur implements IAccumulateur {

	
	Pile pile = new Pile();
	PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	ArrayList<Character> memoireChar = new ArrayList<>();
	
	public Accumulateur(PropertyChangeListener propertyChangeListener) {
		this.pcs.addPropertyChangeListener(propertyChangeListener);
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
		System.out.println(pile);
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
		try {
			memoireChar.remove(memoireChar.size()-1);
		} catch(IndexOutOfBoundsException e) {
			// plus rien dans la mémoire
		}
	}


	@Override
	public void accumuler(Character touche) {
		memoireChar.add(touche);
		String cumul = "";
		for(Character chiffre:memoireChar)
			cumul += chiffre;
		//pile.push(Double.parseDouble(cumul));
		pcs.firePropertyChange("saisie", null, Double.parseDouble(cumul));
	}



	@Override
	public void reset() {
		/*
		 * Appui sur entrée
		 */
		String cumul = "";
		for(Character chiffre:memoireChar)
			cumul += chiffre;
		pile.push(Double.parseDouble(cumul));
		pcs.firePropertyChange("saisie", null, Double.parseDouble(cumul));
		System.out.println(pile);
		// 2 parce que la pile initialisée avec [0.0] (????)
		pcs.firePropertyChange("nouveauNb", null, Double.parseDouble(cumul));
		memoireChar = new ArrayList<Character>();
	}



	public Pile getPile() {
		return this.pile;
	}
	
	
	public void setPile(Pile pile) {
		this.pile = pile;
	}


	
}
