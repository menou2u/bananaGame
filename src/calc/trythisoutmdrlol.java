package calc;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class trythisoutmdrlol {

	public static void main(String[] args) throws IOException {
		ArrayList<String> al = new ArrayList<String>();
		boolean blank = false;
		String filePath = "EnglishWords.txt";
		FileReader in = new FileReader(filePath);
		File file = new File(filePath);
		FileWriter wr = new FileWriter("dic.txt");
		String temp = "";
		Pattern p = Pattern.compile("[a-zA-Z]");
		String temp2 = ""+(char)in.read();
		Matcher m = p.matcher(temp2);
		while(true)
		{
			if (blank = true && m.matches())
			{
				blank = false;
				temp+=temp2;
			}
			else
			{
				blank = true;
				temp+=":";
				wr.append(temp);
				System.out.println(temp);
				wr.append(System.getProperty("line.separator"));
				temp = "";
			}
		}
	}
}
