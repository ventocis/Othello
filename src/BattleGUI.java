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

import java.util.Scanner;

public class  BattleGUI extends BattleBoard implements ActionListener {

	// make an arrayList of buttons
	private boolean smallShip;
	private boolean medShip;
	private boolean bigShip;
	private boolean biggestShip;
	ArrayList<JButton> buttons = new ArrayList<JButton>();
	static final int NUMLABELS = 5;

	// instantiate our icons
	Icon blankSpace = new ImageIcon("imgs/water.jpg");
	Icon hitPc = new ImageIcon("imgs/hit.jpg");
	Icon noHitPc = new ImageIcon("imgs/noHit.jpg");
	Icon ship = new ImageIcon("imgs/ship.jpg");



	// variables to hold the elements in the GUI
	BattleBoard gameBoard;
	GridBagLayout gridBag = new GridBagLayout();

	JPanel p = new JPanel(gridBag);
	/*These are three labels one for:
	 * player turn, black piece score, white piece score
	 */
	JLabel player1Hits, player2Hits;
	JFrame f;

	public BattleGUI(BattleBoard initialBoard) {
		GridBagConstraints cnstrnts = new GridBagConstraints();
		//Gives a name to the frame
		f = new JFrame("Battle Ship");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// store the game board so we can access it later
		gameBoard = initialBoard;

		cnstrnts.fill = GridBagConstraints.BOTH;


		// Add in the icons for the game
		JButton newGameButton = new JButton("New Game");
		newGameButton.setPreferredSize(new Dimension(40,20)); 
		newGameButton.setVerticalAlignment(JLabel.BOTTOM);
		cnstrnts.gridx = 0;
		cnstrnts.gridy = 0;
		buttons.add(newGameButton);
		gridBag.setConstraints(newGameButton, cnstrnts);
		
		// Add in the icons for the game
		JButton smallShipButton = new JButton("SSH");
		smallShipButton.setPreferredSize(new Dimension(40,20)); 
		smallShipButton.setVerticalAlignment(JLabel.BOTTOM);
		cnstrnts.gridx = 3;
		cnstrnts.gridy = 0;
		buttons.add(smallShipButton);
		gridBag.setConstraints(smallShipButton, cnstrnts);
		
		JButton medShipButton = new JButton("MSH");
		medShipButton.setPreferredSize(new Dimension(40,20)); 
		medShipButton.setVerticalAlignment(JLabel.BOTTOM);
		cnstrnts.gridx = 4;
		cnstrnts.gridy = 0;
		buttons.add(medShipButton);
		gridBag.setConstraints(medShipButton, cnstrnts);
		
		JButton bigShipButton = new JButton("BSH");
		bigShipButton.setPreferredSize(new Dimension(40,20)); 
		bigShipButton.setVerticalAlignment(JLabel.BOTTOM);
		cnstrnts.gridx = 5;
		cnstrnts.gridy = 0;
		buttons.add(bigShipButton);
		gridBag.setConstraints(bigShipButton, cnstrnts);
		
		JButton biggestShipButton = new JButton("SBSH");
		biggestShipButton.setPreferredSize(new Dimension(40,20)); 
		biggestShipButton.setVerticalAlignment(JLabel.BOTTOM);
		cnstrnts.gridx = 6;
		cnstrnts.gridy = 0;
		buttons.add(biggestShipButton);
		gridBag.setConstraints(biggestShipButton, cnstrnts);


		//Icon to indicate the number of pieces black has
		player1Hits = new JLabel("Your Hits: 0");
		player1Hits.setHorizontalTextPosition(JLabel.CENTER);
		player1Hits.setVerticalTextPosition(JLabel.BOTTOM);
		player1Hits.setHorizontalAlignment(JLabel.CENTER);
		player1Hits.setVerticalAlignment(JLabel.BOTTOM);
		cnstrnts.gridx = 7;
		cnstrnts.gridy = 0;
		gridBag.setConstraints(player1Hits, cnstrnts);

		//icon to indicate the number of pieces white has
		player2Hits = new JLabel("Computer Hits: 0");
		player2Hits.setHorizontalTextPosition(JLabel.CENTER);
		player2Hits.setVerticalTextPosition(JLabel.BOTTOM);
		player2Hits.setHorizontalAlignment(JLabel.CENTER);
		player2Hits.setVerticalAlignment(JLabel.BOTTOM);
		cnstrnts.gridx = 8;
		cnstrnts.gridy = 0;
		gridBag.setConstraints(player2Hits, cnstrnts);

		//add the icons to the JPanel
		p.add(player1Hits);
		p.add(player2Hits);

		//Set icons for each JButton & add them to the arrayList
		//of JButtons will write this after writing code for Board
		for (int x = 0; x < 10; x++) {
			for (int y = 1; y < 10; y++) {
				JButton button = new JButton();
				if (gameBoard.getPlyr(x, y) == getShip()) {
					button.setIcon(ship);
					buttons.add(button);
				}else if (gameBoard.getPlyr(x, y) == getRedpeg()) {
					button.setIcon(hitPc);
					buttons.add(button);
				}else if (gameBoard.getPlyr(x, y) == getGreypeg()) {
					button.setIcon(noHitPc);
					buttons.add(button);
				} else {
					button.setIcon(blankSpace);
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

		//Make sure the GUI displays correctly when it pops up
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
		/*x and y are used to hold the column and row position 
		 * on the board
		 *z is used so that x and y don't increment when the
		 * button loop is,
		 *for one of the top buttons such as new game.
		 */
		int x = 0, y = 0, z = 0;

		//for loops to check & see which button was pushed
		for (JButton button : buttons) {
			// check the source of the button
			if (e.getSource() == button) {
				if (z == 0) {
					gameBoard.initializeBoard();
				}else if(z == 1) {
				smallShip = true;
				}
				//Make sure that the JButton isn't one of the
				//top buttons 
				if (z > NUMLABELS - 1) {
					//Make sure it's a valid move for horizontal ship and vertical,
					//if so, play the correct piece
					if (smallShip) {
							gameBoard.placeShipAt("small ship h", x, y);
							button.setIcon(ship);
							gameBoard.switchPlayer();
					}
					if (gameBoard.getCurrPlyr() == 0) {
						if (gameBoard.isValidHorizontalShipMove("medium ship", x, y)) {
							gameBoard.placeShipAt("medium ship h", x, y);
							button.setIcon(ship);
//							gameBoard.nextPlyr();
						}
					}
					if (gameBoard.getCurrPlyr() == 0) {
						if (gameBoard.isValidHorizontalShipMove("big ship", x, y)) {
							gameBoard.placeShipAt("big ship h", x, y);
							button.setIcon(ship);
//							gameBoard.nextPlyr();
						}
					}
					if (gameBoard.getCurrPlyr() == 0) {
						if (gameBoard.isValidHorizontalShipMove("biggest ship", x, y)) {
							gameBoard.placeShipAt("biggest ship h", x, y);
							button.setIcon(ship);
//							gameBoard.nextPlyr();
						}
					}
					if (gameBoard.getCurrPlyr() == 1) {
						if (gameBoard.isValidHorizontalShipMove("small ship", x, y)) {
							gameBoard.placeShipAt("small ship v", x, y);
							button.setIcon(ship);
//							gameBoard.nextPlyr();
						}
					}
					if (gameBoard.getCurrPlyr() == 1) {
						if (gameBoard.isValidHorizontalShipMove("medium ship", x, y)) {
							gameBoard.placeShipAt("small ship v", x, y);
							button.setIcon(ship);
//							gameBoard.nextPlyr();
						}
					}
					if (gameBoard.getCurrPlyr() == 1) {
						if (gameBoard.isValidHorizontalShipMove("big ship", x, y)) {
							gameBoard.placeShipAt("small ship v", x, y);
							button.setIcon(ship);
//							gameBoard.nextPlyr();
						}
					}
					if (gameBoard.getCurrPlyr() == 1) {
						if (gameBoard.isValidHorizontalShipMove("biggest ship", x, y)) {
							gameBoard.placeShipAt("small ship v", x, y);
							button.setIcon(ship);
//							gameBoard.nextPlyr();
						}
					}
			}
			
		}

			//increment x and y if the loop is
			//passed the top buttons
			if (z > NUMLABELS - 1) {
				if (y == 9) {
					y = 0;
					x++;
				} else {
					y++;
				}
			}
			//increment z, z tracks which button we are on
			//(out of the total buttons, not just the board)
			z++;
	}
		drawBoard();
	}
	
	/**
	 * Draw the board by updating the icons based on which player is where
	 * in the board array.
	 */
	private void drawBoard() {
		/*x and y are used to hold the x and y position on the board
		 *z is used so that x and y don't increment when the button
		 *loop is for one of the top buttons
		 *such as New Game
		 */
		int x = 0, y = 0, z = 0;
		//Go through each button
		for (JButton button : buttons) {
			//Make sure it's not on the top buttons
			if (z > NUMLABELS - 1) {
				//set the appropriate icon
				if (gameBoard.getPlyr(x, y) == getEmpty()) {
					button.setIcon(blankSpace);
				}else if(gameBoard.getPlyr(x, y) == getRedpeg()) {
					button.setIcon(hitPc);
				}else if(gameBoard.getPlyr(x, y) == getGreypeg()) {
					button.setIcon(noHitPc);
				}else if(gameBoard.getPlyr(x, y) == getShip()) {
					button.setIcon(ship);
				}

			}
			z++;
		}

		//Set the current player on the board
//		String player;
//		if (gameBoard.getCurrPlyr() == getHumanPlayer()) {
//			player = "Human";
//		} else {
//			player = "Computer";
//		}
//		player1Hits.setText("Human: " + gameBoard.getPlayerScore(getHumanPlayer()));
//		player2Hits.setText("Computer: " + gameBoard.getPlayerScore(getCompPlayer()));
	}
}
