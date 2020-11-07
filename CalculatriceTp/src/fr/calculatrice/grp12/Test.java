package fr.calculatrice.grp12;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		Accumulateur acc = new Accumulateur();
		Pile p  = acc.getPile();
		
		for (double i=0;i<10;i++)
			p.push(i);
		
		
		// Test de la pile
		System.out.println("Pile initiale 	:	" + p.toString());
		
		p.swap();
		System.out.println("Pile swap 	:	" + p.toString());
		
		p.clear();
		System.out.println("Pile drop 	:	" + p.toString());

		
		// Test de l'accumulateur
		acc.add();
		
			
		
	}
	
	
}
