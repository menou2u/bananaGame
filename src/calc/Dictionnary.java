package calc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Dictionnary {

	private String filePath;
	private String base = "http://api.pearson.com/v2/dictionaries/entries?headword=";
	private String header;
	private File list; 
	private Path path;
	
	
	public Dictionnary()
	{
		header = "";
		filePath = "C:\\Users\\Miron\\Desktop\\EnglishWords.txt";
		path = Paths.get(filePath);
		list = new File(filePath);
	}

	public int isWordInFile() throws FileNotFoundException
	{
		int noLine = -1;
		Scanner inFile = new Scanner(filePath);
		String line = "";
		String word = "";
		Scanner inLine = new Scanner(line);
		inLine.useDelimiter(":");
		while (inFile.hasNextLine())
		{
			line = inFile.nextLine();
			noLine+=1;
			word = inLine.next();
			word.replaceAll(" ","");
			if (this.getHeader().length() == word.length() && this.getHeader().equals(word))
			{
				return noLine;
			}
		}
		return -1;
	}
	
	public BufferedReader openConnexion() throws IOException
	{
		URL url = new URL(base+this.getHeader());
		URLConnection uc = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
		return in;
	}
	
	public String getLine() throws IOException{
		String inputLine;
		String answer = "";
		BufferedReader in = openConnexion();
		while((inputLine = in.readLine())!= null)
		{
			answer += inputLine;
		}
		return answer;
	}
	
	public String extractDefinition() throws IOException
	{
		Scanner scan = new Scanner(getLine());
		scan.useDelimiter("\"");
		String def = null;
		while (def==null)
		{
			if (scan.next().contains("definition"))
			{
				scan.next();
				def = scan.next();
			}
		}
		if (def!=null)
		{
			def = def.substring(0, 1).toUpperCase()+def.substring(1, def.length())+".";
		}
		return def;
	}

	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public static void main(String[] args) throws IOException {
		Dictionnary d = new Dictionnary();
		d.setHeader("try");
		System.out.println(d.extractDefinition());
		System.out.println(d.isWordInFile());
	}
	
}

