package calc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WordChecking {

	private String word;         //the word entered, to be checked
	private String previousWord; //the previous word
	private String filename;
	private ArrayList<String> alreadyFoundWords;
	
	public WordChecking() {
		alreadyFoundWords = new ArrayList<String>();
		filename = "EnglishWords.txt";
		previousWord = "";
	}
	
	public boolean isAlreadyFound(){
		return alreadyFoundWords.contains(word);
	}
	
	public boolean iAPlays(){
		int i = 1;
		String wholeWord = previousWord; //on ne va pas chercher au delà
		//System.out.println("Le joueur a joué ce mot : "+wholeWord);
		String beginningOfTheWord = previousWord.substring(previousWord.length()-i);
		//System.out.println("Je vais chercher un mot qui commence par : "+beginningOfTheWord);
		
		while (!beginningOfTheWord.equals(wholeWord)){
			FileReader flot;
			BufferedReader flotFiltre;
			String ligne = null;
			Scanner filtre = null;
		
			try {
				flot = new FileReader(filename);
				flotFiltre = new BufferedReader(flot);
				ligne = flotFiltre.readLine();
				//System.out.println("ligne : "+ligne);
				while (ligne != null){
					filtre = new Scanner(ligne);
					if (filtre.hasNext()) {
						String mot = filtre.next();
						//System.out.println("mot : "+mot);
						if (mot.startsWith(beginningOfTheWord) && !alreadyFoundWords.contains(mot) && mot.length()>2) {  //on a trouvé une correspondance
							//System.out.println("OIN");
							previousWord = mot;
							alreadyFoundWords.add(previousWord);
							return true;
						}
					}
					ligne = flotFiltre.readLine();
					//System.out.println("ligne : "+ligne);
				}
				flotFiltre.close();
						
			} catch (IOException e) {System.out.println(e.getMessage());}
			i++;
			//System.out.println("i : "+i);
			beginningOfTheWord = previousWord.substring(previousWord.length()-i);
			//System.out.println("next tested word : "+beginningOfTheWord);
		}
		return false;
	}
	
	public boolean isWordActual(String w){
		if (!w.equals(null) && !w.equals("") && w.length()>2 && !w.matches(".*\\d+.*") && !joiningWords(w).equals("")) {
			word = w;
			return true;
		}
		word = null;
		return false;
		
	}
	
	public String joiningWords(String oldText){
			System.out.println("Début j");
			int limit = Math.min(oldText.length(),previousWord.length());
			String substringOld = "";
			String substringPreviousWord = "";
			System.out.println("oT : "+oldText);
			System.out.println("pW : "+previousWord);
			for (int i=0;i<limit;i++){
				substringPreviousWord = previousWord.substring(previousWord.length() - 1 - i, previousWord.length());
				System.out.println("sspw : "+substringPreviousWord);
				substringOld = oldText.substring(0, i+1);
				System.out.println("sso : "+substringOld);
				if (substringOld.equals(substringPreviousWord)){
					System.out.println("res : "+substringOld);
					System.out.println("Fin J");
					return substringOld;
				}
			}
		return "";
	}

	@SuppressWarnings("resource")
	public boolean existsInDictionnary() {
		int i = 1;
		String wholeWord = previousWord + joiningWords(word);
		String j = joiningWords(word);
		System.out.println("j : "+j);
		//System.out.println("whole : " + wholeWord);
		String wordTested = word;
		//System.out.println("tested : " + wordTested);
		while (!wordTested.equals(wholeWord) || previousWord.equals("")){
			FileReader flot;
			BufferedReader flotFiltre;
			String ligne = null;
			Scanner filtre = null;
		
			try {
				flot = new FileReader(filename);
				flotFiltre = new BufferedReader(flot);
				ligne = flotFiltre.readLine();
				//System.out.println("ligne : "+ligne);
				while (ligne != null){
					filtre = new Scanner(ligne);
					if (filtre.hasNext()) {
						String mot = filtre.next();
						//System.out.println("mot : "+mot);
						if (mot.equals(wordTested)) {  //on a trouvé une correspondance
							System.out.println("OIN");
							previousWord = wordTested;
							alreadyFoundWords.add(previousWord);
							//System.out.println("words : "+alreadyFoundWords);
							return true;
						}
					}
					ligne = flotFiltre.readLine();
					//System.out.println("ligne : "+ligne);
				}
				flotFiltre.close();
						
			} catch (IOException e) {System.out.println(e.getMessage());}
			if (previousWord.equals("")){
				return false;
			}
			i++;
			if (i > previousWord.length()){
				//System.out.println("i : "+i);
				wordTested = previousWord.substring(previousWord.length()-i) + word;
				//System.out.println("next tested word : "+wordTested);
			}
			else {
				return false;
			}
		}
		return false;
	}
	
	public void randomStart(){
		Random r = new Random();
		int i = 0;
		int q = r.nextInt(30);
		FileReader flot;
		BufferedReader flotFiltre;
		String ligne = null;
		Scanner filtre = null;
	
		try {
			flot = new FileReader(filename);
			flotFiltre = new BufferedReader(flot);
			while (i < q){
				ligne = flotFiltre.readLine();
				i++;
			}
			filtre = new Scanner(ligne);
			previousWord = filtre.next();
			alreadyFoundWords.add(previousWord);
			flotFiltre.close();
					
		} catch (IOException e) {System.out.println(e.getMessage());}
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
}
