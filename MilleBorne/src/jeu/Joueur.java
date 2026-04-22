package jeu;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import cartes.Bataille;
import cartes.Botte;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Limite;

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
	
	@Override
	public int hashCode() {
		return toString().hashCode();
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
	
	public void deposer(Carte c) {
		zoneDeJeu.deposer(c);
	}
	
	public Set<Coup> coupsPossible(Set<Joueur> participants){
		Set<Coup> ensembleCoupsValide = new HashSet<>();
		
		for (Joueur cible : participants) {
			for (Carte carte : mainJoueur) {
				Coup coup = new Coup(this, carte, cible);
	            if (coup.estValide()) ensembleCoupsValide.add(coup);
			}
		}
		return ensembleCoupsValide;
	}
	
	public Set<Coup> coupsDefausse() {
	    Set<Coup> defausses = new HashSet<>();

	    for (Carte carte : mainJoueur) {
	        Coup coup = new Coup(this, carte, null);
	        defausses.add(coup);
	    }

	    return defausses;
	}
	
	public void retirerDeLaMain(Carte carte){
		mainJoueur.jouer(carte);
	}

	public Coup choisirCoup(Set<Joueur> participants) {
		Set<Coup> coupPossible = coupsPossible(participants);
		if(coupPossible.isEmpty()) {
			coupPossible = coupsDefausse();
		}
		
		int result = (int) (Math.random() * (participants.size() - 0) + 0);
		
		Coup coup = null;
		for (Iterator<Coup> it = coupPossible.iterator(); it.hasNext() && result != 0;) {
			coup = it.next();
			result--;
		}

		return coup;
	}
	
	public String afficherEtatJoueur() {
		StringBuilder texte = new StringBuilder();
		 HashSet<Botte> ensBotte = zoneDeJeu.getBottes();
		 
		 texte.append("L'ensemble des bottes : ");
		 for (Botte botte : ensBotte) {
			 texte.append("	-");
			 texte.append(botte.toString());
			 texte.append("\n");
		 }
		 texte.append("\n\n");
		 
		 List<Limite> pileLimite = zoneDeJeu.getLimite();
		 texte.append(pileLimite.getLast() instanceof DebutLimite);
		 texte.append("\n\n");
		 
		 texte.append(mainJoueur.toString());
		 
		return texte.toString();
	}
}
