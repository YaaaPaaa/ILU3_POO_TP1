package jeu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cartes.Limite;
import cartes.Parade;
import cartes.Type;
import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;

public class ZoneDeJeu {
	private List<Limite> pileLimite;
	private List<Bataille> pileBataille;
	private Collection<Borne> collecBorne;
	
	public ZoneDeJeu() {
		this.pileLimite = new ArrayList<>();
		this.pileBataille = new ArrayList<>();
		this.collecBorne = new ArrayList<>();
	}
	
	public int donnerLimitationVitesse() {
		int limite = 50;
		if(pileLimite.isEmpty() || pileLimite.getLast() instanceof FinLimite){
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
		if(c instanceof Borne borne) collecBorne.add(borne);
		if (c instanceof Limite limite) pileLimite.add(limite);
		if (c instanceof Bataille bataille) pileBataille.add(bataille);
	}
	
	public Boolean peutAvancer() {
		Bataille feuVert = new Parade(Type.FEU);
		return !pileBataille.isEmpty() && pileBataille.getLast().equals(feuVert);
	}
	
	private Boolean estDepotFeuVertAutorise() {
		Bataille feuRouge = new Attaque(Type.FEU);
		Bataille feuVert = new Parade(Type.FEU);
		
		//On test séparement si la pile est vide pour pouvoir récupérer son sommet si elle ne l'est pas.
		if (pileBataille.isEmpty()) {
	        return true;
	    }
		
		Bataille sommetPile = pileBataille.getLast();
		return sommetPile.equals(feuRouge) || (sommetPile instanceof Parade && !sommetPile.equals(feuVert));
	}
	
	private Boolean estDepotBorneAutorise(Borne borne) {
		if (!pileBataille.isEmpty()) {
			return !(pileBataille.getLast() instanceof Attaque); //L'adversaire est bloqué
	    }
		
		return borne.getKm() < donnerLimitationVitesse() && (borne.getKm() + donnerKmParcourus() < 1000);
	}
	
	private Boolean estDepotLimiteAutorise(Limite limite) {
		if(limite instanceof DebutLimite) return pileLimite.isEmpty() 
				|| pileLimite.getLast() instanceof FinLimite;
		return pileLimite.getLast() instanceof DebutLimite;
	}
	
	private Boolean estDepotBatailleAutorise(Bataille bataille) {
		if(bataille instanceof Attaque) {
			if (!pileBataille.isEmpty()) {
				return !(pileBataille.getLast() instanceof Attaque); //L'adversaire est bloqué
		    }
		} else {
			Bataille feuVert = new Parade(Type.FEU);
			if (bataille.equals(feuVert)){
				return estDepotFeuVertAutorise();
			}
			return !pileBataille.isEmpty() && pileBataille.getLast().getType().equals(bataille.getType());
		}
		return false;
	}
	
	public Boolean estDepotAutorise(Carte carte) {
	    if (carte instanceof Bataille bataille) return estDepotBatailleAutorise(bataille);
	    if (carte instanceof Borne borne) return estDepotBorneAutorise(borne);
	    if (carte instanceof Limite limite) return estDepotLimiteAutorise(limite);
	    return false;
	}	
}