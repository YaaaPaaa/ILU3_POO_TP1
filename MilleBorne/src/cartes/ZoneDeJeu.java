package cartes;

import java.util.Collection;
import java.util.List;

public class ZoneDeJeu {
	private List<Limite> pileLimite;
	private List<Bataille> pileAttaqueParade;
	private Collection<Borne> collecBorne;
	
	public ZoneDeJeu(List<Limite> pileLimite, List<Bataille> pileAttaqueParade, Collection<Borne> collecBorne) {
		this.pileLimite = pileLimite;
		this.pileAttaqueParade = pileAttaqueParade;
		this.collecBorne = collecBorne;
	}
	
	public List<Limite> getPileLimite() {
		return pileLimite;
	}
	
	public List<Bataille> getPileAttaque() {
		return pileAttaqueParade;
	}
	
	public Collection<Borne> getCollecBorne() {
		return collecBorne;
	}
}
