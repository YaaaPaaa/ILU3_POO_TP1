package jeu;

import cartes.Carte;

public class Sabot {
	private Carte[] stockCartes;
	private int nbCarte;
	
	public Sabot(Carte[] carte) {
		this.nbCarte = carte.length;
		Carte[] sCarte = new Carte[carte.length];
		this.stockCartes = sCarte;
	}

	public Carte[] getStockCarte() {
		return stockCartes;
	}

	public int getNbCarte() {
		return nbCarte;
	}
	
	public boolean estVide(Carte[] pioche) {
		return pioche == null;
	}
	
		public Carte[] ajouterCarte(Carte carteAjoute) {
			for (int i = 0; i < stockCartes.length; i++) {
				if (stockCartes[i] == null) {
					stockCartes[i] = carteAjoute;
					return stockCartes;
				}
			}
		    throw new IllegalStateException("Capacité maximale atteinte : impossible d'ajouter la carte.");
		}
}
