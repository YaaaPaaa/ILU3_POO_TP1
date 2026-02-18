package cartes;

public class JeuDeCartes {
	private Configuration[] typesDeCartesConfigurations;
	
	public String affichageJeuDeCartes() {
		
		return "";
	}
	
	public Configuration[] getTypesDeCartesConfigurations() {
		return typesDeCartesConfigurations;
	}

	public void setTypesDeCartesConfigurations(Configuration[] typesDeCartesConfigurations) {
		this.typesDeCartesConfigurations = typesDeCartesConfigurations;
	}

	private class Configuration extends Carte{
		private int nbExemplaires;
		private Carte carte;
		
		private Configuration(Carte carte, int nbExemplaires) {
			this.carte = carte;
			this.nbExemplaires = nbExemplaires;
		}
		
		public Carte getCarte() {
			return carte;
		}
		
		public int getNbExemplaires() {
			return nbExemplaires;
		}
		
		public Carte[] donnerCartes() {
			Carte[] jeuDeCarte = null;
			
			
			return jeuDeCarte;
		}
	}
}
