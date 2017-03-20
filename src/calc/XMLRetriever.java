package calc;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLRetriever {

	public static void main(String[] args) {
		retrieve("aba");
	}

	public static String retrieve(String temp) {

		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		String definition = "No definition available for the word "+temp;
		try {
			/*
			 * Etape 2 : cr�ation d'un parseur
			 */
			final DocumentBuilder builder = factory.newDocumentBuilder();

			/*
			 * Etape 3 : cr�ation d'un Document
			 */
			final Document document = builder.parse(new File("test.xml"));

			// Affiche du prologue
			/*
			 * System.out.println("*************PROLOGUE************");
			 * System.out.println("version : " + document.getXmlVersion());
			 * System.out.println("encodage : " + document.getXmlEncoding());
			 * System.out.println("standalone : " +
			 * document.getXmlStandalone());
			 */

			/*
			 * Etape 4 : r�cup�ration de l'Element racine
			 */
			final Element racine = document.getDocumentElement();

			// Affichage de l'�l�ment racine
			/*
			 * System.out.println("\n*************RACINE************");
			 * System.out.println(racine.getNodeName());
			 */

			/*
			 * Etape 5 : r�cup�ration des mots
			 */
			final NodeList racineNoeuds = racine.getChildNodes();

			// TODO : Changer ce nombre
			final int nbRacineNoeuds = racineNoeuds.getLength();

			// TODO : du coup changer la boucle
			for (int i = 0; i < nbRacineNoeuds; i++) {
				if (racineNoeuds.item(i).getNodeName().contains(temp)) {
					definition = racineNoeuds.item(i).getTextContent();

					// Affichage d'une personne
					/*
					 * System.out.println("\n*************PERSONNE************")
					 * ; System.out.println("sexe : " +
					 * personne.getAttribute("sexe"));
					 */

					/*
					 * Etape 6 : r�cup�ration du nom et du pr�nom
					 */
					//final Element def = (Element) personne.getElementsByTagName(temp).item(0);

					// Affichage du nom et du pr�nom
					System.out.println(temp + " : " + definition);
					return definition;


					/*
					 * Etape 7 : r�cup�ration des num�ros de t�l�phone
					 */
					/*
					 * final NodeList telephones =
					 * personne.getElementsByTagName("telephone"); final int
					 * nbTelephonesElements = telephones.getLength();
					 */

					/*
					 * for (int j = 0; j < nbTelephonesElements; j++) { final
					 * Element telephone = (Element) telephones.item(j);
					 * 
					 * // Affichage du t�l�phone
					 * System.out.println(telephone.getAttribute("type") + " : "
					 * + telephone.getTextContent()); }
					 */
				}
			}
		} catch (final ParserConfigurationException e) {
			e.printStackTrace();
		} catch (final SAXException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return definition;
	}
	

	
}
