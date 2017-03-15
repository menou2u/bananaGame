package calc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
	
	public void wordFound(){
		alreadyFoundWords.add(word);
	}
	
	public boolean isAlreadyFound(){
		return alreadyFoundWords.contains(word);
	}

	public boolean existsInDictionnary() {
		int i = 1;
		String wholeWord = previousWord + word;
		System.out.println("whole : " + wholeWord);
		String wordTested = "";
		if (previousWord.length() > 0){
			wordTested = previousWord.substring(previousWord.length()-i) + word;
		}
		else {
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
	
	public boolean isWordActual(String w){
		if (!(w.equals(null) || w.equals("") || (w.length() == 0) || (w.matches(".*\\d+.*")))) {
			word = w;
			return true;
		}
		word = null;
		return false;
		
	}
	
	public String getWord(){
		return word;
	}
	
	public void setPreviousWord(String pw){
		previousWord = pw;
	}
	//

	public String getPreviousWord() {
		return previousWord;
	}

	public String getDefinition() {
		return definition;
	}
}
