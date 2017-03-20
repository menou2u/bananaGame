package model;

import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.util.Observable;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import listener.OpeningMenuListener;

public class OpeningMenu extends Observable{

	private Button play;
	private Button rules;
	private Button highScores;
	private JLabel welcome;
	
	public OpeningMenu() {		
		play = new Button("Play");
		play.setPreferredSize(new Dimension(238, 57));
		try {
			play.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/resources/play.bmp"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		play.addActionListener(new OpeningMenuListener(this));
		play.setContentAreaFilled(false);
		play.setBorder(BorderFactory.createEmptyBorder());
		
		rules = new Button("Rules");
		try {
			rules.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/resources/rules.png"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rules.setContentAreaFilled(false);
		rules.setBorder(BorderFactory.createEmptyBorder());
		rules.addActionListener(new OpeningMenuListener(this));
		
		highScores = new Button("Highscores");
		try {
			highScores.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/resources/highscores.png"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		highScores.setContentAreaFilled(false);
		highScores.setBorder(BorderFactory.createEmptyBorder());
		highScores.addActionListener(new OpeningMenuListener(this));
		
		welcome = new JLabel();
		welcome.setFont(new Font("Serif", Font.PLAIN, 60));
		welcome.setText("Welcome to the Banana Game!");
	}

	public JLabel getWelcome() {
		return welcome;
	}

	public JButton getPlay() {
		return play;
	}

	public JButton getRules() {
		return rules;
	}

	public JButton getHighScores() {
		return highScores;
	}

	public void update(Object o) {
		setChanged();
		notifyObservers(o);
	}	
}
