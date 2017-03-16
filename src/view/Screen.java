package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import calc.WordChecking;
import listener.AnswerZoneListener;
import listener.ValidateListener;
import model.ValidateButton;

public class Screen extends JFrame implements Observer{

	private String[] words = new String[4];
	private JLabel word1, word2, word3, word4;
	private JTextArea definitions;
	private JFormattedTextField answerZone;
	private ValidateButton validate;
	private WordChecking wordChecking;
	private boolean correctAnswer;
	private int numberOfLives;

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("static-access")
	public Screen(){
		numberOfLives = 3;
		wordChecking = new WordChecking();
		wordChecking.randomStart();
	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Banana Game");
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		word1 = new JLabel();
		word1.setFont(new Font("Serif", Font.PLAIN, 40));
		
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0, 1, 25, 1);
		add(word1,gbc);
		
		word2 = new JLabel();
		word2.setFont(new Font("Serif", Font.PLAIN, 40));
		
		gbc.gridx += gbc.gridwidth;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0, 1, 25, 1);
		add(word2,gbc);
		
		word3 = new JLabel();
		word3.setFont(new Font("Serif", Font.PLAIN, 40));
		
		gbc.gridx += gbc.gridwidth;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0, 1, 25, 1);
		add(word3,gbc);
		
		word4 = new JLabel();
		word4.setText(wordChecking.getPreviousWord());
		word4.setFont(new Font("Serif", Font.PLAIN, 40));
		words[3] = word4.getText();
		
		gbc.gridx += gbc.gridwidth;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0, 1, 25, 1);
		add(word4,gbc);
		
		definitions = new JTextArea(15,25);
		definitions.setEditable(false);
		gbc.insets = new Insets(0, 0, 0, 5);
		gbc.gridy += gbc.gridheight;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 3;
		add(definitions,gbc);		
		
		answerZone = new JFormattedTextField();
		answerZone.setColumns(10);
		answerZone.setMaximumSize(new Dimension(70, 5));
		validate = new ValidateButton();
		answerZone.addKeyListener(new AnswerZoneListener(validate));
		gbc.fill = gbc.HORIZONTAL;
		gbc.insets = new Insets(0, 5, 0, 5);
		gbc.gridx += gbc.gridwidth;
		gbc.gridwidth = 1;
		add(answerZone,gbc);
		
		validate.addObserver(this);
		validate.getButton().addActionListener(new ValidateListener(validate));
		gbc.gridx += gbc.gridwidth;
		add(validate.getButton(),gbc);
	
		setPreferredSize(new Dimension(800,700));
		setVisible(true);
		pack();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof ValidateButton){
			String text = answerZone.getText();
			if (wordChecking.isWordActual(text)){
				if (!wordChecking.isAlreadyFound()){
					if (wordChecking.existsInDictionnary()){
						changeWordsDisplay(wordChecking.getPreviousWord());
						definitions.append(wordChecking.getDefinition()+"\n");
						answerZone.setValue("");
						correctAnswer = true;
					}
					else {
						definitions.append("The word "+text+" is unknown!\n");
						correctAnswer = false;
					}
				}
				else {
					definitions.append("The word "+text+" has already been found!\n");
					correctAnswer = false;
				}
			}
			else {
				definitions.append("You proposition is not valid!\n");
				correctAnswer = false;
			}
			if (correctAnswer) {
				if (wordChecking.iAPlays()){ //si l'IA trouve quelque chose
					changeWordsDisplay(wordChecking.getPreviousWord());
					definitions.append(wordChecking.getDefinition()+"\n");
				}
				else {
					definitions.append("You've won against the computer, congratulations!\n");
				}
			}
			else {
				numberOfLives--;
				if (numberOfLives == 0){
					definitions.append("You've lost of your lives !\n");
				}
			}
		}
	}
	
	public void changeWordsDisplay(String s){
		word1.setText(word2.getText());
		word2.setText(word3.getText());
		word3.setText(word4.getText());
		word4.setText(s);
	}
}
