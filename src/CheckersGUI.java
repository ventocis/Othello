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



public class CheckersGui implements MouseListener,
									ActionListener {
	private JPanel boardPanel;
	private JFrame frame;
	private JMenuItem startGame;
	private JMenuBar menuBar;
	private JMenuItem quit;
	private JMenu menu;

	private Color turn;
	
	private final int borderWidth = 1;
	
	private CheckersBoard CheckersBoard;
	
	private int blackCheckersRemaining;
	private int redCheckersRemaining;
	private CheckersSquare selSquare;
	private JLabel piecesText;
	
	public void InitializeGUI() {
		frame = new JFrame("CheckersGui Frame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		
		frame.getContentPane().setLayout(
				new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		
		boardPanel = new JPanel(new GridLayout(8, 8));
		boardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		CheckersBoard = new CheckersBoard();
		CheckersBoard.initializePieces();
		
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

		setBoard(CheckersBoard, boardPanel);
		frame.add(boardPanel);
		frame.add(piecesText);
		frame.setJMenuBar(menuBar);
		frame.pack();
		
		Rectangle boundingRect = frame.getBounds();
		frame.setBounds(boundingRect.x, boundingRect.y, boundingRect.width + 5, boundingRect.height);
		frame.setVisible(true);
	}	
	
	public CheckersGui() {
		InitializeGUI();
		
		turn = Color.GREEN;
		
		redCheckersRemaining = 12;
		blackCheckersRemaining = 12;
		
		updateGameStatus();
	}

		
	@Override
	public void mouseClicked(MouseEvent e) {
		CheckersSquare sel = (CheckersSquare)e.getComponent();

		if(sel.hasPiece()) 
			if(sel.getPiece().getColor() != turn &&
				turn != Color.GREEN) {
			piecesText.setText("Ash! This isn't the time to use that!");
			return;
		}
		
		if (sel.hasPiece() && selSquare == null) {
			selSquare = sel;
			selSquare.setHighlight(true);
			CheckersBoard.highlightMoves(selSquare.getPiece(), true);
			return;
		} else if (sel.hasPiece() && !sel.equals(selSquare)) {
			selSquare.setHighlight(false);
			CheckersBoard.highlightMoves(selSquare.getPiece(), false);
			
			selSquare = sel;
			selSquare.setHighlight(true);
			CheckersBoard.highlightMoves(selSquare.getPiece(), true);
			return;
			
		} else if (sel.equals(selSquare)) {
			selSquare.setHighlight(false);
			CheckersBoard.highlightMoves(selSquare.getPiece(), false);
			selSquare = null;
		} else if (!sel.hasPiece() && selSquare != null) {
			boolean found = false;
			boolean jumped = false;
			
			Vector<CheckersSquare> oldPossibleMoves = CheckersBoard.possibleMoves(selSquare.getPiece());
			
			for (CheckersSquare choice : oldPossibleMoves) {
				if (choice.equals(sel)) {
					if (turn == Color.GREEN)
						turn = selSquare.getPiece().getColor();
					jumped = CheckersBoard.move(selSquare, sel);
					found = true;
				}
			}
			
			if (found) {
				if (jumped) {
					if (turn == Color.BLACK) {
						redCheckersRemaining--;
					}
					else {
						blackCheckersRemaining--;
					}
				}
				
				selSquare.setHighlight(false);
				for (CheckersSquare unhighlight : oldPossibleMoves) {
					unhighlight.setHighlight(false);
				}
				selSquare = null;
				
				nextTurn();
				updateGameStatus();
				
				String winningStr = getWinner();
				if (winningStr != null) {
					int restart = JOptionPane.showConfirmDialog(null, winningStr + " Do you want to begin a new game?", "New Game?", JOptionPane.YES_NO_OPTION);
					
					if (restart == JOptionPane.YES_OPTION) {
						resetGame();
					}
					else {
						frame.setVisible(false);
						frame.dispose();
					}
				}
			}
			else if (!found) {
				piecesText.setText("You are able to move");
			}
		}
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startGame) {
			resetGame();
		}
		else if(e.getSource() == quit) {
			frame.setVisible(false);
			frame.dispose();
		}
		
	}
		
	public void resetGame() {
		frame.setVisible(false);
		selSquare = null;
		frame.remove(boardPanel);
		boardPanel = new JPanel(new GridLayout(8, 8));
		boardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		CheckersBoard = new CheckersBoard();
		CheckersBoard.initializePieces();
		
		setBoard(CheckersBoard, boardPanel);
		frame.add(boardPanel, 0);
		redCheckersRemaining = 12;
		blackCheckersRemaining = 12;
		turn = Color.BLACK;
		
		updateGameStatus();
		frame.pack();
		frame.setVisible(true);
	}

	public void setBoard(CheckersBoard b, JPanel p) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				CheckersSquare sq = b.getSquare(i, j);
				sq.addMouseListener(this);
				
				JPanel ContainerPanel = new JPanel(new FlowLayout());
				ContainerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,
																					borderWidth));
				ContainerPanel.add(sq);
				if (sq.getBackgroundColor() == CheckersSquare.BackgroundColor.DARK) {
					ContainerPanel.setBackground(Color.DARK_GRAY);
				}
				else {
					ContainerPanel.setBackground(Color.LIGHT_GRAY);
				}
				p.add(ContainerPanel);
			}
		}
	}
	
	public void updateGameStatus() {
		piecesText.setText("Red Pieces Left: " + redCheckersRemaining + "             Black Pieces Left: " + blackCheckersRemaining);
	}
	
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
				if (CheckersBoard.getSquare(i, j).hasPiece()) {
					Vector<CheckersSquare> potentialMoves = CheckersBoard.possibleMoves(CheckersBoard.getSquare(i, j).getPiece());
					if (!potentialMoves.isEmpty()) {
						if (CheckersBoard.getSquare(i, j).getPiece().getColor() == Color.black) {
							blackCanMove = true;
						}
						else {
							redCanMove = true;
						}
					}
				}
			}
		}
		
		if (redCanMove && !blackCanMove) {
			return "Red team wins since Black team can make no more moves!";
		}
		else if (blackCanMove && !redCanMove) {
			return "Black team wins since Red team can make no more moves!";
		}
		else if (!redCanMove && !blackCanMove) {
			return "Neither side wins!";
		}
		
		return null;
	}
	
	public void nextTurn() {
		if(turn == Color.BLACK) {
			turn = Color.RED;
		}
		else {
			turn = Color.BLACK;
		}
	}

	public static void main(String[] args) {
		new CheckersGui();
	}
}
