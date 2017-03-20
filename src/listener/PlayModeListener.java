package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Play;

public class PlayModeListener implements ActionListener {

	private Play playReference;
	
	public PlayModeListener(Play pm) {
		playReference = pm;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		playReference.update(e.getSource());
	}

}
