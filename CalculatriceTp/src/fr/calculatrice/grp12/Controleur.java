package fr.calculatrice.grp12;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class Controleur implements 	PropertyChangeListener,
									EventHandler<MouseEvent>{
	
	IAccumulateur acc = new Accumulateur((PropertyChangeListener)this);
	IVue view = new Vue((EventHandler<MouseEvent>)this);
	
	public Controleur() {
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
	}
              

	@Override
	public void handle(MouseEvent evt) {
		Button btn = (Button)evt.getSource();
		System.out.println(btn.getText());
	}
	

	
}
