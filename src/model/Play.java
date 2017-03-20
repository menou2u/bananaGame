package model;

import java.io.IOException;
import java.util.Observable;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import listener.BackListener;
import listener.PlayModeListener;

public class Play extends Observable{

	private Button solo;
	private Button duo;
	private Button back;
	
	public Play(){
		solo = new Button("1 vs AI");
		solo.addActionListener(new PlayModeListener(this));
		try {
			solo.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/resources/1 vs ai.png"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		solo.setContentAreaFilled(false);
		solo.setBorder(BorderFactory.createEmptyBorder());
		
		
		duo = new Button("1 vs 1");
		duo.addActionListener(new PlayModeListener(this));
		try {
			duo.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/resources/1 vs 1.png"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		duo.setContentAreaFilled(false);
		duo.setBorder(BorderFactory.createEmptyBorder());
		
		back = new Button("Back");
		back.addActionListener(new BackListener(this));
		try {
			back.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/resources/back.png"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		back.setContentAreaFilled(false);
		back.setBorder(BorderFactory.createEmptyBorder());
	}

	public JButton getSolo() {
		return solo;
	}

	public JButton getDuo() {
		return duo;
	}

	public JButton getBack() {
		return back;
	}

	public void update(Object source) {
		setChanged();
		notifyObservers(source);
	}
	
}
