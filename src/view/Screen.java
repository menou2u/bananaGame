package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;

public class Screen extends JFrame implements Observer{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Screen(){
		
		setPreferredSize(new Dimension(800,800));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Banana Game");
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JFormattedTextField answerZone = new JFormattedTextField();
		answerZone.setColumns(10);
		gbc.gridx += gbc.gridwidth;
		gbc.gridy += gbc.gridheight;
		add(answerZone,gbc);
		
		JButton validate = new JButton();
		validate.setText("Check");
		gbc.gridx += gbc.gridwidth;
		add(validate,gbc);		
		
		setVisible(true);
		pack();
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
