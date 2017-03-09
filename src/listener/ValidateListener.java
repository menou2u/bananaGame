package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.ValidateButton;

public class ValidateListener implements ActionListener {

	private ValidateButton referenceButton;
	
	public ValidateListener(ValidateButton vb) {
		referenceButton = vb;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		referenceButton.update();
	}
	//
}
