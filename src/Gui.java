import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Gui extends Board implements ActionListener{
	
	//make an arraylist of buttons
	ArrayList <JButton> buttons = new ArrayList<JButton>();
	
	//instantiate our icons
	Icon blankIcon = new ImageIcon("imgs/square.png");
	Icon whitePc = new ImageIcon("imgs/white pc.png");
	Icon blackPc = new ImageIcon("imgs/black pc.png");
	
	//variable to hold the gameboard
	Board gameBoard;
	
	public Gui(Board initialBoard) {
		JPanel p = new JPanel(new GridLayout(9,8,2,2));
		//p.add()
		JFrame f = new JFrame("Othello");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//store the game board so we can access it later
		gameBoard = initialBoard;
		
		
		//Add in the icons for the game
		for(int i = 0; i < 64; i++)
			if(i == 27)
				buttons.add(new JButton(blackPc));
			else if(i == 28)
				buttons.add(new JButton(whitePc));
			else if(i == 35)
				buttons.add(new JButton(whitePc));
			else if(i == 36)
				buttons.add(new JButton(blackPc));
			else
				buttons.add(new JButton(blankIcon));
		
		//Set up the jbuttons
		for(JButton button:buttons) {
			//button.setBackground(Color.gray);
			//button.setBorder(BorderFactory.createEmptyBorder());
			button.addActionListener(this);
			button.setOpaque(true);
			p.add(button);
		}
		
		
		p.setSize(2500,2500);
		f.setPreferredSize(new Dimension(700,700));
		p.setVisible(true);
		f.add(p);
		f.pack();
		f.setVisible(true);		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		int x = 0, y = 0;
		
		for(JButton button:buttons) {
			//check the source of the button
			if(e.getSource() == button) {
				//set the piece color based on the current player
				if(gameBoard.getCurrPlyr() == 1) {
					if(gameBoard.isValidMove(x, y)) {
						gameBoard.playPiece(x, y);
						button.setIcon(blackPc);
						gameBoard.nextPlyr();
					}
				}
				else if(gameBoard.isValidMove(x, y)) {
						gameBoard.playPiece(x, y);
						button.setIcon(whitePc);
						gameBoard.nextPlyr();
					}
			}
			if(x == 7) {
				x = 0;
				y++;
			}
			else
				x++;
		}
		
		
		x = 0;
		y = 0;
		for(JButton button:buttons) {
			//check the source of the button
			if(gameBoard.getPlyr(x, y) == 1)
				button.setIcon(blackPc);
			else if(gameBoard.getPlyr(x, y) == 2)
				button.setIcon(whitePc);
			
			if(x == 7) {
				x = 0;
				y++;
			}
			else
				x++;
		}
		
		
		gameBoard.isWinner();
		//FIXME add something to check if there's a valid move for that player
	}

}
