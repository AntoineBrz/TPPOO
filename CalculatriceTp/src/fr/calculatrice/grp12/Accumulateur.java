package fr.calculatrice.grp12;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Accumulateur implements IAccumulateur {

	/* Noms d'événements à passer au contrôleur */
	/**
	 * Propriété de l'évènement qui met à jour la saisie en cours
	 */
	public static final String SAISIE = "saisieEnCours";
	/**
	 * Propriété de l'évènement qui termine la saisie en cours
	 */
	public static final String PUSH = "nouveauNombre";
	/**
	 * Propriété de l'évènement de fin d'une opération mathématique
	 */
	public static final String RESULTAT = "nouveauResultatDeCalcul";
	
	/* Mémoire (stack) pour piocher les opérandes/opérateurs de calcul */
	Pile pile = new Pile();
	
	/* Objet qui notifie l'observeur (le contrôleur) */
	PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	/* Mémoire accumulant les chiffres d'une saisie en cours */
	ArrayList<Character> memoireCaractere = new ArrayList<>(); 
	
	Logger logger = Logger.getLogger("Accumulateur"); /* pour visualiser la pile après chaque opération logique */

	/** 
	 * Constructeur qui enregistre un <b>observeur</b> de type {@link PropertyChangeListener},
	 * qui va être notifié des types d'évènements définis dans les <a href="../../../constant-values.html">constantes de classe</a>.
	 * 
	 * @param propertyChangeListener  le contrôleur qui est notifié 
	 * des différentes propriétés d'évènement
	 */
	public Accumulateur(PropertyChangeListener propertyChangeListener) {
		this.pcs.addPropertyChangeListener(propertyChangeListener);
	}
	

	/**
	 * Rajoute un nombre à la pile
	 * 
	 * @param nombre  le nombre à rajouter
	 */
	@Override
	public void push(Double nombre) {
		pile.push(nombre);
	}

	/**
	 * Même méthode que clear()
	 */
	@Override
	public void drop() {
		pile.clear();
	}
	
	/**
	 * Interchange les deux derniers éléments de la pile,
	 * utile pour les opérations non commutatives : 
	 * {@link #sub()} et {@link #div()}
	 */
	@Override
	public void swap() {
		pile.swap();
	}

	
	/*CALCULS*/
	
	/**
	 * Additionne les deux derniers nombres de la pile.
	 */
	@Override
	public void add() {
		if (pile.size()<2) return;
		Double result = (double)pile.pop() + (double)pile.pop();
		postOperation(result);
		logger.log(Level.INFO,"Pile : "+pile.toString());
	}

	/**
	 * Soustrait les deux derniers nombres de la pile 
	 * (avant-dernier - dernier).
	 */
	@Override
	public void sub() {
		if (pile.size()<2) return;
		pile.swap(); /* soustraction : non commutative */
		Double result = (double)pile.pop() - (double)pile.pop();
		postOperation(result);
		logger.log(Level.INFO,"Pile : "+pile.toString());
	}

	/**
	 * Multiplie les deux derniers nombres de la pile.
	 */
	@Override
	public void mult() {
		if (pile.size()<2) return;
		Double result = (double)pile.pop() * (double)pile.pop();
		postOperation(result);
		logger.log(Level.INFO,"Pile : "+pile.toString());
	}

	/**
	 * Divise les deux derniers nombres de la pile.
	 * (avant-dernier / dernier).
	 */
	@Override
	public void div() {
		if (pile.size()<2) return;
		pile.swap(); /* division : non commutative */
		Double result = (double)pile.pop() / (double)pile.pop();
		postOperation(result);
		logger.log(Level.INFO,"Pile : "+pile.toString());
	}

	/**
	 * Renvoie l'opposé du dernier élément de la pile.
	 */
	@Override
	public void neg() {
		if (pile.size()<1) return;
		Double result=-this.pile.pop();
		postOperation(result);
		logger.log(Level.INFO,"Pile : "+pile.toString());
	}

	/**
	 * Annule la saisie du dernier chiffre tapé sur la calculatrice.
	 */
	@Override
	public void backspace() {
		try {
			memoireCaractere.remove(memoireCaractere.size()-1);
			String cumul = "";
			for(Character chiffre:memoireCaractere)
				cumul += chiffre;
			pcs.firePropertyChange(SAISIE, null, Double.parseDouble(cumul));
		} catch(Exception e) {
			memoireCaractere = new ArrayList<Character>();
			pcs.firePropertyChange(SAISIE, null, 0);
		}
	}

	
	/**
	 * Cumule les chiffres (de 0 à 9) en cours de saisie
	 * et envoie le nombre correspondant au cumul actuel au contrôleur.
	 * (propriété d'évènement {@link #RESULTAT})
	 */
	private void postOperation(Double result) {
		pile.add(result);
		try {
			Double oldResult = this.pile.lastElement(); /* ancien nombre saisi */
			pcs.firePropertyChange(RESULTAT, oldResult, result);
		} catch (Exception e) {
			pcs.firePropertyChange(RESULTAT, 0., 0.);
		}
	}


	
	/**
	 * Cumule les chiffres (de 0 à 9) en cours de saisie
	 * et envoie le nombre correspondant au cumul actuel au contrôleur
	 * (propriété d'évènement {@link #SAISIE})
	 */
	@Override
	public void accumuler(Character touche) {
		memoireCaractere.add(touche);
		String cumul = "";
		for(Character chiffre:memoireCaractere)
			cumul += chiffre;
		pcs.firePropertyChange(SAISIE, null, Double.parseDouble(cumul));
	}


	/**
	 * Récupère le cumul de chiffres fait par {@link #accumuler(Character)}
	 * qui correspond au nombre entier saisi.
	 * Envoie ce nombre entier au contrôleur 
	 * pour notifier de la fin de saisie 
	 * (propriété d'évènement {@link #PUSH})
	 */
	@Override
	public void reset() {
		String cumul = "";
		for(Character chiffre:memoireCaractere)
			cumul += chiffre;
		Double oldNumber=pile.lastElement();
		pcs.firePropertyChange(PUSH, oldNumber, Double.parseDouble(cumul));
		memoireCaractere = new ArrayList<Character>();
		pile.push(Double.parseDouble(cumul));
		logger.log(Level.INFO,"Pile : "+pile.toString());
	}

	
}
