package view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Button;
import model.HighScores;
import model.OpeningMenu;
import model.Play;
import model.Rules;

public class MenuScreen extends JFrame implements Observer{

	private Play p;
	private Rules r;
	private HighScores h;
	private OpeningMenu o;
	private CardLayout card;
	private JPanel play;
	private JPanel rules;
	private JPanel highscores;
	private JPanel content;
	private JPanel opening;
	private JPanel game;
	private String[] listContent = { "Play", "Rules", "HighScores", "Opening", "Game" };
	
	public MenuScreen(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Banana Game");
		
		p = new Play();
		p.addObserver(this);
		r = new Rules();
		r.addObserver(this);
		h = new HighScores();
		h.addObserver(this);
		o = new OpeningMenu();
		o.addObserver(this);
		
		card = new CardLayout();
		play = new PlayDisplay(p);
		rules = new RulesDisplay(r);
		highscores = new HighScoresDisplay(h);
		opening = new OpeningMenuDisplay(o);
		content = new JPanel();
		add(content);
		
		content.setLayout(card);
		content.add(play, listContent[0]);
		content.add(rules, listContent[1]);
		content.add(highscores, listContent[2]);
		content.add(opening, listContent[3]);
		
		card.show(content, listContent[3]);
		
		setPreferredSize(new Dimension(800,700));
		setVisible(true);
		pack();
	}

	@Override
	public void update(Observable obs, Object arg) {
		if (obs instanceof OpeningMenu){
			if (((Button) arg).getButtonName().equals("Play")){
				card.show(content,listContent[0]);
			}
			if (((Button) arg).getButtonName().equals("Rules")){
				card.show(content,listContent[1]);
			}
			if (((Button) arg).getButtonName().equals("Highscores")){
				card.show(content,listContent[2]);
			}
		}
		if (obs instanceof Play){
			if (((Button) arg).getButtonName().equals("1 vs AI")){
				game = new Screen(1);
				content.add(game, listContent[4]);
				card.show(content, listContent[4]);
			}
			if (((Button) arg).getButtonName().equals("1 vs 1")){
				game = new Screen(2);
				content.add(game, listContent[4]);
				card.show(content, listContent[4]);
			}
			if (((Button) arg).getButtonName().equals("Back")){
				card.show(content,listContent[3]);
			}
		}
		if (obs instanceof Rules){
			if (((Button) arg).getButtonName().equals("Back")){
				card.show(content,listContent[3]);
			}
		}
		if (obs instanceof HighScores){
			if (((Button) arg).getButtonName().equals("Back")){
				card.show(content,listContent[3]);
			}
		}
	}
	
}
