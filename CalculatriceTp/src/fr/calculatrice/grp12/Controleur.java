package fr.calculatrice.grp12;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controleur implements PropertyChangeListener, ActionListener {

	IAccumulateur acc = new Accumulateur();
	PropertyChangeSupport pcs;
	
	public Controleur() {
		this.pcs=acc.getpcs();
		pcs.addPropertyChangeListener(this);
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
	}
              

	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
	
	
}
