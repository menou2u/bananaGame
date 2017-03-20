package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.HighScores;
import model.Play;
import model.Rules;
import view.MenuScreen;
import view.Screen;

public class BackListener implements ActionListener {

	private Object objectReference;

	public BackListener(Object o){
		objectReference = o;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (objectReference instanceof Play){
			((Play) objectReference).update(e.getSource());
		}
		if (objectReference instanceof Rules){
			((Rules) objectReference).update(e.getSource());
		}
		if (objectReference instanceof HighScores){
			((HighScores) objectReference).update(e.getSource());
		}
	}

}
