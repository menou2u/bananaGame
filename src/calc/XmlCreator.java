package calc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class XmlCreator {
	
	private static String alphabet[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"}; 
	private static Dictionnary dico = new Dictionnary();
    public static void main(final String[] args) {
        /*
	 * Etape 1 : récupération d'une instance de la classe "DocumentBuilderFactory"
	 */
	final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
	try {
	    /*
	     * Etape 2 : création d'un parseur
	     */
	    final DocumentBuilder builder = factory.newDocumentBuilder();
	    		
	    /*
	     * Etape 3 : création d'un Document
	     */
	    final Document document= builder.newDocument();
					
	    /*
	     * Etape 4 : création de l'Element racine
	     */
	    final Element racine = document.createElement("dictionnaire");
	    document.appendChild(racine);			
			
	    /*
	     * Etape 5 : création d'une personne
	     */
	    //final Comment commentaire = document.createComment("John DOE");
	    //racine.appendChild(commentaire);
			
	     
	    
	    final Element enteteA = document.createElement("Entête");
	    enteteA.setAttribute("lettre", "A");
	    racine.appendChild(enteteA);
	    
	    final Element enteteB = document.createElement("Entête");
	    enteteB.setAttribute("lettre", "B");
	    racine.appendChild(enteteB);
	    
	    final Element enteteC = document.createElement("Entête");
	    enteteC.setAttribute("lettre", "C");
	    racine.appendChild(enteteC);
	    
	    final Element enteteD = document.createElement("Entête");
	    enteteD.setAttribute("lettre", "D");
	    racine.appendChild(enteteD);
	    
	    final Element enteteE = document.createElement("Entête");
	    enteteE.setAttribute("lettre", "E");
	    racine.appendChild(enteteE);
	    
	    final Element enteteF = document.createElement("Entête");
	    enteteF.setAttribute("lettre", "F");
	    racine.appendChild(enteteF);
	    
	    final Element enteteG = document.createElement("Entête");
	    enteteG.setAttribute("lettre", "G");
	    racine.appendChild(enteteG);
	    
	    final Element enteteH = document.createElement("Entête");
	    enteteH.setAttribute("lettre", "H");
	    racine.appendChild(enteteH);
	    
	    final Element enteteI = document.createElement("Entête");
	    enteteI.setAttribute("lettre", "I");
	    racine.appendChild(enteteI);
	    
	    final Element enteteJ = document.createElement("Entête");
	    enteteJ.setAttribute("lettre", "J");
	    racine.appendChild(enteteJ);
	    
	    final Element enteteK = document.createElement("Entête");
	    enteteK.setAttribute("lettre", "K");
	    racine.appendChild(enteteK);
	    
	    final Element enteteL = document.createElement("Entête");
	    enteteL.setAttribute("lettre", "L");
	    racine.appendChild(enteteL);
	    
	    final Element enteteM = document.createElement("Entête");
	    enteteM.setAttribute("lettre", "M");
	    racine.appendChild(enteteM);
	    
	    final Element enteteN = document.createElement("Entête");
	    enteteN.setAttribute("lettre", "N");
	    racine.appendChild(enteteN);
	    
	    final Element enteteO = document.createElement("Entête");
	    enteteO.setAttribute("lettre", "O");
	    racine.appendChild(enteteO);
	    
	    final Element enteteP = document.createElement("Entête");
	    enteteP.setAttribute("lettre", "P");
	    racine.appendChild(enteteP);
	    
	    final Element enteteQ = document.createElement("Entête");
	    enteteQ.setAttribute("lettre", "Q");
	    racine.appendChild(enteteQ);
	    
	    final Element enteteR = document.createElement("Entête");
	    enteteR.setAttribute("lettre", "R");
	    racine.appendChild(enteteR);
	    
	    final Element enteteS = document.createElement("Entête");
	    enteteS.setAttribute("lettre", "S");
	    racine.appendChild(enteteS);
	    
	    final Element enteteT = document.createElement("Entête");
	    enteteT.setAttribute("lettre", "T");
	    racine.appendChild(enteteT);
	    
	    final Element enteteU = document.createElement("Entête");
	    enteteU.setAttribute("lettre", "U");
	    racine.appendChild(enteteU);
	    
	    final Element enteteV = document.createElement("Entête");
	    enteteV.setAttribute("lettre", "V");
	    racine.appendChild(enteteV);
	    
	    final Element enteteW = document.createElement("Entête");
	    enteteW.setAttribute("lettre", "W");
	    racine.appendChild(enteteW);
	    
	    final Element enteteX = document.createElement("Entête");
	    enteteX.setAttribute("lettre", "X");
	    racine.appendChild(enteteX);
	    
	    final Element enteteY = document.createElement("Entête");
	    enteteY.setAttribute("lettre", "Y");
	    racine.appendChild(enteteY);
	    
	    final Element enteteZ = document.createElement("Entête");
	    enteteZ.setAttribute("lettre", "Z");
	    racine.appendChild(enteteZ);
	    
	    Element alphabetElem[] = {enteteA,enteteB,enteteC,enteteD,enteteE,enteteF,enteteG,enteteH,enteteI,enteteJ,enteteK,enteteL,enteteM,enteteN,enteteO,enteteP,enteteQ,enteteR,enteteS,enteteT,enteteU,enteteV,enteteW,enteteX,enteteY,enteteZ};
	    
	    Element mot = document.createElement("Mot");
	    Element tempElem;
	    //Node motTemp = mracine.getFirstChild().appendChild(mot);
	    Scanner in = new Scanner(new File("EnglishWords.txt"));
	    String temp = "";
	    String def = "";
	    
	    NodeList list = racine.getChildNodes();
	    while(in.hasNextLine())
	    {
	    	temp = in.nextLine();
	    	if (temp.length()>1)
	    	{
	    		for (int index =0; index<list.getLength();index ++)
	    		{
	    			if (temp.toUpperCase().startsWith(alphabet[index]))
	    			{
	    				dico = new Dictionnary();
	    				tempElem = document.createElement(temp);
	    				dico.setHeader(temp);
	    				System.out.println(temp);
	    				try {
		    				def = dico.extractDefinition();
						} catch (DOMException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	    				tempElem.appendChild(document.createTextNode(def));
	    				alphabetElem[index].appendChild(tempElem);
	    			}
	    		}
	    	}
	    }
	    
	    /*
	     * Etape 6 : création du nom et du prénom
	     */
	    //final Element nom = document.createElement("nom");
	    //nom.appendChild(document.createTextNode("DOE"));
			
	    //final Element prenom = document.createElement("prenom");
	    //prenom.appendChild(document.createTextNode("John"));
			
	    //personne.appendChild(nom);
	    //personne.appendChild(prenom);			
							
	    /*
	     * Etape 7 : récupération des numéros de téléphone
	     */
	    //final Element telephones = document.createElement("telephones");
	    
	    //final Element fixe = document.createElement("telephone");
	    //fixe.appendChild(document.createTextNode("01 02 03 04 05"));
	    //fixe.setAttribute("type", "fixe");
			
	    //final Element portable = document.createElement("telephone");
	    //portable.appendChild(document.createTextNode("06 07 08 09 10"));
	    //portable.setAttribute("type", "portable");
			
	    //telephones.appendChild(fixe);
	    //telephones.appendChild(portable);
	    //personne.appendChild(telephones);
			
	    /*
	     * Etape 8 : affichage
	     */
	    final TransformerFactory transformerFactory = TransformerFactory.newInstance();
	    final Transformer transformer = transformerFactory.newTransformer();
	    final DOMSource source = new DOMSource(document);
	    final StreamResult sortie = new StreamResult(new File("D:\\Romain\\Bureau\\test.xml"));
	    //final StreamResult result = new StreamResult(System.out);
			
	    //prologue
	    transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
	    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	    transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");			
	    		
	    //formatage
	    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
	    //sortie
	    transformer.transform(source, sortie);	
	}
	catch (final ParserConfigurationException e) {
	    e.printStackTrace();
	}
	catch (TransformerConfigurationException e) {
	    e.printStackTrace();
	}
	catch (TransformerException e) {
	    e.printStackTrace();
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}			
    }
}
