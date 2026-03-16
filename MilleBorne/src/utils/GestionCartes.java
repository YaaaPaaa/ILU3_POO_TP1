package utils;

import java.util.Random;
import java.util.List;
import java.util.ListIterator;

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
		while(liste.hasPrevious()) { //On se remet au début
			liste.previous();
		}
		int compteur = 0;
		while(liste.hasNext()) { // On compte les éléments
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
	
	public static <T> void melanger() {
		
	}
	
	public static <T> void verifierMelange() {
		
	}
	
	public static <T> void rassembler() {
		
	}
	
	public static <T> void verifierRassembler() {
		
	}
}
