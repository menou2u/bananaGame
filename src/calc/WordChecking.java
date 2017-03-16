package calc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

public class WordChecking {

	private String word;         //the word entered, to be checked
	private String previousWord; //the previous word
	private String filename;
	private String definition;
	private ArrayList<String> alreadyFoundWords;
	
	public WordChecking() {
		alreadyFoundWords = new ArrayList<String>();
		filename = "dic.txt";
		previousWord = "";
	}
	
	public boolean iAPlays(){
		int i = 1;
		String wholeWord = previousWord; //on ne va pas chercher au delà
		System.out.println("Le joueur a joué ce mot : "+wholeWord);
		String beginningOfTheWord = previousWord.substring(previousWord.length()-i);
		System.out.println("Je vais chercher un mot qui commence par : "+beginningOfTheWord);
		
		while (!beginningOfTheWord.equals(wholeWord)){
			FileReader flot;
			BufferedReader flotFiltre;
			String ligne = null;
			Scanner filtre = null;
		
			try {
				flot = new FileReader(filename);
				flotFiltre = new BufferedReader(flot);
				ligne = flotFiltre.readLine();
				System.out.println("ligne : "+ligne);
				while (ligne != null){
					filtre = new Scanner(ligne).useDelimiter(",");
					if (filtre.hasNext()) {
						String mot = filtre.next();
						System.out.println("mot : "+mot);
						if (mot.startsWith(beginningOfTheWord) && !alreadyFoundWords.contains(mot)) {  //on a trouvé une correspondance
							System.out.println("OIN");
							previousWord = mot;
							alreadyFoundWords.add(previousWord);
							definition = filtre.next();
							System.out.println("def : "+definition);
							return true;
						}
					}
					ligne = flotFiltre.readLine();
					System.out.println("ligne : "+ligne);
				}
				flotFiltre.close();
						
			} catch (IOException e) {System.out.println(e.getMessage());}
			i++;
			System.out.println("i : "+i);
			beginningOfTheWord = previousWord.substring(previousWord.length()-i);
			System.out.println("next tested word : "+beginningOfTheWord);
		}
		return false;
	}
	
	public boolean isWordActual(String w){
		if (!(w.equals(null) || w.equals("") || (w.length() == 0) || (w.matches(".*\\d+.*")))) {
			word = w;
			return true;
		}
		word = null;
		return false;
		
	}
	
	public String joiningWords(String oldText){
		int limit = Math.min(oldText.length(),previousWord.length());
		String substringOld = "";
		String substringPreviousWord = "";
		System.out.println("oT : "+oldText);
		System.out.println("pW : "+previousWord);
		for (int i=0;i<limit-1;i++){
			substringOld = oldText.substring(oldText.length() - 1 - i, oldText.length());
			System.out.println("sso : "+substringOld);
			substringPreviousWord = previousWord.substring(0, i+1);
			System.out.println("sspw : "+substringPreviousWord);
			if (substringOld.equals(substringPreviousWord)){
				System.out.println("res : "+previousWord.substring(i+1));
				return previousWord.substring(i+1);
			}
		}
		return "";
	}

	@SuppressWarnings("resource")
	public boolean existsInDictionnary() {
		int i = 1;
		String wholeWord = previousWord + word;
		System.out.println("whole : " + wholeWord);
		String wordTested = "";
		if (previousWord.length() > 0) { //pour le début du jeu (car on n'aura pas de previousWord)
			wordTested = previousWord.substring(previousWord.length()-i) + word;
		}
		else { //pour tout le reste du jeu
			wordTested = word;
		}
		System.out.println("tested : " + wordTested);
		while (!wordTested.equals(wholeWord) || previousWord.equals("")){
			FileReader flot;
			BufferedReader flotFiltre;
			String ligne = null;
			Scanner filtre = null;
		
			try {
				flot = new FileReader(filename);
				flotFiltre = new BufferedReader(flot);
				ligne = flotFiltre.readLine();
				System.out.println("ligne : "+ligne);
				while (ligne != null){
					filtre = new Scanner(ligne).useDelimiter(",");
					if (filtre.hasNext()) {
						String mot = filtre.next();
						System.out.println("mot : "+mot);
						if (mot.equals(wordTested)) {  //on a trouvé une correspondance
							System.out.println("OIN");
							previousWord = wordTested;
							alreadyFoundWords.add(previousWord);
							definition = filtre.next();
							System.out.println("def : "+definition);
							System.out.println("words : "+alreadyFoundWords);
							return true;
						}
					}
					ligne = flotFiltre.readLine();
					System.out.println("ligne : "+ligne);
				}
				flotFiltre.close();
						
			} catch (IOException e) {System.out.println(e.getMessage());}
			if (previousWord.equals("")){
				return false;
			}
			i++;
			System.out.println("i : "+i);
			wordTested = previousWord.substring(previousWord.length()-i) + word;
			System.out.println("next tested word : "+wordTested);
		}
		return false;
	}
	
	public void wordFound(){
		alreadyFoundWords.add(word);
	}
	
	//GETTERS AND SETTERS
	public String getWord(){
		return word;
	}
	
	public void setPreviousWord(String pw){
		previousWord = pw;
	}

	public String getPreviousWord() {
		return previousWord;
	}

	public String getDefinition() {
		return definition;
	}
}
