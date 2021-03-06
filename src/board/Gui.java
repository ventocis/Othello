package board;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * Class for the Board GUI of Othello.
 * @author Owner
 *
 */
public class Gui extends Board implements ActionListener {

	/**
	 *  make an arrayList of buttons.
	 */
	private ArrayList<JButton> buttons = new ArrayList<JButton>();
	/**
	 * Variable for number of buttons.
	 */
	private static final int NUMBUTTONS = 2;
	/**
	 * Boolean toggle value for AI.
	 */
	private boolean ai = true;

	/** 
	 * instantiate our icons.
	 */
	private Icon blankPc = new ImageIcon("imgs/square.png");
	/** 
	 * instantiate our icons.
	 */
	private Icon whitePc = new ImageIcon("imgs/white pc.png");
	/** 
	 * instantiate our icons.
	 */
	private Icon blackPc = new ImageIcon("imgs/black pc.png");

	/** 
	 * variables to hold the elements in the GUI.
	 * 
	 */
	private AI myAi;
	/** 
	 * variables to hold the elements in the GUI.
	 * 
	 */
	private Board gameBoard;
	/**
	 * Method for getting buttons.
	 * @return buttons
	 */
	public ArrayList<JButton> getButtons() {
		return buttons;
	}
	/**
	 * Method for setting the buttons.
	 * @param buttons sets the buttons.
	 */
	public void setButtons(final ArrayList<JButton> buttons) {
		this.buttons = buttons;
	}
	/**
	 * Method to check if AI is on.
	 * @return returns true if on.
	 */
	public boolean isAi() {
		return ai;
	}
	/**
	 * Method to set status of AI.
	 * @param ai sets the ai.
	 */
	public void setAi(final boolean ai) {
		this.ai = ai;
	}
	/**
	 * Getter method for blank spaces.
	 * @return returns the blank.
	 */
	public Icon getBlankPc() {
		return blankPc;
	}
	/**
	 * Method for setting to blank.
	 * @param blankPc sets the piece to blank.
	 */
	public void setBlankPc(final Icon blankPc) {
		this.blankPc = blankPc;
	}
	/**
	 * Method for setting to white.
	 * @return whitePc sets the piece to white.
	 */
	public Icon getWhitePc() {
		return whitePc;
	}
	/**
	 * Method that sets piece to white.
	 * @param whitePc sets the white piece.
	 */
	public void setWhitePc(final Icon whitePc) {
		this.whitePc = whitePc;
	}
	/**
	 * Method to get the black piece.
	 * @return return the piece.
	 */
	public Icon getBlackPc() {
		return blackPc;
	}
	/**
	 * Method to set piece.
	 * @param blackPc sets the black piece.
	 */
	public void setBlackPc(final Icon blackPc) {
		this.blackPc = blackPc;
	}
	/**
	 * Method to get AI.
	 * @return returns the AI.
	 */
	public AI getMyAi() {
		return myAi;
	}
	/**
	 * Method to set the AI.
	 * @param myAi sets the AI.
	 */
	public void setMyAi(final AI myAi) {
		this.myAi = myAi;
	}
	/**
	 * Method to get the gameboard.
	 * @return returns the board.
	 */
	public Board getGameBoard() {
		return gameBoard;
	}
	/**
	 * Sets the game board to board.
	 * @param gameBoard sets the board.
	 */
	public void setGameBoard(final Board gameBoard) {
		this.gameBoard = gameBoard;
	}
	/**
	 * Method to get gridbag.
	 * @return returns the grid bag.
	 */
	public GridBagLayout getGridBag() {
		return gridBag;
	}
	/**
	 * Method to set grid bag.
	 * @param gridBag .
	 */
	public void setGridBag(final GridBagLayout gridBag) {
		this.gridBag = gridBag;
	}
	/**
	 * gets the P.
	 * @return returns the p.
	 */
	public JPanel getP() {
		return p;
	}
	/**
	 * sets the panel.
	 * @param p sets the panel.
	 */
	public void setP(final JPanel p) {
		this.p = p;
	}
	/**
	 * Returns the players turn.
	 * @return the turn.
	 */
	public JLabel getPlyrTurn() {
		return plyrTurn;
	}
	/**
	 * Sets the curr players turn.
	 * @param plyrTurn .
	 */
	public void setPlyrTurn(final JLabel plyrTurn) {
		this.plyrTurn = plyrTurn;
	}
	/**
	 * gets the label for black score.
	 * @return blackscore.
	 */
	public JLabel getBlackScore() {
		return blackScore;
	}
	/**
	 * sets the black score label.
	 * @param blackScore sets the black score
	 */
	public void setBlackScore(final JLabel blackScore) {
		this.blackScore = blackScore;
	}
	/**
	 * gets the whites score label.
	 * @return returns the label.
	 */
	public JLabel getWhiteScore() {
		return whiteScore;
	}
	/**
	 * sets the white score label.
	 * @param whiteScore .
	 */
	public void setWhiteScore(final JLabel whiteScore) {
		this.whiteScore = whiteScore;
	}
	/**
	 * Method to get the frame.
	 * @return returns the frame.
	 */
	public JFrame getF() {
		return f;
	}
	/**
	 * Sets the frame to frame.
	 * @param f sets frame.
	 */
	public void setF(final JFrame f) {
		this.f = f;
	}
	/**
	 * Getter to get the number of buttons.
	 * @return the number of buttons.
	 */
	public static int getNumbuttons() {
		return NUMBUTTONS;
	}

	/** 
	 * variables to hold the elements in the GUI.
	 * 
	 */
	private GridBagLayout gridBag = new GridBagLayout();
	/** 
	 * variables to hold the elements in the GUI.
	 * 
	 */
	private JPanel p = new JPanel(gridBag);

	/**
	 * These are three labels one for: player turn,
	 *  black piece score, white piece
	 * score.
	 */
	private JLabel plyrTurn, blackScore, whiteScore;
	/**
	 * Variable for frame.
	 */
	private JFrame f;
	/**
	 * Acts as constructor for GUI.
	 * @param initialBoard is the starting board.
	 */
	public Gui(final Board initialBoard) {
		GridBagConstraints cnstrnts = new GridBagConstraints();
		// Gives a name to the frame
		f = new JFrame("Othello");
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// store the game board so we can access it later
		gameBoard = initialBoard;
		myAi = new AI();

		cnstrnts.fill = GridBagConstraints.BOTH;

		// Add in the icons for the game
		JButton newGameButton = new JButton("New Game");
		newGameButton.setPreferredSize(new Dimension(40, 20));
		newGameButton.setVerticalAlignment(JLabel.BOTTOM);
		cnstrnts.gridx = 0;
		cnstrnts.gridy = 0;
		cnstrnts.gridwidth = 2;
		buttons.add(newGameButton);
		gridBag.setConstraints(newGameButton, cnstrnts);
		
		cnstrnts.gridwidth = 1;

		// Button to toggle AI
		JButton jAi = new JButton("Toggle AI");
		newGameButton.setPreferredSize(new Dimension(40, 20));
		newGameButton.setVerticalAlignment(JLabel.BOTTOM);
		cnstrnts.gridx = 2;
		cnstrnts.gridy = 0;
		cnstrnts.gridwidth = 2;
		buttons.add(jAi);
		gridBag.setConstraints(jAi, cnstrnts);


		// icon to indicate whose turn it is
		plyrTurn = new JLabel("White's Turn");
		plyrTurn.setHorizontalTextPosition(JLabel.CENTER);
		plyrTurn.setVerticalTextPosition(JLabel.BOTTOM);
		plyrTurn.setVerticalAlignment(JLabel.BOTTOM);
		cnstrnts.gridwidth = 2;
		cnstrnts.gridx = 4;
		cnstrnts.gridy = 0;
		gridBag.setConstraints(plyrTurn, cnstrnts);

		// Icon to indicate the number of pieces black has
		cnstrnts.gridwidth = 1;
		blackScore = new JLabel("Black: 2");
		blackScore.setHorizontalTextPosition(JLabel.CENTER);
		blackScore.setVerticalTextPosition(JLabel.BOTTOM);
		blackScore.setHorizontalAlignment(JLabel.CENTER);
		blackScore.setVerticalAlignment(JLabel.BOTTOM);
		cnstrnts.gridx = 6;
		cnstrnts.gridy = 0;
		gridBag.setConstraints(blackScore, cnstrnts);

		// icon to indicate the number of pieces white has
		whiteScore = new JLabel("White: 2");
		whiteScore.setHorizontalTextPosition(JLabel.CENTER);
		whiteScore.setVerticalTextPosition(JLabel.BOTTOM);
		whiteScore.setHorizontalAlignment(JLabel.CENTER);
		whiteScore.setVerticalAlignment(JLabel.BOTTOM);
		cnstrnts.gridx = 7;
		cnstrnts.gridy = 0;
		gridBag.setConstraints(whiteScore, cnstrnts);

		// add the icons to the JPanel
		p.add(plyrTurn);
		p.add(blackScore);
		p.add(whiteScore);

		// Set icons for each JButton & add them to the arrayList
		// of JButtons
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

		// Set up the JButtons
		for (JButton button : buttons) {
			button.addActionListener(this);
			button.setOpaque(true);
			p.add(button);
		}

		// Make sure the GUI displays correctly when it pops up
		p.setSize(2500, 2500);
		f.setPreferredSize(new Dimension(700, 700));
		p.setVisible(true);
		f.add(p);
		f.pack();
		f.setVisible(true);

	}

	@Override
	/*
	 * Used to catch any actions that are performed on the GUI
	 */
	public void actionPerformed(final ActionEvent e) {
		/*
		 * x and y are used to hold the column
		 *  and row position on the board z is used
		 * so that x and y don't increment when
		 *  the button loop is, for one of the top
		 * buttons such as new game.
		 */
		int x = 0, y = 0, z = 0;

		int[] move;

		// for loops to check & see which button was pushed
		for (JButton button : buttons) {
			// check the source of the button
			if (e.getSource() == button) {
				if (z == 0) {
					gameBoard.newGame();
				} else if (z == 1) {
					if (ai) {
						ai = false;
					} else {
						ai = true;
					}
				}
				if (z > NUMBUTTONS - 1) {
					// Make sure it's a valid move for
					//black,
					// if so, play the piece
					if (gameBoard.getCurrPlyr() == 1) {
						if (gameBoard.isValidMove(x,
								y)) {
							gameBoard.playPiece(x,
									y);
							button.setIcon(blackPc);
							Board.nextPlyr();
						}
					} else if (gameBoard
							.isValidMove(x, y)) {
						gameBoard.playPiece(x, y);
						drawBoard();
						button.setIcon(whitePc);
						Board.nextPlyr();
						if (ai) {
							drawBoard();
							move = myAi
							.computeMove(gameBoard);
							gameBoard
							.playPiece(move[1],
								move[0]);
							Board.nextPlyr();
						}
					}
				}
			}

			// increment x and y if the loop is
			// passed the top buttons
			if (z > NUMBUTTONS - 1) {
				if (y == 7) {
					y = 0;
					x++;
				} else {
					y++;
				}
			}
			// increment z, z tracks which button we are on
			// (out of the total buttons, not just the board)
			z++;
		}

		drawBoard();

		// Make sure the next player can move
		if (!canMove()) {
			// If player can't move then toggle player again
			Board.nextPlyr();
			// check if both players can't move
			if (!canMove()) {
				if (gameBoard.getWinner() == 1) {
					JOptionPane.showMessageDialog(f,
							"\nThis win goes"
							+ " to Black!");
				} else if (gameBoard
						.getWinner() == -1) {
					JOptionPane.showMessageDialog(f,
							"Oh my, oh my\nLooks"
							+ " like we have"
							+ " a tie!");
				} else {
					JOptionPane.showMessageDialog(f,
							"\nThe win goes"
							+ " to White!");

				gameBoard.newGame();
			}
				} else if (getCurrPlyr() == 1) {
				JOptionPane.showMessageDialog(f, "No available"
						+ " moves for white");
			} else {
				JOptionPane.showMessageDialog(f, "No available"
						+ " moves for black");
		}
			}

		// draw the board to reflect any current player changes
		drawBoard();

	}

	/**
	 * Draw the board by updating the icons based
	 *  on which player is where in the
	 * board array.
	 */
	private void drawBoard() {
		/*
		 * x and y are used to hold the x and y
		 *  position on the board z is used so that
		 * x and y don't increment when the button
		 *  loop is for one of the top buttons
		 * such as New Game
		 */
		System.out.println("check");
		int x = 0, y = 0, z = 0;
		// Go through each button
		for (JButton button : buttons) {
			// Make sure it's not on the top buttons
			if (z > NUMBUTTONS - 1) {
				// set the appropriate icon
				if (gameBoard.getPlyr(x, y) == 1) {
					button.setIcon(blackPc);
				} else if (gameBoard.getPlyr(x, y) == 2) {
					button.setIcon(whitePc);
				} else {
					button.setIcon(blankPc);
				}
				if (y == 7) {
					y = 0;
					x++;
				} else {
					y++;
				}
			}
			z++;
		}

		// Set the current player on the board
		String player;
		if (gameBoard.getCurrPlyr() == 1) {
			player = "Black";
		} else {
			player = "White";
		}
		plyrTurn.setText(player + "'s Turn");
		blackScore.setText("Black: " + gameBoard.blackScore());
		whiteScore.setText("White: " + gameBoard.whiteScore());
	}
}
