package fr.calculatrice.grp12;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controleur implements PropertyChangeListener, ActionListener {

	IAccumulateur acc = new Accumulateur();
	
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
	}


	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
	
	
}
