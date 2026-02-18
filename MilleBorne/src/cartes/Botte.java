package cartes;

public class Botte extends Probleme {
	public Botte(Type type) {
		
	}
	
	@Override
	public String toString() {
		return getType().getBotte();
	}
}
