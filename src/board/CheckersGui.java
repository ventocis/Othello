package board;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

/**
 * @author Chandler Scott, Sam Ventocilla, Allen Huric This is the class that
 *         contains GUI logic checkers board.
 * 
 * @version 1.0
 */
public class CheckersGui implements MouseListener, ActionListener {
	/**
	 * Jpanel variable.
	 */
	private JPanel boardPanel;
	/**
	 * JFrame variable.
	 */
	private JFrame frame;
	/**
	 * Variable for menu item.
	 */
	private JMenuItem startGame;
	/**
	 * Variable for menu bar.
	 */
	private JMenuBar menuBar;
	/**
	 * Variable for quit menu item.
	 */
	private JMenuItem quit;
	/**
	 * Variable for the JMenu.
	 */
	private JMenu menu;
	/**
	 * Variable for current color.
	 */
	private Color turn;
	/**
	 * Variable for border width.
	 */
	private static final int BORDERWIDTH = 1;
	/**
	 * Variable for the board.
	 */
	private CheckersBoard checkersBoard;
	/**
	 * Variable for remaining black pieces.
	 */
	private int blackCheckersRemaining;
	/**
	 * Variable for remaining red pieces.
	 */
	private int redCheckersRemaining;
	/**
	 * Variable for the selected square.
	 */
	private CheckersSquare selSquare;
	/**
	 * Variable for JLabel piece text.
	 */
	private JLabel piecesText;

	/**
	 * Method to initialize the GUI of the board.
	 */
	void initializeGui() {
		frame = new JFrame("CheckersGui Frame");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		frame.getContentPane().setLayout(new 
				BoxLayout(frame.getContentPane(),
						BoxLayout.Y_AXIS));

		boardPanel = new JPanel(new GridLayout(8, 8));
		boardPanel.setBorder(
				BorderFactory.createLineBorder(Color.BLACK));
		checkersBoard = new CheckersBoard();
		checkersBoard.initializePieces();
		piecesText = new JLabel(" ");
		piecesText.setHorizontalTextPosition(JLabel.LEFT);
		piecesText.setVerticalTextPosition(JLabel.BOTTOM);
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		startGame = new JMenuItem("New Game");
		startGame.addActionListener(this);
		quit = new JMenuItem("Quit");
		quit.addActionListener(this);
		menu.add(startGame);
		menu.add(quit);
		menuBar.add(menu);
		setBoard(checkersBoard, boardPanel);
		frame.add(boardPanel);
		frame.add(piecesText);
		frame.setJMenuBar(menuBar);
		frame.pack();
		Rectangle boundingRect = frame.getBounds();
		frame.setBounds(boundingRect.x,
				boundingRect.y, boundingRect.width + 5,
				boundingRect.height);
		frame.setVisible(true);
	}

	/**
	 * Constructor for the GUI of checkers.
	 */
	public CheckersGui() {
		initializeGui();
		turn = Color.GREEN;
		redCheckersRemaining = 12;
		blackCheckersRemaining = 12;
		updateGameStatus();
	}

	@Override
	/**
	 * Method to register mouse clicker events.
	 */
	public void mouseClicked(final MouseEvent e) {
		CheckersSquare sel = (CheckersSquare) e.getComponent();
		if (sel.hasPiece()) {
			if (sel.getPiece().getColor() 
					!= turn && turn != Color.GREEN) {
				piecesText.setText("Can't do that...");
				return;
			}
		}
		if (sel.hasPiece() && selSquare == null) {
			selSquare = sel;
			selSquare.setHighlight(true);
			checkersBoard.highlightMoves(selSquare.getPiece(),
					true);
			return;
		} else if (sel.hasPiece() && !sel.equals(selSquare)) {
			selSquare.setHighlight(false);
			checkersBoard.highlightMoves(selSquare.getPiece(),
					false);
			selSquare = sel;
			selSquare.setHighlight(true);
			checkersBoard.highlightMoves(selSquare.getPiece(),
					true);
			return;

		} else if (sel.equals(selSquare)) {
			selSquare.setHighlight(false);
			checkersBoard.highlightMoves(selSquare.getPiece(),
					false);
			selSquare = null;
		} else if (!sel.hasPiece() && selSquare != null) {
			boolean found = false;
			boolean jumped = false;
			Vector<CheckersSquare> oldPossibleMoves 
			= checkersBoard.possibleMoves(selSquare.getPiece());
			for (CheckersSquare choice : oldPossibleMoves) {
				if (choice.equals(sel)) {
					if (turn == Color.GREEN) {
						turn = selSquare.
								getPiece().
								getColor();
					jumped = checkersBoard.
							move(selSquare, sel);
					found = true;
					}
				}
			}
			if (found) {
				if (jumped) {
					if (turn == Color.BLACK) {
						redCheckersRemaining--;
					} else {
						blackCheckersRemaining--;
					}
				}
				selSquare.setHighlight(false);
				for (CheckersSquare unhighlight 
						: oldPossibleMoves) {
					unhighlight.setHighlight(false);
				}
				selSquare = null;
				nextTurn();
				updateGameStatus();
				String winningStr = getWinner();
				if (winningStr != null) {
					int restart 
					= JOptionPane.
					showConfirmDialog(null,
							winningStr 
							+ " Do you want"
							+ " to begin"
							+ " a new"
							+ " game?",
							"New Game?",
							JOptionPane.
							YES_NO_OPTION);

					if (restart == JOptionPane.YES_OPTION) {
						resetGame();
					} else {
						frame.setVisible(false);
						frame.dispose();
					}
				}
			} else if (!found) {
				piecesText.setText("You are able to move");
			}
		}
	}

	/**
	 * The mouse event gets entered.
	 * @param e mouse entered.
	 */
	public void mouseEntered(final MouseEvent e) {
	}

	/**
	 * The mouse event gets exited.
	 * @param e mouse exited.
	 */
	public void mouseExited(final MouseEvent e) {
	}

	/**
	 * The mouse is pressed.
	 * @param e mouse pressed.
	 */
	public void mousePressed(final MouseEvent e) {
	}

	/**
	 * The mouse event is released.
	 * @param e mouse event.
	 */
	public void mouseReleased(final MouseEvent e) {
	}

	/**
	 * Method to register an action performed from and action event.
	 * @param e event.
	 */
	public void actionPerformed(final ActionEvent e) {
		if (e.getSource() == startGame) {
			resetGame();
		} else if (e.getSource() == quit) {
			frame.setVisible(false);
			frame.dispose();
		}

	}

	/**
	 * Method to reset the game if chosen to do so.
	 */
	public void resetGame() {
		frame.setVisible(false);
		selSquare = null;
		frame.remove(boardPanel);
		boardPanel = new JPanel(new GridLayout(8, 8));
		boardPanel.setBorder(BorderFactory.createLineBorder(
				Color.BLACK));
		checkersBoard = new CheckersBoard();
		checkersBoard.initializePieces();

		setBoard(checkersBoard, boardPanel);
		frame.add(boardPanel, 0);
		redCheckersRemaining = 12;
		blackCheckersRemaining = 12;
		turn = Color.BLACK;

		updateGameStatus();
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Method that sets the Board to its proper logic.
	 * 
	 * @param b variable that represents the board.
	 * @param p variable that represents the panel.
	 */
	public void setBoard(final CheckersBoard b,  final JPanel p) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				CheckersSquare sq = b.getSquare(i, j);
				sq.addMouseListener(this);
				JPanel containerPanel 
				= new JPanel(new
						FlowLayout());
				containerPanel.
				setBorder(BorderFactory.
						createLineBorder(Color.BLACK,
						BORDERWIDTH));
				containerPanel.add(sq);
				if (sq.getBackgroundColor() 
						== CheckersSquare.
						BackgroundColor.DARK) {
					containerPanel.
					setBackground(Color.DARK_GRAY);
				} else {
					containerPanel.
					setBackground(Color.LIGHT_GRAY);
				}
				p.add(containerPanel);
			}
		}
	}

	/**
	 * Method to update the user on current status of pieces.
	 */
	public void updateGameStatus() {
		piecesText.setText("Red Pieces"
				+ " Left: " 
				+ redCheckersRemaining 
				+ "             Black Pieces Left: "
				+ blackCheckersRemaining);
	}

	/**
	 * Method to reveal the winner of the game.
	 * 
	 * @return returns the winner or the game.
	 */
	public String getWinner() {

		if (blackCheckersRemaining == 0) {
			return "Red has won by taking Black's pieces!";
		}

		if (redCheckersRemaining == 0) {
			return "Black has won by taking Red's pieces!";
		}

		boolean redCanMove = false;
		boolean blackCanMove = false;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (checkersBoard.getSquare(i, j).hasPiece()) {
					Vector<CheckersSquare> potentialMoves =
							checkersBoard
							.possibleMoves(
							checkersBoard
						.getSquare(i, j).getPiece());
					if (!potentialMoves.isEmpty()) {
						if (checkersBoard
								.getSquare(i, j)
								.getPiece()
								.getColor() 
								== 
								Color.black) {
							blackCanMove = true;
						} else {
							redCanMove = true;
						}
					}
				}
			}
		}
		if (redCanMove && !blackCanMove) {
			return "Red team wins since"
					+ " Black team can make no more moves!";
	} else if (blackCanMove && !redCanMove) {
			return "Black team wins since"
					+ " Red team can make no more moves!";
		} else if (!redCanMove && !blackCanMove) {
			return "Neither side wins!";
		}

		return null;
	}



	/**
	 * Method to go to the next players turn.
	 */
	public void nextTurn() {
		if (turn == Color.BLACK) {
			turn = Color.RED;
		} else {
			turn = Color.BLACK;
		}
	}
}
