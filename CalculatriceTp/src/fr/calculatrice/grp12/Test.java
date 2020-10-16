package fr.calculatrice.grp12;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		Pile p = new Pile();
		
		for (double i=0;i<10;i++)
			p.push(i);
		
		System.out.println("Pile initiale 	:	" + p.toString());
		
		p.swap();
		System.out.println("Pile swap 	:	" + p.toString());
		
		p.drop();
		System.out.println("Pile drop 	:	" + p.toString());


		
	}
	
	
}
