package fr.calculatrice.grp12;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.beans.PropertyChangeListener;


public class Accumulateur implements IAccumulateur {

	// Noms d'évènements à passer au contrôleur
	public static final String SAISIE = "saisieEnCours";
	public static final String PUSH = "nouveauNombre";
	public static final String RESULTAT = "nouveauResultatDeCalcul";
	
	// Mémoire (stack) pour piocher les opérandes/opérateurs de calcul
	Pile pile = new Pile();
	// Objet qui notifie l'observeur (le contrôleur enregistré au constructeur)
	PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	// Mémoire accumulant les chiffres d'une saisie en cours
	ArrayList<Character> memoireCaractere = new ArrayList<>(); 
	
	// Pour visualiser la pile après chaque opération logique
	Logger logger;

	public Accumulateur(PropertyChangeListener propertyChangeListener) {
		/*
		 * Constructeur qui enregistre un observeur de type PropertyChangeListener,
		 * qui va être notifié des types d'évènements définis dans les constantes de classe.
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
		 * Même méthode que clear()
		 */
		pile.clear();
	}

	@Override
	public void swap() {
		/*
		 * Interchange les deux derniers éléments,
		 * utile pour les opérations - et / non commutatives
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
		 * Renvoie l'opposé du dernier élément de la pile.
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
		 * Annule la saisie du dernier chiffre tapé sur la calculatrice.
		 */
		try {
			memoireCaractere.remove(memoireCaractere.size()-1);
		} catch(Exception e) {
			
		}
	}


	@Override
	public void accumuler(Character touche) {
		/*
		 * Cumule les chiffres (de 0 à 9) en cours de saisie
		 * et envoie le nombre correspondant au cumul actuel au contrôleur.
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
		 * Récupère le cumul de chiffre fait par accumuler()
		 * qui correspond au nombre entier saisi.
		 * Envoie ce nombre entier au contrôleur 
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
