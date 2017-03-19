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

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import calc.Dictionnary;
import calc.WordChecking;
import listener.AnswerZoneListener;
import listener.ValidateListener;
import model.ValidateButton;

public class Screen extends JFrame implements Observer, ActionListener {

	private String[] words = new String[4];
	private JButton word1, word2, word3, word4;
	private JTextArea definitions;
	private JFormattedTextField answerZone;
	private ValidateButton validate;
	private WordChecking wordChecking;
	private boolean correctAnswer;
	private int numberOfLives;
	private Dictionnary dic;

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("static-access")
	public Screen(){
		numberOfLives = 3;
		wordChecking = new WordChecking();
		wordChecking.randomStart();
		dic = new Dictionnary();
	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Banana Game");
		
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
						changeWordsDisplay(wordChecking.getPreviousWord(), Color.BLUE);
						dic.setHeader(wordChecking.getPreviousWord());
						try {
							definitions.append(wordChecking.getPreviousWord()+" : "+dic.extractDefinition()+"\n");
						} catch (IOException e) {
							// TODO Auto-generated catch block
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
						definitions.append(wordChecking.getPreviousWord()+" : "+dic.extractDefinition()+"\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
		if (e.getSource() instanceof JButton){
			JButton c = (JButton) e.getSource();
			dic.setHeader(c.getText());
			try {
				definitions.append(c.getText()+" : "+dic.extractDefinition()+"\n");
			} catch (IOException er) {
				// TODO Auto-generated catch block
				er.printStackTrace();
			}
		}
	}
}
