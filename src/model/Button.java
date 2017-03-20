package model;

import javax.swing.JButton;

public class Button extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String buttonName;
	
	public Button(String name){
		buttonName = name;
	}

	public String getButtonName() {
		return buttonName;
	}
	
}
