package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import calc.Dictionnary;
import calc.WordChecking;
import listener.AnswerZoneListener;
import listener.BackListener;
import listener.ValidateListener;
import model.Button;
import model.Play;
import model.ValidateButton;

public class Screen extends JPanel implements Observer, ActionListener {

	private String[] words = new String[4];
	private JButton word1, word2, word3, word4;
	private JTextArea definitions;
	private JFormattedTextField answerZone;
	private ValidateButton validate;
	private WordChecking wordChecking;
	private boolean correctAnswer;
	private int[] numberOfLives;
	private Dictionnary dic;
	private int numberOfPlayers;
	private int turn;
	private Button reset;

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("static-access")
	public Screen(int nbPlayers) { 
		
		numberOfPlayers = nbPlayers;
		if (numberOfPlayers == 2){
			numberOfLives = new int[numberOfPlayers];
			numberOfLives[1] = 3;
			Random r = new Random();
			int q = r.nextInt(1);
			turn = 1 + q;
		}
		else {
			numberOfLives = new int[numberOfPlayers];
		}
		numberOfLives[0] = 3;
		wordChecking = new WordChecking();
		wordChecking.randomStart();
		dic = new Dictionnary();
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		word1 = new JButton();
		word1.setFont(new Font("Serif", Font.PLAIN, 40));
		word1.addActionListener(this);
		word1.setOpaque(false);
		word1.setContentAreaFilled(false);
		word1.setBorderPainted(false);
		word1.setForeground(Color.BLUE);
		
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0, 1, 25, 1);
		add(word1,gbc);
		
		word2 = new JButton();
		word2.setFont(new Font("Serif", Font.PLAIN, 40));
		word2.addActionListener(this);
		word2.setOpaque(false);
		word2.setContentAreaFilled(false);
		word2.setBorderPainted(false);
		word2.setForeground(Color.RED);
		
		gbc.gridx += gbc.gridwidth;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0, 1, 25, 1);
		add(word2,gbc);
		
		word3 = new JButton();
		word3.setFont(new Font("Serif", Font.PLAIN, 40));
		word3.addActionListener(this);
		word3.setOpaque(false);
		word3.setContentAreaFilled(false);
		word3.setBorderPainted(false);
		word3.setForeground(Color.BLUE);
		
		gbc.gridx += gbc.gridwidth;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0, 1, 25, 1);
		add(word3,gbc);
		
		word4 = new JButton();
		word4.addActionListener(this);
		word4.setOpaque(false);
		word4.setContentAreaFilled(false);
		word4.setBorderPainted(false);
		word4.setText(wordChecking.getPreviousWord());
		word4.setFont(new Font("Serif", Font.PLAIN, 40));
		word4.setForeground(Color.RED);
		words[3] = word4.getText();
		
		gbc.gridx += gbc.gridwidth;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0, 1, 25, 1);
		add(word4,gbc);
		
		definitions = new JTextArea(15,25);
		definitions.setEditable(false);
		if (numberOfPlayers == 2){
			definitions.append("Player "+turn+", you start!\n");
		}
		else {
			definitions.append("The computer played "+wordChecking.getPreviousWord()+", it's your turn now!\n");
		}
		gbc.insets = new Insets(0, 0, 0, 5);
		gbc.gridy += gbc.gridheight;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 4;
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
		
		reset = new Button("Reset");
		reset.setText("Reset");
		reset.addActionListener(this);
		gbc.gridx += gbc.gridwidth;
		add(reset,gbc);
	
		setPreferredSize(new Dimension(800,700));
		setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof ValidateButton && numberOfPlayers == 1){
			String text = answerZone.getText();
			if (wordChecking.isWordActual(text)){
				if (!wordChecking.isAlreadyFound()){
					if (wordChecking.existsInDictionnary()){
						changeWordsDisplay(wordChecking.getPreviousWord(), Color.BLUE);
						dic.setHeader(wordChecking.getPreviousWord());
						try {
							definitions.append("You played "+wordChecking.getPreviousWord()+" : "+dic.extractDefinition()+"\n");
						} catch (IOException e) {
							e.printStackTrace();
						}
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
					changeWordsDisplay(wordChecking.getPreviousWord(),Color.RED);
					dic.setHeader(wordChecking.getPreviousWord());
					try {
						definitions.append("The computer played : "+wordChecking.getPreviousWord()+" : "+dic.extractDefinition()+"\n");
						definitions.append("Your turn now\n");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else {
					definitions.append("You've won against the computer, congratulations!\n");
				}
			}
			else {
				numberOfLives[0]--;
				if (numberOfLives[0] == 0){
					definitions.append("You've lost of your lives !\n");
					answerZone.setEditable(false);
				}
			}
		}
		if (o instanceof ValidateButton && numberOfPlayers == 2){
			String text = answerZone.getText();
			if (wordChecking.isWordActual(text)){
				if (!wordChecking.isAlreadyFound()){
					if (wordChecking.existsInDictionnary()){
						changeWordsDisplay(wordChecking.getPreviousWord(), turn);
						dic.setHeader(wordChecking.getPreviousWord());
						try {
							definitions.append("You played "+wordChecking.getPreviousWord()+" : "+dic.extractDefinition()+"\n");
						} catch (IOException e) {
							e.printStackTrace();
						}
						answerZone.setValue("");
						correctAnswer = true;
					}
					else {
						definitions.append("The word "+text+" is unknown!\n");
						correctAnswer = false;
						numberOfLives[turn - 1]--;
						definitions.append("Remaining lives :"+numberOfLives[turn - 1]+"\n");
						
					}
				}
				else {
					definitions.append("The word "+text+" has already been found!\n");
					correctAnswer = false;
					numberOfLives[turn - 1]--;
					definitions.append("Remaining lives :"+numberOfLives[turn - 1]+"\n");
				}
			}
			else {
				definitions.append("You proposition is not valid!\n");
				correctAnswer = false;
				numberOfLives[turn - 1]--;
				definitions.append("Remaining lives : "+numberOfLives[turn - 1]+"\n");
			}
		}
		if (correctAnswer && numberOfPlayers == 2){
			if (turn==1){
				turn = 2;
			}
			else {
				turn = 1;
			}
			definitions.append("It's your turn player "+turn+"\n");
		}
		else if (numberOfLives[turn - 1] <=0 ){
			definitions.append("Player "+turn+" you've lost of of your lives!\n");
			answerZone.setEditable(false);
		}
	}
	
	private void changeWordsDisplay(String previousWord, int turn2) {
		if (turn2 == 2){
			word2.setForeground(Color.RED);
			word4.setForeground(Color.RED);
			word1.setForeground(Color.BLUE);
			word3.setForeground(Color.BLUE);
		}
		else {
			word1.setForeground(Color.RED);
			word3.setForeground(Color.RED);
			word2.setForeground(Color.BLUE);
			word4.setForeground(Color.BLUE);
		}
		word1.setText(word2.getText());
		word2.setText(word3.getText());
		word3.setText(word4.getText());
		word4.setText(previousWord);
	}

	public void changeWordsDisplay(String s, Color c){
		if (c.equals(Color.RED)){
			word2.setForeground(c);
			word4.setForeground(c);
			word1.setForeground(Color.BLUE);
			word3.setForeground(Color.BLUE);
		}
		else {
			word1.setForeground(c);
			word3.setForeground(c);
			word2.setForeground(Color.BLUE);
			word4.setForeground(Color.BLUE);
		}
		word1.setText(word2.getText());
		word2.setText(word3.getText());
		word3.setText(word4.getText());
		word4.setText(s);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton && !((JButton) e.getSource()).getText().equals("Check") && !((JButton) e.getSource()).getText().equals("Reset") && !((JButton) e.getSource()).getText().equals("Back")){
			JButton c = (JButton) e.getSource();
			dic.setHeader(c.getText());
			try {
				definitions.append(c.getText()+" : "+dic.extractDefinition()+"\n");
			} catch (IOException er) {
				er.printStackTrace();
			}
		}
		else {
			answerZone.setEditable(true);
			answerZone.setText("");
			if (numberOfPlayers == 2){
				numberOfLives = new int[numberOfPlayers];
				numberOfLives[1] = 3;
				Random r = new Random();
				int q = r.nextInt(1);
				turn = 1 + q;
			}
			else {
				numberOfLives = new int[numberOfPlayers];
			}
			numberOfLives[0] = 3;
			wordChecking = new WordChecking();
			wordChecking.randomStart();
			word1.setText("");
			word2.setText("");
			word3.setText("");
			word4.setText(wordChecking.getPreviousWord());
			
			if (numberOfPlayers == 2){
				definitions.append("Player "+turn+", you start!\n");
			}
			else {
				definitions.append("The computer played "+wordChecking.getPreviousWord()+", it's your turn now!\n");
			}
		}
	}
}
