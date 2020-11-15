package fr.calculatrice.grp12;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

/**
*
* @author Groupe 12 : ANDRIANASOLO et BRUEZ
*/
public class Controleur implements 	PropertyChangeListener,
									EventHandler<MouseEvent>{
	
	IAccumulateur acc = new Accumulateur((PropertyChangeListener)this);
	IVue view = new Vue((EventHandler<MouseEvent>)this);
	
	public Controleur() {
	}
	
	/**
	 * Prend en charge les actions logiques implémentées par l'accumulateur
	 * et les récupère pour modifier les données de la vue.
	 * Les différents cas d'action logiques sont les constantes 
	 * de la classe Accumulateur.
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		if (evt.getPropertyName().equals(Accumulateur.SAISIE)) { // en X
			view.changeX(evt.getNewValue().toString());
		}
		
		else if (evt.getPropertyName().equals(Accumulateur.PUSH)) { // en Y
			ArrayList<String> newData = new ArrayList<String>();
			newData.add(evt.getOldValue().toString());
			newData.add(evt.getNewValue().toString());
			view.change(newData);
		}
		
		else if (evt.getPropertyName().equals(Accumulateur.RESULTAT)) { 
			view.changeX(evt.getNewValue().toString()); // répliquer le résultat de calcul en X
			ArrayList<String> newData = new ArrayList<String>();
			newData.add(evt.getOldValue().toString());
			newData.add(evt.getNewValue().toString());
			view.change(newData);
		}
		
	}
              
	/**
	 * Traitement de l'action à effectuer grâce au texte du btn 
	 * (1 caractère)
	 */
	@Override
	public void handle(MouseEvent evt) {
		Button btn = (Button)evt.getSource();
		handleButtonCharacter(btn.getText().charAt(0));
	}
	
	/**
	 * Effectue la délégation d'action vers le modèle (Accumulateur).
	 * La méthode propertyChange() se chargera de récupérer traiter les actions
	 * du modèle ensuite, et de les passer à la vue.
	 */
    private void handleButtonCharacter(char touche) {
    	String op = String.valueOf(touche); /* pour éviter charAt() à chaque "case" */
    	switch (op) {
    	case "+":
    		this.acc.add();
    		break;
    	case "-":
    		this.acc.sub();
    		break;
    	case "x":
    		this.acc.mult();
    		break;
    	case "/":
    		this.acc.div();
    		break;
    	case "↵":
    		this.acc.reset();
    		this.view.changeX("");
    		break;
    	case "←":
    		this.acc.backspace();
    		break;
    	default: /* pour les chiffres 0 à 9 */
    		this.acc.accumuler(touche);
    	} 
	}
    
    
	/**
	 * Lancement de l'application via un contrôleur
	 * appelant la méthode affiche() de la vue.
	 * 
	 * @param args Arguments en ligne de commande
	 */
	public static void main(String[] args) {
       
		Controleur controleur = new Controleur();

        try {
			controleur.view.affiche();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	
}
