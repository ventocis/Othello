import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Gui extends Board implements ActionListener {

	// make an arraylist of buttons
	ArrayList<JButton> buttons = new ArrayList<JButton>();

	// instantiate our icons
	Icon blankPc = new ImageIcon("imgs/square.png");
	Icon whitePc = new ImageIcon("imgs/white pc.png");
	Icon blackPc = new ImageIcon("imgs/black pc.png");

	// variable to hold the gameboard
	Board gameBoard;
	GridBagLayout gridBag = new GridBagLayout();
	JPanel p = new JPanel(gridBag);

	public Gui(Board initialBoard) {
		GridBagConstraints cnstrnts = new GridBagConstraints();
		// p.add()
		JFrame f = new JFrame("Othello");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// store the game board so we can access it later
		gameBoard = initialBoard;

		cnstrnts.fill = GridBagConstraints.BOTH;
		// Add in the icons for the game
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				JButton button = new JButton();
				if (gameBoard.getPlyr(x, y) == 1) {
					button.setIcon(blackPc);
					buttons.add(button);
				} else if (gameBoard.getPlyr(x, y) == 2) {
					button.setIcon(whitePc);
					buttons.add(button);
				} else {
					button.setIcon(blankPc);
					buttons.add(button);

				}
				cnstrnts.gridx = x;
				cnstrnts.gridy = y;
				gridBag.setConstraints(button, cnstrnts);
			}
		}

//			if (i == 27)
//				buttons.add(new JButton(blackPc));
//			else if (i == 28)
//				buttons.add(new JButton(whitePc));
//			else if (i == 35)
//				buttons.add(new JButton(whitePc));
//			else if (i == 36)
//				buttons.add(new JButton(blackPc));
//			else
//				buttons.add(new JButton(blankIcon));

		// Set up the jbuttons
		for (JButton button : buttons) {
			// button.setBackground(Color.gray);
			// button.setBorder(BorderFactory.createEmptyBorder());
			button.addActionListener(this);
			button.setOpaque(true);
			p.add(button);
		}

		p.setSize(2500, 2500);
		f.setPreferredSize(new Dimension(700, 700));
		p.setVisible(true);
		f.add(p);
		f.pack();
		f.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		int x = 0, y = 0;

		for (JButton button : buttons) {
			// check the source of the button
			if (e.getSource() == button) {
				// set the piece color based on the current player
				if (gameBoard.getCurrPlyr() == 1) {
					if (gameBoard.isValidMove(x, y)) {
						gameBoard.playPiece(x, y);
						button.setIcon(blackPc);
						gameBoard.nextPlyr();
					}
				} else if (gameBoard.isValidMove(x, y)) {
					gameBoard.playPiece(x, y);
					button.setIcon(whitePc);
					gameBoard.nextPlyr();
				}
			}
			if (x == 7) {
				x = 0;
				y++;
			} else
				x++;
		}

		drawBoard();

		/*
		 * int winner = gameBoard.isWinner();
		 * 
		 * boolean availMoves = false; if(winner != 0) System.out.println("Player " +
		 * winner + " won!"); //FIXME add something to check if there's a valid move for
		 * that player else for(int i = 0; i < 8; i++) for(int j = 0; j < 8; j++)
		 * if(gameBoard.isValidMove(i, j)) availMoves = true;
		 * 
		 * if(!availMoves) System.out.println("No available moves");
		 */
	}

	private void drawBoard() {
		int x = 0, y = 0;
		for (JButton button : buttons) {
			// check the source of the button
			if (gameBoard.getPlyr(x, y) == 1)
				button.setIcon(blackPc);
			else if (gameBoard.getPlyr(x, y) == 2)
				button.setIcon(whitePc);

			if (x == 7) {
				x = 0;
				y++;
			} else
				x++;
		}
	}
}
