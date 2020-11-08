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
	IVue view = new Vue();
	ArrayList<Character> memoire = new ArrayList<Character>();
	
	public Controleur() {
		Vue.handler = (EventHandler<MouseEvent>)this;
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		/*
		 * Avertit le controleur d'une nouvelle valeur à passer à la vue
		 */
		if (evt.getPropertyName().equals("valeur")) {
			ArrayList<String> newData = new ArrayList<String>();
			newData.add(evt.getOldValue().toString());
			newData.add(evt.getNewValue().toString());
			
			view.change(newData);
			
		}

		if (evt.getPropertyName().equals("saisie")) { // en X
			view.changeX(evt.getNewValue().toString());
		}
		
		if (evt.getPropertyName().equals("nouveauNb")) { // en Y
			ArrayList<String> newData = new ArrayList<String>();
			newData.add(evt.getNewValue().toString());
			view.change(newData);
		}
		
	}
              

	@Override
	public void handle(MouseEvent evt) {
		Button btn = (Button)evt.getSource();
		System.out.println(btn.getText());
		calculer(btn.getText().charAt(0));
	}
	
    
    private void calculer(char touche) {
    	String op = String.valueOf(touche); // eviter charAt
    	// TODO faire usage de Vue.ADD = "+" static...
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
    	default: // chiffres 0 à 9
    		this.acc.accumuler(touche);
    	} 
	}
    
    

	public static void main(String[] args) {
        Controleur controleur = new Controleur();

        try {
			controleur.view.affiche();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	
}
