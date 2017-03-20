package model;

import java.io.IOException;
import java.util.Observable;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;

import listener.BackListener;

public class HighScores extends Observable{

	private JTable scores;
	private Button back;
	
	public HighScores(){
		scores = new JTable(11, 2);
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

	public JTable getScores() {
		return scores;
	}
	
	public JButton getBack(){
		return back;
	}

	public void update(Object source) {
		setChanged();
		notifyObservers(source);
	}
	
}
