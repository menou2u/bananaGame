package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import model.Rules;

public class RulesDisplay extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RulesDisplay(Rules r){
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(r.getRules(),gbc);
		
		gbc.gridy += gbc.gridheight;
		add(r.getBack(),gbc);
		
	}
	
}
