package utils;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Collections;

public class GestionCartes {
	//Travail direct sur la liste
	public static <T> T extraire1(List<T> liste) {
		if(liste.isEmpty()) {
			throw new NullPointerException("La liste est vide.");
		}
		
		T element;
		Random random = new Random();
        int randomElement = random.nextInt(liste.size());
        element = liste.get(randomElement);
        liste.remove(randomElement);
		return element;
	}
	
	//Travail en exploitant un ListIterator
	public static <T> T extraire2(ListIterator<T> liste) {
		if(!(liste.hasNext() || liste.hasPrevious())) {
			throw new NullPointerException("La liste est vide.");
		}
		
		//On se remet au début
		while(liste.hasPrevious()) {
			liste.previous();
		}
		
		// On compte les éléments
		int compteur = 0;
		while(liste.hasNext()) {
			liste.next();
			compteur++;
		}
		
		T element;
		Random random = new Random();
        int randomInt = random.nextInt(compteur);
        int randomElement = compteur - randomInt;
        
        while(randomElement > 0) {
        	liste.previous();
        	randomElement--;
        }
        
        element = liste.next();
        liste.remove();
		return element;
	}
	
	public static <T> List<T> melanger(List<T> liste) {
		List<T> listeObtenue = new ArrayList<>();
		while (!liste.isEmpty()) {
			T element = extraire1(liste);
			listeObtenue.add(element);
		}
		return listeObtenue;
	}
	
	public static <T> boolean verifierMelange(List<T> l1, List<T> l2) {
	    if (l1.size() != l2.size()) {
	        return false;
	    }

	    for (Object carte : l1) {
	        int freq1 = Collections.frequency(l1, carte);
	        int freq2 = Collections.frequency(l2, carte);

	        if (freq1 != freq2) {
	            return false;
	        }
	    }

	    return true;
	}

	
	public static <T> List<T> rassembler(List<T> liste) {
	    List<T> resultat = new ArrayList<>();
	    List<T> dejaTraites = new ArrayList<>();

	    for (T elem : liste) {
	        if (!dejaTraites.contains(elem)) {
	            int freq = Collections.frequency(liste, elem);

	            for (int i = 0; i < freq; i++) {
	                resultat.add(elem);
	            }

	            dejaTraites.add(elem);
	        }
	    }

	    return resultat;
	}

	
	public static <T> boolean verifierRassemblement(List<T> liste) {
	    if (liste.isEmpty()) {
	        return true;
	    }

	    ListIterator<T> it1 = liste.listIterator();
	    T precedent = it1.next();

	    while (it1.hasNext()) {
	        T courant = it1.next();

	        if (!courant.equals(precedent)) {

	            ListIterator<T> it2 = liste.listIterator(it1.nextIndex());

	            while (it2.hasNext()) {
	                T suivant = it2.next();

	                if (suivant.equals(precedent)) {
	                    return false;
	                }
	            }

	            precedent = courant;
	        }
	    }

	    return true;
	}

}











