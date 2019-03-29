
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

//Extends is inheritance, inherits all the qualities from JFrame
public class MainMenuPanel extends JFrame{
	private JButton othello;
	private JButton battleShip;	
	private JButton checkers;
	private JButton exit;
	
	JFrame frame;;
		
	public MainMenuPanel() {
		frame = new JFrame();
		frame.setSize(400, 500);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.BLACK);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		othello = new JButton("Othello");
		add(othello);
		add(Box.createRigidArea(new Dimension (0, 10)));
		
		battleShip = new JButton("Battle Ship");
		add(battleShip);
		add(Box.createRigidArea(new Dimension (0, 10)));
		
		checkers = new JButton("Checkers");
		add(checkers);
		add(Box.createRigidArea(new Dimension (0, 10)));
		
		exit = new JButton("Exit");
		add(exit);
		
		thehandler handler  = new thehandler();
		othello.addActionListener(handler);
		battleShip.addActionListener(handler);
		checkers.addActionListener(handler);
		exit.addActionListener(handler);
		
		frame.setVisible(true);
		
	}
	
	private class thehandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == othello) {
				Board gameBoard = new Board();
				Gui ourGui = new Gui(gameBoard);
//			} else if(event.getSource() == battleShip) {
//				BattleBoard battleBoard = new BattleBoard();
//				Gui ourGui = new Gui(battleBoard);
//			} else if(event.getSource() == checkers) {
//				CheckerBoard checkerBoard = new CheckerBoard();
			} else if(event.getSource() == exit) {
				JOptionPane.showMessageDialog(null, "Thanks for playing the suite!");
				System.exit(1);
			}
			
		}
	}

}