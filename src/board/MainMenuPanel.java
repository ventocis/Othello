package board;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * Extends is inheritance, inherits all the qualities from JFrame.
 * @author Owner
 *
 */
public class MainMenuPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Button for othello.
	 */
	private JButton othello;
	/**
	 * Button for battleShip.
	 */
	private JButton battleShip;	
	/**
	 * Button for checkers.
	 */
	private JButton checkers;
	/**
	 * Button for exit.
	 */
	private JButton exit;
	
	/**
	 * Constructor for main menu panel.
	 */
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
		
		TheHandler handler  = new TheHandler();
		othello.addActionListener(handler);
		battleShip.addActionListener(handler);
		checkers.addActionListener(handler);
		exit.addActionListener(handler);
		
		
	}
	/**
	 * Class for the event handler in the program.
	 * @author Owner
	 *
	 */
	private class TheHandler implements ActionListener {
		/**
		 * Method for the actions performed.
		 * @param event is the event performed.
		 */
		public void actionPerformed(final ActionEvent event) {
			if (event.getSource() == othello) {
				Board gameBoard = new Board();
				new Gui(gameBoard);
			} else if (event.getSource() == checkers) {
				new CheckersGui();
			} else if (event.getSource() == exit) {
				JOptionPane.showMessageDialog(null,
						"Thanks for playing the"
						+ " suite!");
				//System.exit(1);
			}
			
		}
	}

}
