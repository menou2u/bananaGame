package model;

import java.util.Observable;

import javax.swing.JButton;

public class ValidateButton extends Observable {

	JButton button;
	
	public ValidateButton(){
		
		button = new JButton("Check");
	}
	
	public JButton getButton(){
		return button;
	}

	public void update() {
		setChanged();
		notifyObservers();
	}
	//
}
