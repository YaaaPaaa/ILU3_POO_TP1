package jeu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cartes.Limite;
import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.FinLimite;

public class ZoneDeJeu {
	private List<Limite> pileLimite;
	private List<Bataille> pileAttaqueParade;
	private Collection<Borne> collecBorne;
	
	public ZoneDeJeu() {
		this.pileLimite = new ArrayList<>();
		this.pileAttaqueParade = new ArrayList<>();
		this.collecBorne = new ArrayList<>();
	}
	
	public int donnerLimitationVitesse() {
		int limite = 50;
		Limite finLimite = new FinLimite();
		if(pileLimite.isEmpty() || pileLimite.getLast().equals(finLimite)){
			limite = 200;
		}
		return limite;
	}
	
	public int donnerKmParcourus() {
		int nbKm = 0;
		for (Borne borne : collecBorne) {
			nbKm += borne.getKm();
		}
		return nbKm;
	}
	
	public void deposer(Carte c) {
		if(c.toString().contains("KM")) {
			collecBorne.add((Borne) c);
		} else if (c.toString().equals("Debut Limite") || c.toString().equals("Fin Limite")) {
			pileLimite.add((Limite) c);
		} else {
			pileAttaqueParade.add((Bataille) c);
		}
	}
}