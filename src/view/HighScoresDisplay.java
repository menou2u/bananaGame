package view;

import javax.swing.JPanel;

import model.HighScores;

public class HighScoresDisplay extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HighScoresDisplay(HighScores h){
		add(h.getScores());
		add(h.getBack());
	}
	
}
