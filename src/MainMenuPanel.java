
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

//Extends is inheritance, inherits all the qualities from JFrame
public class MainMenuPanel extends JPanel{
	private JButton othello;
	private JButton battleShip;	
	private JButton checkers;
	private JButton exit;
	
		
	public MainMenuPanel() {
		
		setLayout(new FlowLayout());
		setBackground(Color.BLACK);

		othello = new JButton("Othello");
		add(othello);
		
		battleShip = new JButton("Battle Ship");
		add(battleShip);
		
		checkers = new JButton("Checkers");
		add(checkers);
		
		exit = new JButton("Exit");
		add(exit);
		
		thehandler handler  = new thehandler();
		othello.addActionListener(handler);
		battleShip.addActionListener(handler);
		checkers.addActionListener(handler);
		exit.addActionListener(handler);
		
		
	}
	
	private class thehandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == othello) {
				Board gameBoard = new Board();
				Gui firstGui = new Gui(gameBoard);
			} else if(event.getSource() == battleShip) {
				BattleBoard battleBoard = new BattleBoard();
				BattleGUI secondGui = new BattleGUI(battleBoard);
//			} else if(event.getSource() == checkers) {
//				CheckerBoard checkerBoard = new CheckerBoard();
			} else if(event.getSource() == exit) {
				JOptionPane.showMessageDialog(null, "Thanks for playing the suite!");
				System.exit(1);
			}
			
		}
	}

}