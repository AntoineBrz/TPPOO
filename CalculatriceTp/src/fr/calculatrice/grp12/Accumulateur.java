package fr.calculatrice.grp12;

public class Accumulateur implements IAccumulateur {

	Pile pile;
	
	@Override
	public void push(Double nombre) {
		pile.push(nombre);
	}

	@Override
	public void drop() {
		pile.drop();
	}

	@Override
	public void swap() {
		pile.swap();
	}

	
	/*CALCUL*/
	@Override
	public void add(Double lOperande, Double rOperande) {
		additionner(lOperande, rOperande);
	}

	private void additionner(Double lOperande, Double rOperande) {
	}

	@Override
	public void sub(Double lOperande, Double rOperande) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mult(Double lOperande, Double rOperande) {
		// TODO Auto-generated method stub

	}

	@Override
	public void div(Double lOperande, Double rOperande) {
		// TODO Auto-generated method stub

	}

	@Override
	public void neg(Double nombre) {
		// TODO Auto-generated method stub

	}

	@Override
	public void backspace() {
		// TODO Auto-generated method stub

	}

	@Override
	public void accumuler(Character character) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}
