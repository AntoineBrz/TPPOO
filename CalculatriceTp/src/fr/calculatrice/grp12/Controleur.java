package fr.calculatrice.grp12;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class Controleur implements 	PropertyChangeListener,
									EventHandler<MouseEvent>{
	
	IAccumulateur acc = new Accumulateur((PropertyChangeListener)this);
	IVue view = new Vue();
	
	public Controleur() {
		Vue.handler = (EventHandler<MouseEvent>)this;
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
	}
              

	@Override
	public void handle(MouseEvent evt) {
		Button btn = (Button)evt.getSource();
		System.out.println(btn.getText());
	}
	
    
    public static void main(String[] args) {
        Controleur controleur = new Controleur();

        try {
			controleur.view.affiche();
		} catch (Exception e) {
			System.out.println("Application déjà affichée");
		}
    }

	
}
