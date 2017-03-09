package listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.ValidateButton;

public class AnswerZoneListener implements KeyListener {

	ValidateButton referenceButton;
	
	public AnswerZoneListener(ValidateButton vb){
		referenceButton = vb;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_ENTER){
			referenceButton.update();
        }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	//
}
