package calc;

import java.util.ArrayList;

public class WordChecking {

	private String word;
	private String previousWord;
	private String filename;
	private ArrayList<String> alreadyFoundWords;
	
	public WordChecking() {
		alreadyFoundWords = new ArrayList<String>();
	}
	
	public void wordFound(){
		alreadyFoundWords.add(word);
	}
	
	public boolean isAlreadyFound(){
		return alreadyFoundWords.contains(word);
	}

	public boolean existsInDictionnary() {
		// TODO Auto-generated method stub
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
}
