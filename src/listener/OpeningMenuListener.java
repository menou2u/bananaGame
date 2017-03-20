package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.OpeningMenu;

public class OpeningMenuListener implements ActionListener {

	private OpeningMenu openingMenuReference;
	
	public OpeningMenuListener(OpeningMenu om) {
		openingMenuReference = om;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		openingMenuReference.update(arg0.getSource());
	}
	
}
