package model;

import java.awt.Dimension;
import java.io.IOException;
import java.util.Observable;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;

import listener.BackListener;

public class Rules extends Observable {

	private JTextArea rules;
	private Button back;
	
	public Rules(){
		rules = new JTextArea("Hello, welcome to the BananaGame !\nHere are the few rules you need to know :\nA word will be given randomly at start and will become the main word.\nYou need to write a word that starts with a part of the main word. A part, not the whole word.\nIf your word actually exists and has never been used in this game, it will be accepted and the other player(s)\n will have to do so with your word becoming the main word, and so on.\nThe latest words are visible and you can click on them to get their definition.");
		rules.setEditable(false);
		rules.setPreferredSize(new Dimension(590,150));
		
		back = new Button("Back");
		back.addActionListener(new BackListener(this));
		try {
			back.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/resources/back.png"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		back.setContentAreaFilled(false);
		back.setBorder(BorderFactory.createEmptyBorder());
	}

	public JTextArea getRules() {
		return rules;
	}
	
	public JButton getBack() {
		return back;
	}

	public void update(Object source) {
		setChanged();
		notifyObservers(source);
	}
	
}
