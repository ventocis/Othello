package board;
import java.awt.Color;

import javax.swing.JFrame;
/*******************************************************************
 * This is an Othello game that counts the number of pieces each 
 * player has and displays whose turn it is currently.
 * 
 * @author Sam Ventocilla, Chandler, and Alen
 * @version 1.0, Winter 2019
 ******************************************************************/

public class Main extends JFrame {
 
	private static final long serialVersionUID = 1L;
	/**
	 * Main that the program runs from. 
	 * @param args argument array.
	 */
	public static void main(final String[] args) {

    	JFrame frame = new JFrame("Game Suite");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.BLACK);
		
		MainMenuPanel panel = new MainMenuPanel();
		frame.getContentPane().add(panel);
		
    	frame.setSize(100, 200);
    	frame.setLocationRelativeTo(null);
    	
    	frame.setVisible(true);

    }
}
