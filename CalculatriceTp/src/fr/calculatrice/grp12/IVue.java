package fr.calculatrice.grp12;

import java.util.ArrayList;

public interface IVue {
	
	public void changeX(String string);
	public void change(ArrayList<String> data);
	public void affiche() throws Exception;
	
}
