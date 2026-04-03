package jeu;

import cartes.Carte;

public class Joueur {
	private String nom;
	private ZoneDeJeu zoneDeJeu;
	private MainJoueur mainJoueur;
	
	public Joueur(String nom, ZoneDeJeu zoneDeJeu, MainJoueur mainJoueur) {
		this.nom = nom;
		this.zoneDeJeu = zoneDeJeu;
		this.mainJoueur = mainJoueur;
	}
	
	@Override
	public String toString() {
		return nom;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Object joueur) {
			return toString().equals(joueur.toString());
		}
		return false;
	}
	
	public ZoneDeJeu getZoneDeJeu() {
		return zoneDeJeu;
	}
	
	public void donner(Carte carte) {
		mainJoueur.prendre(carte);
	}
	
	public Carte prendreCarte(Sabot sabot) {
		Carte carte = null;
		carte = sabot.piocher();
		donner(carte);

		return carte;
	}
	
	public int donnerKmParcourus() {
		return zoneDeJeu.donnerKmParcourus();
	}
}
