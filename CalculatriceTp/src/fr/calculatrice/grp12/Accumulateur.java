package fr.calculatrice.grp12;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.beans.PropertyChangeListener;


public class Accumulateur implements IAccumulateur {

	public static final String SAISIE = "saisie";
	public static final String PUSH = "nouveauNombre";
	public static final String RESULTAT = "nouveauResultatDeCalcul";
	Pile pile = new Pile();
	PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	ArrayList<Character> memoireCharacter = new ArrayList<>();
	
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
		Double result = (double)pile.pop() + (double)pile.pop();
		Double oldResult = this.pile.lastElement();
		pile.add(result);
		pcs.firePropertyChange(RESULTAT, oldResult, result);
	}


	@Override
	public void sub() {
		pile.swap();
		Double result = - (double)pile.pop() + (double)pile.pop();
		Double oldResult = this.pile.lastElement();
		pile.add(result);
		pcs.firePropertyChange(RESULTAT, oldResult, result);
	}

	@Override
	public void mult() {
		Double result = (double)pile.pop() * (double)pile.pop();
		Double oldResult = this.pile.lastElement();
		pile.add(result);
		pcs.firePropertyChange(RESULTAT, oldResult, result);
	}

	@Override
	public void div() {
		pile.swap();
		Double result = (double)pile.pop() / (double)pile.pop();
		Double oldResult = this.pile.lastElement();
		pile.add(result);
		pcs.firePropertyChange(RESULTAT, oldResult, result);
	}

	@Override
	public void neg() {
		Double oldResult=this.pile.pop();;
		Double result=-oldResult;
		pile.add(result);
		pcs.firePropertyChange(RESULTAT,oldResult,result);
	}

	@Override
	public void backspace() {
		try {
			memoireCharacter.remove(memoireCharacter.size()-1);
		} catch(Exception e) {
			
		}
	}


	@Override
	public void accumuler(Character touche) {
		memoireCharacter.add(touche);
		String cumul = "";
		for(Character chiffre:memoireCharacter)
			cumul += chiffre;
		pcs.firePropertyChange(SAISIE, null, Double.parseDouble(cumul));
	}



	@Override
	public void reset() {
		/*
		 * Appui sur entrée
		 */
		String cumul = "";
		for(Character chiffre:memoireCharacter)
			cumul += chiffre;
		Double oldNumber=pile.lastElement();
		pile.push(Double.parseDouble(cumul));
		pcs.firePropertyChange("saisie", null, Double.parseDouble(cumul));
		pcs.firePropertyChange(PUSH, oldNumber, Double.parseDouble(cumul));
		memoireCharacter = new ArrayList<Character>();
	}

	
}
