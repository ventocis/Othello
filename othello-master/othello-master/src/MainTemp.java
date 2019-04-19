import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;//need this


public class MainTemp extends JFrame{
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