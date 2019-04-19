//import java.awt.Dimension;
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//
//import javax.swing.Icon;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//
//public class AIBattleGUI extends BattleBoard implements ActionListener {
//
//	// make an arrayList of buttons
//	ArrayList<JButton> buttons = new ArrayList<JButton>();
//	private static final int NUMLABELS = 9;
//
//	private static final int SHIFTER = 4;
//	private static final int VSHIFTER = 0;
//
//	boolean sShip = false;
//	boolean mShip = false;
//	boolean bShip = false;
//	boolean bstShip = false;
//
//	int toggle = 0;
//	int x1, x2, y1, y2, xChange, yChange;
//
//	// instantiate our icons
//	Icon blankSpace = new ImageIcon("imgs/water.png");
//	Icon hitPc = new ImageIcon("imgs/hit.png");
//	Icon noHitPc = new ImageIcon("imgs/noHit.png");
//	Icon smallShip = new ImageIcon("imgs/small_ship.png");
//	Icon medShip = new ImageIcon("imgs/medium_ship.png");
//	Icon bigShip = new ImageIcon("imgs/big_ship.png");
//	Icon biggestShip = new ImageIcon("imgs/biggest_ship.png");
//
//	// variables to hold the elements in the GUI
//	BattleBoard aiGameBoard;
//	GridBagLayout gridBag = new GridBagLayout();
//	JPanel p = new JPanel(gridBag);
//	/*
//	 * These are three labels one for: player turn, black piece score, white piece
//	 * score
//	 */
//	JLabel player1Hits, player2Hits, hShips, vShips;
//	JFrame f;
//
//	public AIBattleGUI(BattleBoard initialBoard) {
//		GridBagConstraints cnstrnts = new GridBagConstraints();
//		// Gives a name to the frame
//		f = new JFrame("AI Battle Ship");
//		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//
//		// store the game board so we can access it later
//		aiGameBoard = initialBoard;
//
//		cnstrnts.fill = GridBagConstraints.BOTH;
//
//		// Add in the icons for the game
//		JButton newGameButton = new JButton("New Game");
//		newGameButton.setPreferredSize(new Dimension(40, 20));
//		newGameButton.setVerticalAlignment(JLabel.BOTTOM);
//		cnstrnts.gridx = 0;
//		cnstrnts.gridy = VSHIFTER;
//		cnstrnts.gridwidth = 2;
//		buttons.add(newGameButton);
//		gridBag.setConstraints(newGameButton, cnstrnts);
//
//		cnstrnts.gridwidth = 1;
//		// Add in the icons for the game
//		JButton h5 = new JButton("5");
//		newGameButton.setPreferredSize(new Dimension(30, 20));
//		newGameButton.setVerticalAlignment(JLabel.BOTTOM);
//		cnstrnts.gridx = SHIFTER;
//		cnstrnts.gridy = VSHIFTER;
//		buttons.add(h5);
//		gridBag.setConstraints(h5, cnstrnts);
//
//		// Add in the icons for the game
//		JButton h4 = new JButton("4");
//		newGameButton.setPreferredSize(new Dimension(40, 20));
//		newGameButton.setVerticalAlignment(JLabel.BOTTOM);
//		cnstrnts.gridx = 1 + SHIFTER;
//		cnstrnts.gridy = VSHIFTER;
//		buttons.add(h4);
//		gridBag.setConstraints(h4, cnstrnts);
//
//		// Add in the icons for the game
//		JButton h3 = new JButton("3");
//		newGameButton.setPreferredSize(new Dimension(40, 20));
//		newGameButton.setVerticalAlignment(JLabel.BOTTOM);
//		cnstrnts.gridx = 2 + SHIFTER;
//		cnstrnts.gridy = VSHIFTER;
//		buttons.add(h3);
//		gridBag.setConstraints(h3, cnstrnts);
//
//		// Add in the icons for the game
//		JButton h2 = new JButton("2");
//		newGameButton.setPreferredSize(new Dimension(40, 20));
//		newGameButton.setVerticalAlignment(JLabel.BOTTOM);
//		cnstrnts.gridx = 3 + SHIFTER;
//		cnstrnts.gridy = VSHIFTER;
//		buttons.add(h2);
//		gridBag.setConstraints(h2, cnstrnts);
//
//		// Add in the icons for the game
//		JButton v5 = new JButton("5");
//		newGameButton.setPreferredSize(new Dimension(40, 20));
//		newGameButton.setVerticalAlignment(JLabel.BOTTOM);
//		cnstrnts.gridx = SHIFTER;
//		cnstrnts.gridy = VSHIFTER + 1;
//		buttons.add(v5);
//		gridBag.setConstraints(v5, cnstrnts);
//
//		// Add in the icons for the game
//		JButton v4 = new JButton("4");
//		newGameButton.setPreferredSize(new Dimension(40, 20));
//		newGameButton.setVerticalAlignment(JLabel.BOTTOM);
//		cnstrnts.gridx = SHIFTER + 1;
//		cnstrnts.gridy = VSHIFTER + 1;
//		buttons.add(v4);
//		gridBag.setConstraints(v4, cnstrnts);
//
//		// Add in the icons for the game
//		JButton v3 = new JButton("3");
//		newGameButton.setPreferredSize(new Dimension(40, 20));
//		newGameButton.setVerticalAlignment(JLabel.BOTTOM);
//		cnstrnts.gridx = SHIFTER + 2;
//		cnstrnts.gridy = VSHIFTER + 1;
//		buttons.add(v3);
//		gridBag.setConstraints(v3, cnstrnts);
//
//		// Add in the icons for the game
//		JButton v2 = new JButton("2");
//		newGameButton.setPreferredSize(new Dimension(40, 20));
//		newGameButton.setVerticalAlignment(JLabel.BOTTOM);
//		cnstrnts.gridx = SHIFTER + 3;
//		cnstrnts.gridy = VSHIFTER + 1;
//		buttons.add(v2);
//		gridBag.setConstraints(v2, cnstrnts);
//
//		// Icon to indicate the number of pieces black has
//		hShips = new JLabel("Horizontal Ships:");
//		hShips.setHorizontalTextPosition(JLabel.CENTER);
//		hShips.setVerticalTextPosition(JLabel.BOTTOM);
//		hShips.setHorizontalAlignment(JLabel.CENTER);
//		hShips.setVerticalAlignment(JLabel.BOTTOM);
//		cnstrnts.gridx = SHIFTER - 2;
//		cnstrnts.gridy = VSHIFTER;
//		cnstrnts.gridwidth = 2;
//		gridBag.setConstraints(hShips, cnstrnts);
//		p.add(hShips);
//
//		// Icon to indicate the number of pieces black has
//		vShips = new JLabel("Vertical Ships:");
//		vShips.setHorizontalTextPosition(JLabel.CENTER);
//		vShips.setVerticalTextPosition(JLabel.BOTTOM);
//		vShips.setHorizontalAlignment(JLabel.CENTER);
//		vShips.setVerticalAlignment(JLabel.BOTTOM);
//		cnstrnts.gridx = SHIFTER - 2;
//		cnstrnts.gridy = 1 + VSHIFTER;
//		gridBag.setConstraints(vShips, cnstrnts);
//		p.add(vShips);
//
//		cnstrnts.gridwidth = 1;
//
//		// Icon to indicate the number of pieces black has
//		player1Hits = new JLabel("You: 0");
//		player1Hits.setHorizontalTextPosition(JLabel.CENTER);
//		player1Hits.setVerticalTextPosition(JLabel.BOTTOM);
//		player1Hits.setHorizontalAlignment(JLabel.CENTER);
//		player1Hits.setVerticalAlignment(JLabel.BOTTOM);
//		cnstrnts.gridx = 0;
//		cnstrnts.gridy = VSHIFTER + 1;
//		gridBag.setConstraints(player1Hits, cnstrnts);
//		p.add(player1Hits);
//
//		// icon to indicate the number of pieces white has
//		player2Hits = new JLabel("PC: 0");
//		player2Hits.setHorizontalTextPosition(JLabel.CENTER);
//		player2Hits.setVerticalTextPosition(JLabel.BOTTOM);
//		player2Hits.setHorizontalAlignment(JLabel.CENTER);
//		player2Hits.setVerticalAlignment(JLabel.BOTTOM);
//		cnstrnts.gridx = 1;
//		cnstrnts.gridy = VSHIFTER + 1;
//		gridBag.setConstraints(player2Hits, cnstrnts);
//		p.add(player2Hits);
//
//		// Set icons for each JButton & add them to the arrayList
//		// of JButtons will write this after writing code for Board
//		for (int x = 0; x < 10; x++) {
//			for (int y = 3; y < 13; y++) {
//				JButton button = new JButton();
//				// if (gameBoard.getPlyr(x, y - 1) == 1) {
//				// button.setIcon();
//				// buttons.add(button);
//				// } else if (gameBoard.getPlyr(x, y - 1) == 2) {
//				// button.setIcon(whitePc);
//				// buttons.add(button);
//				// } else {
//				button.setIcon(blankSpace);
//				buttons.add(button);
//
//				// }
//				cnstrnts.gridx = x;
//				cnstrnts.gridy = y;
//				gridBag.setConstraints(button, cnstrnts);
//			}
//		}
//
//		// Set up the JButtons
//		for (JButton button : buttons) {
//			button.addActionListener(this);
//			button.setOpaque(true);
//			p.add(button);
//		}
//
//		// Make sure the GUI displays correctly when it pops up
//		p.setSize(2500, 2500);
//		f.setPreferredSize(new Dimension(700, 700));
//		p.setVisible(true);
//		f.add(p);
//		f.pack();
//		f.setVisible(true);
//
//	}
//
//	@Override
//	/*
//	 * Used to catch any actions that are performed on the GUI
//	 */
//	public void actionPerformed(final ActionEvent e) {
//		/*
//		 * x and y are used to hold the column and row position on the board z is used
//		 * so that x and y don't increment when the button loop is, for one of the top
//		 * buttons such as new game.
//		 */
//		int x = 0, y = 0, z = 0;
//
//		// for loops to check & see which button was pushed
//		for (JButton button : buttons) {
//			// check the source of the button
//			if (e.getSource() == button) {
//
//				if (z == 0)
//					// FIXME Add New Game
//					System.out.println("Fixme");
//				else if (z == 1) {
//					bstShip = true;
//					bShip = false;
//					mShip = false;
//					sShip = false;
//					System.out.println("got in");
//				} else if (z == 2) {
//					bstShip = false;
//					bShip = true;
//					mShip = false;
//					sShip = false;
//				} else if (z == 3) {
//					bstShip = false;
//					bShip = false;
//					mShip = true;
//					sShip = false;
//				} else if (z == 4) {
//					bstShip = false;
//					bShip = false;
//					mShip = false;
//					sShip = true;
//				} else {
//					if (bstShip || bShip || mShip || sShip) {
//						if (bstShip) {
//							if (aiGameBoard.isValidHorizontalShipMove("biggest ship", x, y)) {
//								System.out.println("biggest ship");
//							}
//						} else if (bShip) {
//							if (aiGameBoard.isValidHorizontalShipMove("big ship", x, y))
//								System.out.println("yes!");
//						} else if (mShip) {
//							if (aiGameBoard.isValidHorizontalShipMove("medium ship", x, y))
//								System.out.println("yes!");
//						} else if (sShip) {
//							if (aiGameBoard.isValidHorizontalShipMove("small ship", x, y))
//								System.out.println("yes!");
//						}
//					}
//				}
//
//				System.out.println("x" + x + "y" + y);
//			}
//
////increment x and y if the loop is
////passed the top buttons
//			if (z > NUMLABELS - 1) {
//				if (y == 9) {
//					// hi
//					y = 0;
//					x++;
//				} else {
//					y++;
//				}
//			}
////increment z, z tracks which button we are on
////(out of the total buttons, not just the board)
//			z++;
//		}
//	}
//
////		drawBoard();
//
////		//Make sure the next player can move
////		if (!canMove()) {
////			//If player can't move then toggle player again
////			gameBoard.nextPlyr();
////			//check if both players can't move
////			if (!canMove()) {
////				if (gameBoard.getWinner() == 1) {
////					JOptionPane.showMessageDialog(f, 
////							"\nThis win goes to Black!");
////				}
////				else if(gameBoard.getWinner() == -1)
////					JOptionPane.showMessageDialog(f, 
////							"Oh my, oh my\nLooks like we have a tie!");
////				else
////					JOptionPane.showMessageDialog(f, 
////							"\nThe win goes to White!");
////
////				gameBoard.newGame();
////			}
////			else if(getCurrPlyr() == 1)
////				JOptionPane.showMessageDialog(f, 
////						"No available moves for white");
////			else
////				JOptionPane.showMessageDialog(f, 
////						"No available moves for black");
////		}
////
////		//draw the board to reflect any current player changes
////		drawBoard();
////
////	}
//
//	/**
//	 * Draw the board by updating the icons based on which player is where in the
//	 * board array.
//	 */
//	private void drawBoard() {
//		/*
//		 * x and y are used to hold the x and y position on the board z is used so that
//		 * x and y don't increment when the button loop is for one of the top buttons
//		 * such as New Game
//		 */
//		int x = 0, y = 0, z = 0;
//		// Go through each button
//		for (JButton button : buttons) {
//			// Make sure it's not on the top buttons
//			if (z > NUMLABELS - 1) {
//				// set the appropriate icon
//				if (aiGameBoard.getPlyr(x, y) == 0) {
//					button.setIcon(blankSpace);
//				}
//				if (y == 7) {
//					y = 0;
//					x++;
//				} else {
//					y++;
//				}
//			}
//			z++;
//		}
//
//		// Set the current player on the board
//		String player;
//		if (aiGameBoard.getCurrPlyr() == 1) {
//			player = "Black";
//		} else {
//			player = "White";
//		}
//		// player1Hits.setText("Black: " + gameBoard.player1Score());
//		// player2Hits.setText("White: " + gameBoard.player2Score());
//	}
//}
