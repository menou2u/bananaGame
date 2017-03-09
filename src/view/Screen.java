package view;

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

	private JLabel words;
	private JTextArea definitions;
	private JFormattedTextField answerZone;
	private ValidateButton validate;
	private WordChecking wordChecking;
	private String previousWord = "apple";

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("static-access")
	public Screen(){
	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Banana Game");
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		words = new JLabel();
		words.setText(previousWord);
		words.setFont(new Font("Serif", Font.PLAIN, 40));
		
		gbc.gridx += gbc.gridwidth;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 1, 25, 1);
		add(words,gbc);
		
		definitions = new JTextArea(15,25);
		definitions.setEditable(false);
		gbc.insets = new Insets(0, 0, 0, 5);
		gbc.gridy += gbc.gridheight;
		gbc.gridx = 0;
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
	
		wordChecking = new WordChecking();
		setPreferredSize(new Dimension(700,700));
		setVisible(true);
		pack();
		//
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof ValidateButton){
			String text = answerZone.getText();
			if (wordChecking.isWordActual(text)){
				if (!wordChecking.isAlreadyFound()){
					if (wordChecking.existsInDictionnary()){
						previousWord = words.getText();
						wordChecking.setPreviousWord(previousWord);
						words.setText(text);
						answerZone.setValue("");
					}
					else {
						definitions.append("Unknown word !\n");
					}
				}
				else {
					definitions.append("You already found it !\n");
				}
			}
			else {
				definitions.append("You proposition is not valid\n");
			}
		}
	}
}
