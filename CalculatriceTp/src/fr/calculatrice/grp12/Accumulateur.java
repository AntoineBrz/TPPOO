package fr.calculatrice.grp12;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.beans.PropertyChangeListener;


public class Accumulateur implements IAccumulateur {

	// Noms d'�v�nements � passer au contr�leur
	public static final String SAISIE = "saisieEnCours";
	public static final String PUSH = "nouveauNombre";
	public static final String RESULTAT = "nouveauResultatDeCalcul";
	
	// M�moire (stack) pour piocher les op�randes/op�rateurs de calcul
	Pile pile = new Pile();
	// Objet qui notifie l'observeur (le contr�leur enregistr� au constructeur)
	PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	// M�moire accumulant les chiffres d'une saisie en cours
	ArrayList<Character> memoireCaractere = new ArrayList<>(); 
	
	// Pour visualiser la pile apr�s chaque op�ration logique
	Logger logger;

	public Accumulateur(PropertyChangeListener propertyChangeListener) {
		/*
		 * Constructeur qui enregistre un observeur de type PropertyChangeListener,
		 * qui va �tre notifi� des types d'�v�nements d�finis dans les constantes de classe.
		 */
		this.pcs.addPropertyChangeListener(propertyChangeListener);
		logger = Logger.getLogger("Accumulateur");
	}
	

	
	@Override
	public void push(Double nombre) {
		pile.push(nombre);
	}

	@Override
	public void drop() {
		/*
		 * M�me m�thode que clear()
		 */
		pile.clear();
	}

	@Override
	public void swap() {
		/*
		 * Interchange les deux derniers �l�ments,
		 * utile pour les op�rations - et / non commutatives
		 */
		pile.swap();
	}

	
	/*CALCULS*/
	
	public void add() {
		/*
		 * Additionne les deux derniers nombres de la pile.
		 */
		Double result = (double)pile.pop() + (double)pile.pop();
		// ancien nombre saisi qui est maintenant au sommet de la pile:
		Double oldResult = this.pile.lastElement();
		pile.add(result);
		pcs.firePropertyChange(RESULTAT, oldResult, result);
		logger.log(Level.INFO,"Pile : "+pile.toString());
	}


	@Override
	public void sub() {
		/*
		 * Soustrait les deux derniers nombres de la pile 
		 * (avant-dernier - dernier).
		 */
		pile.swap();
		Double result = (double)pile.pop() - (double)pile.pop();
		Double oldResult = this.pile.lastElement();
		pile.add(result);
		pcs.firePropertyChange(RESULTAT, oldResult, result);
		logger.log(Level.INFO,"Pile : "+pile.toString());
	}

	@Override
	public void mult() {
		/*
		 * Multiplie les deux derniers nombres de la pile.
		 */
		Double result = (double)pile.pop() * (double)pile.pop();
		Double oldResult = this.pile.lastElement();
		pile.add(result);
		pcs.firePropertyChange(RESULTAT, oldResult, result);
		logger.log(Level.INFO,"Pile : "+pile.toString());
	}

	@Override
	public void div() {
		/*
		 * Fait le rapport des deux derniers nombres de la pile 
		 * (avant-dernier / dernier).
		 */
		pile.swap();
		Double result = (double)pile.pop() / (double)pile.pop();
		Double oldResult = this.pile.lastElement();
		pile.add(result);
		pcs.firePropertyChange(RESULTAT, oldResult, result);
		logger.log(Level.INFO,"Pile : "+pile.toString());
	}

	@Override
	public void neg() {
		/*
		 * Renvoie l'oppos� du dernier �l�ment de la pile.
		 */
		Double oldResult=this.pile.pop();;
		Double result=-oldResult;
		pile.add(result);
		pcs.firePropertyChange(RESULTAT,oldResult,result);
		logger.log(Level.INFO,"Pile : "+pile.toString());
	}

	@Override
	public void backspace() {
		/*
		 * Annule la saisie du dernier chiffre tap� sur la calculatrice.
		 */
		try {
			memoireCaractere.remove(memoireCaractere.size()-1);
		} catch(Exception e) {
			
		}
	}


	@Override
	public void accumuler(Character touche) {
		/*
		 * Cumule les chiffres (de 0 � 9) en cours de saisie
		 * et envoie le nombre correspondant au cumul actuel au contr�leur.
		 */
		memoireCaractere.add(touche);
		String cumul = "";
		for(Character chiffre:memoireCaractere)
			cumul += chiffre;
		pcs.firePropertyChange(SAISIE, null, Double.parseDouble(cumul));
	}



	@Override
	public void reset() {
		/*
		 * R�cup�re le cumul de chiffre fait par accumuler()
		 * qui correspond au nombre entier saisi.
		 * Envoie ce nombre entier au contr�leur 
		 * pour notifier de la fin de saisie.
		 */
		String cumul = "";
		for(Character chiffre:memoireCaractere)
			cumul += chiffre;
		Double oldNumber=pile.lastElement();
		pile.push(Double.parseDouble(cumul));
		pcs.firePropertyChange("saisie", null, Double.parseDouble(cumul));
		pcs.firePropertyChange(PUSH, oldNumber, Double.parseDouble(cumul));
		memoireCaractere = new ArrayList<Character>();
		logger.log(Level.INFO,"Pile : "+pile.toString());
	}

	
}
