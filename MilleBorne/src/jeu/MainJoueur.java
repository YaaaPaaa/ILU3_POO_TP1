package jeu;

import java.util.Iterator;
import java.util.List;

import cartes.Carte;

public class MainJoueur implements Iterable<Carte>{
	private List<Carte> mainJoueur;
	
	
	public MainJoueur(List<Carte> mainJoueur) {
		this.mainJoueur = mainJoueur;
	}
	
	public void prendre(Carte carte) {
		mainJoueur.add(carte);
	}
	
	
	public void jouer(Carte carte) {
		assert(mainJoueur.contains(carte));
		mainJoueur.remove(carte);
	}
	
	@Override
	public String toString() {
		StringBuilder chaine = new StringBuilder();
		for (Carte carte : this) {
			chaine.append(carte.toString());
		}
		System.out.println("La main du joueur est :" + chaine);
		return "";
	}

	@Override
	public Iterator<Carte> iterator() {
		return mainJoueur.iterator();
	}
}

