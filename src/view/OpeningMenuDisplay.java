package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import model.OpeningMenu;

public class OpeningMenuDisplay extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OpeningMenuDisplay(OpeningMenu m){
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(m.getPlay(),gbc);
		
		gbc.gridy += gbc.gridheight;
		add(m.getRules(),gbc);
		
		/*gbc.gridy += gbc.gridheight;
		add(m.getHighScores(),gbc);*/
	}
	
}
