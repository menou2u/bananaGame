package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

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
		gbc.insets = new Insets(0,0,60,0);
		add(m.getWelcome(),gbc);
		
		gbc.gridy += gbc.gridheight;
		gbc.insets = new Insets(0,0,30,0);
		add(m.getPlay(),gbc);
		
		gbc.gridy += gbc.gridheight;
		gbc.insets = new Insets(0,0,30,0);
		add(m.getRules(),gbc);
	}
	
}
