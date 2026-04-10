package jeu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import cartes.Limite;
import cartes.Parade;
import cartes.Type;
import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Botte;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;

public class ZoneDeJeu {
	private List<Limite> pileLimite;
	private List<Bataille> pileBataille;
	private Collection<Borne> collecBorne;
	private HashSet<Botte> bottes;
	
	public ZoneDeJeu() {
		this.pileLimite = new ArrayList<>();
		this.pileBataille = new ArrayList<>();
		this.collecBorne = new ArrayList<>();
		this.bottes = new HashSet<>();
	}
	
	public int donnerLimitationVitesse() {
		int limite = 50;
		if(pileLimite.isEmpty() || pileLimite.getLast() instanceof FinLimite || estPrioritaire()){
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
		if (c instanceof Botte botte) bottes.add(botte);
	}
	
	/**
	 * Test si on a la Botte qui contre l'Attaque au sommet de la pileBataille (sauf pour la botte "Prioritaire)
	 */
	public boolean estBotteContreAttaque() {
		Botte citerne = new Botte(Type.ESSENCE);
		Botte increvable = new Botte(Type.CREVAISON);
		Botte asDuVolant = new Botte(Type.ACCIDENT);
		
		return 	!pileBataille.isEmpty() && pileBataille.getLast() instanceof Attaque && 
				(
				(bottes.contains(citerne) && pileBataille.getLast().getType().equals(Type.ESSENCE))||
				(bottes.contains(increvable) && pileBataille.getLast().getType().equals(Type.CREVAISON))||
				(bottes.contains(asDuVolant) && pileBataille.getLast().getType().equals(Type.ACCIDENT))
				);
	}
	
	public Boolean peutAvancer() {
		if (pileBataille.isEmpty()) {
	        return estPrioritaire();
	    }
		
		return	(pileBataille.getLast().equals(new Parade(Type.FEU)) ||
				(pileBataille.getLast() instanceof Parade && estPrioritaire()) ||
				(pileBataille.getLast() instanceof Attaque 
						&& pileBataille.getLast().getType().equals(Type.FEU) && estPrioritaire()) ||
				(estBotteContreAttaque() && estPrioritaire()));
	}
	
	private Boolean estDepotFeuVertAutorise() {
		Botte prio = new Botte(Type.FEU);
		
		if (bottes.contains(prio)) {
			return false;
		}
		
		return pileBataille.isEmpty() || pileBataille.getLast().equals(new Attaque(Type.FEU)) || 
				(pileBataille.getLast() instanceof Parade && !pileBataille.getLast().equals(new Parade(Type.FEU))) ||
				estBotteContreAttaque();
	}
	
	private Boolean estDepotBorneAutorise(Borne borne) {
		if (!pileBataille.isEmpty()) {
			return !(pileBataille.getLast() instanceof Attaque); //L'adversaire est bloqué
	    }
		
		return borne.getKm() < donnerLimitationVitesse() && (borne.getKm() + donnerKmParcourus() < 1000);
	}
	
	private Boolean estDepotLimiteAutorise(Limite limite) {
		if(estPrioritaire()) return false;
		
		if(limite instanceof DebutLimite) 
			return pileLimite.isEmpty() || pileLimite.getLast() instanceof FinLimite;
		
		return pileLimite.getLast() instanceof DebutLimite;
	}
	
	private Boolean estDepotBatailleAutorise(Bataille bataille) {
		for (Botte botte : bottes) {
			if(botte.getType().equals(bataille.getType())) return false;
		}
		
		if(bataille instanceof Attaque) {
			return peutAvancer();
		} else {
			Bataille feuVert = new Parade(Type.FEU);
			if (bataille.equals(feuVert)){
				return estDepotFeuVertAutorise();
			}
			return !pileBataille.isEmpty() && pileBataille.getLast().getType().equals(bataille.getType());
		}
	}
	
	public Boolean estDepotAutorise(Carte carte) {
	    if (carte instanceof Bataille bataille) return estDepotBatailleAutorise(bataille);
	    if (carte instanceof Borne borne) return estDepotBorneAutorise(borne);
	    if (carte instanceof Limite limite) return estDepotLimiteAutorise(limite);
	    return carte instanceof Botte;
	}
	
	public boolean estPrioritaire() {
		return bottes.contains(new Botte(Type.FEU));
	}
	
	
	
}