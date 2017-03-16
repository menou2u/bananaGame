package calc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class trythisoutmdrlol {

	public static void main(String[] args) throws IOException {
		String filePath = "C:\\Users\\Miron\\Desktop\\EnglishWords.txt";
		Scanner in = new Scanner(filePath);
		File file = new File(filePath);
		File file2 = new File("C:\\Users\\Miron\\Desktop\\EnglishWords2.txt");
		FileWriter wr = new FileWriter(file2);
		String temp = "";
		while (in.hasNextLine())
		{
			temp = in.nextLine();
			System.out.println(temp);
			wr.write(temp + ":"+"\n");
		}
	}
}
