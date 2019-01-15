import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Gui extends Board implements ActionListener {

	// make an arraylist of buttons
	ArrayList<JButton> buttons = new ArrayList<JButton>();
	static final int NUMLABELS = 1;

	// instantiate our icons
	Icon blankPc = new ImageIcon("imgs/square.png");
	Icon whitePc = new ImageIcon("imgs/white pc.png");
	Icon blackPc = new ImageIcon("imgs/black pc.png");

	// variable to hold the gameboard
	Board gameBoard;
	GridBagLayout gridBag = new GridBagLayout();
	JPanel p = new JPanel(gridBag);
	JLabel plyrTurn, blackScore, whiteScore;

	public Gui(Board initialBoard) {
		GridBagConstraints cnstrnts = new GridBagConstraints();
		// p.add()
		JFrame f = new JFrame("Othello");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// store the game board so we can access it later
		gameBoard = initialBoard;

		cnstrnts.fill = GridBagConstraints.BOTH;
		// Add in the icons for the game
		JButton newGameButton = new JButton("New Game");
		cnstrnts.gridx = 0;
		cnstrnts.gridy = 0;
		buttons.add(newGameButton);
		gridBag.setConstraints(newGameButton, cnstrnts);

		// icon to indicate whose turn it is
		plyrTurn = new JLabel("Black's Turn");
		plyrTurn.setHorizontalTextPosition(JLabel.CENTER);
		plyrTurn.setVerticalTextPosition(JLabel.BOTTOM);
		cnstrnts.gridx = 1;
		cnstrnts.gridy = 0;
		gridBag.setConstraints(plyrTurn, cnstrnts);
		
		blackScore = new JLabel("Black: 2");
		blackScore.setHorizontalTextPosition(JLabel.CENTER);
		blackScore.setVerticalTextPosition(JLabel.BOTTOM);
		cnstrnts.gridx = 6;
		cnstrnts.gridy = 0;
		gridBag.setConstraints(blackScore, cnstrnts);
		
		whiteScore = new JLabel("White: 2");
		whiteScore.setHorizontalTextPosition(JLabel.CENTER);
		whiteScore.setVerticalTextPosition(JLabel.BOTTOM);
		cnstrnts.gridx = 7;
		cnstrnts.gridy = 0;
		gridBag.setConstraints(whiteScore, cnstrnts);
		
		
		p.add(plyrTurn);
		p.add(blackScore);
		p.add(whiteScore);

		for (int x = 0; x < 8; x++) {
			for (int y = 1; y < 9; y++) {
				JButton button = new JButton();
				if (gameBoard.getPlyr(x, y - 1) == 1) {
					button.setIcon(blackPc);
					buttons.add(button);
				} else if (gameBoard.getPlyr(x, y - 1) == 2) {
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

		int x = 0, y = 0, z = 0;

		for (JButton button : buttons) {
			// check the source of the button
			if (e.getSource() == button) {
				// set the piece color based on the current player
				if (z > NUMLABELS - 1) {
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
				} else if (z == 0)
					gameBoard.newGame();
			}

			if (z > NUMLABELS - 1) {
				if (y == 7) {
					y = 0;
					x++;
				} else
					y++;
			}
			z++;
		}

		drawBoard();

	}

	private void drawBoard() {
		int x = 0, y = 0, z = 0;
		for (JButton button : buttons) {
			// check the source of the button
			if (z > NUMLABELS-1) {
				if (gameBoard.getPlyr(x, y) == 1)
					button.setIcon(blackPc);
				else if (gameBoard.getPlyr(x, y) == 2)
					button.setIcon(whitePc);
				else
					button.setIcon(blankPc);

				if (y == 7) {
					y = 0;
					x++;
				} else
					y++;
			}
			z++;
		}
		
		String player;
		if(gameBoard.getCurrPlyr() == 1)
			player = "Black";
		else
			player = "White";
		plyrTurn.setText(player + "'s Turn");
		blackScore.setText("Black: " + gameBoard.blackScore());
		whiteScore.setText("White: " + gameBoard.whiteScore());
	}
	
	
}
