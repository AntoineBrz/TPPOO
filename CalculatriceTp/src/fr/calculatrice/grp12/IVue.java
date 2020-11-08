package fr.calculatrice.grp12;

import java.util.ArrayList;

public interface IVue {
	
	public void change(ArrayList<String> data);
	public void affiche() throws Exception; //si affiché plus d'1 fois
	
}
