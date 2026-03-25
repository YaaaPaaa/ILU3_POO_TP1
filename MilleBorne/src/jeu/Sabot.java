package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import cartes.Carte;

public class Sabot implements Iterable<Carte> {

    private Carte[] stockCartes;
    private int nbCarte;
    private int nbOperation = 0;
    
    @Override
    public Iterator<Carte> iterator() {
        return new Iterateur();
    }

    public Sabot(Carte[] cartes) {
        this.stockCartes = cartes.clone();
        this.nbCarte = cartes.length;
    }

    public Carte[] getStockCarte() {
        return stockCartes;
    }

    public int getNbCarte() {
        return nbCarte;
    }

    public boolean estVide() {
        return nbCarte == 0;
    }

    public Carte[] ajouterCarte(Carte carteAjoute) {
        for (int i = 0; i < stockCartes.length; i++) {
            if (stockCartes[i] == null) {
                stockCartes[i] = carteAjoute;
                nbCarte++;
                nbOperation++;
                return stockCartes;
            }
        }
        throw new IllegalStateException("Capacité maximale atteinte : impossible d'ajouter la carte.");
    }
    
    public Carte piocher() {
        Iterator<Carte> iter = iterator();
        if (!iter.hasNext()) {
            throw new IllegalStateException("Le sabot est vide, impossible de piocher.");
        }
        Carte carte = iter.next();
        iter.remove();
        return carte;
    }


    private class Iterateur implements Iterator<Carte> {
        private int indiceIterateur = 0;
        private int nbOperationReference = nbOperation;
        private boolean nextEffectue = false;

        @Override
        public boolean hasNext() {
            while (indiceIterateur < stockCartes.length && stockCartes[indiceIterateur] == null) {
            	indiceIterateur++;
            }
            return indiceIterateur < stockCartes.length;
        }

        @Override
        public Carte next() {
        	verificationConcurrence();
            if (hasNext()) {
            	Carte carte = stockCartes[indiceIterateur];
            	nextEffectue = true;
            	indiceIterateur++;
                return carte;
            }
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
        	verificationConcurrence();
            if (!nextEffectue) {
                throw new IllegalStateException("remove() ne peut être appelé qu'après next().");
            }

            stockCartes[indiceIterateur - 1] = null;
            nbCarte--;
            nbOperation++;
            nbOperationReference = nbOperation;
            nextEffectue = false;
        }

        private void verificationConcurrence() {
            if (nbOperation != nbOperationReference) {
                throw new ConcurrentModificationException();
            }
        } 
    }
}
