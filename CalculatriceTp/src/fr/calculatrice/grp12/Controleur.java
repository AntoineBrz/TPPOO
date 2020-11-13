package fr.calculatrice.grp12;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class Controleur implements 	PropertyChangeListener,
									EventHandler<MouseEvent>{
	
	IAccumulateur acc = new Accumulateur((PropertyChangeListener)this);
	IVue view = new Vue((EventHandler<MouseEvent>)this);
	
	public Controleur() {
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		/*
		 * Prend en charge les actions logiques implémentées par l'accumulateur
		 * et les récupère pour modifier les données de la vue.
		 * Les différents cas d'action logiques sont les constantes 
		 * de la classe Accumulateur.
		 */

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
              

	@Override
	public void handle(MouseEvent evt) {
		/*
		 * Traitement de l'action à effectuer grâce à texte du btn 
		 * (1 caractère)
		 */
		Button btn = (Button)evt.getSource();
		handleButtonCharacter(btn.getText().charAt(0));
	}
	
    
    private void handleButtonCharacter(char touche) {
    	/*
    	 * Effectue la délégation d'action vers le modèle (Accumulateur).
    	 * La méthode propertyChange se chargera de récupérer traiter les actions
    	 * du modèle ensuite, et de les passer à la vue.
    	 */
    	String op = String.valueOf(touche); // pour éviter charAt à chaque case
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
    	default: // pour les chiffres 0 à 9
    		this.acc.accumuler(touche);
    	} 
	}
    
    
    
	public static void main(String[] args) {
		/*
		 * Lancement de l'application via le contrôleur
		 * et l'appel de méthode affiche() de la vue.
		 */
        Controleur controleur = new Controleur();

        try {
			controleur.view.affiche();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	
}
