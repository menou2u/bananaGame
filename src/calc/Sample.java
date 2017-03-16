package calc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Sample {

	private String header;
	private String base;
	
	public Sample()
	{
		header = null;
	}
	
	private String search(String word) throws IOException
	{
		header = word;
		try {
			base= "http://api.pearson.com/v2/dictionaries/entries?headword=";
			URL url = new URL(base+header);
			header = word;
			System.out.println("http://api.pearson.com/v2/dictionaries/entries?headword="+word);
			URLConnection uc = url.openConnection();
	        BufferedReader in = new BufferedReader(
	                               new InputStreamReader(
	                               uc.getInputStream()));
	       String inputLine;
	        while ((inputLine = in.readLine()) != null) 
	            System.out.println(inputLine);
	        in.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return word;
	}
	
	public static void main(String[] args) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Sample dic = new Sample();
		try {
			dic.header = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			dic.search(dic.header);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
