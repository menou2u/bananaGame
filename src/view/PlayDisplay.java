package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import model.Play;

public class PlayDisplay extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlayDisplay(Play p){
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(p.getSolo(),gbc);
		
		gbc.gridy += gbc.gridheight;
		add(p.getDuo(),gbc);
		
		gbc.gridy += gbc.gridheight;
		add(p.getBack(),gbc);
	}
	
}
