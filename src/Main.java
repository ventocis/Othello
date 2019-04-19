import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;//need this
/*******************************************************************
 * This is an Othello game that counts the number of pieces each 
 * player has and displays whose turn it is currently.
 * 
 * @author Sam Ventocilla, Chandler, and Alen
 * @version 1.0, Winter 2019
 ******************************************************************/

public class Main extends JFrame{
    public static void main(String[] args){

    	JFrame frame = new JFrame("Game Suite");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.BLACK);
		
		MainMenuPanel panel = new MainMenuPanel();
		frame.getContentPane().add(panel);
		
    	frame.setSize(100,200);
    	frame.setLocationRelativeTo(null);
    	
    	frame.setVisible(true);

    }
}
