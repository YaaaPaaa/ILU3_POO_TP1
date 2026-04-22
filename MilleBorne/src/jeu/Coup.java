package jeu;

import cartes.Attaque;
import cartes.Borne;
import cartes.Botte;
import cartes.Carte;
import cartes.Limite;
import cartes.Parade;

public class Coup {
	private Joueur joueurCourant;
	private Carte carteJouee;
	private Joueur joueurCible;
	
	public Coup(Joueur courant, Carte jouee, Joueur cible) {
		this.joueurCourant = courant;
		this.carteJouee = jouee;
		this.joueurCible = cible;
	}

	public Joueur getJoueurCourant() {
		return joueurCourant;
	}

	public Carte getCarteJouee() {
		return carteJouee;
	}
	
	public Joueur getJoueurCible() {
		return joueurCible;
	}
	
	public boolean estValide() {
	    if (joueurCible == null) 
	    	return true;
	    if (carteJouee instanceof Attaque || carteJouee instanceof Limite) 
	    	return !joueurCible.equals(joueurCourant);
	    if (carteJouee instanceof Parade || carteJouee instanceof Botte || carteJouee instanceof Borne)
	        return joueurCible == joueurCourant;
	    return false;
	}

	@Override
	public String toString() {
	    if (joueurCible != null) {
	        return "depose la carte " + carteJouee + " dans la zone de jeu de " + joueurCible;
	    } else {
	        return "defausse la carte " + carteJouee;
	    }
	}
}
