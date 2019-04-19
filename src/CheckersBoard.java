import java.util.Vector;
import java.awt.*;
/**
 * @author Chandler Scott, Sam Ventocilla, Allen Huric
 * This is the class that contains logic for board portion of checkers
 * board. 
 * 
 * 
 * @version 1.0
 */
public class CheckersBoard {
    public static final int rows = 8;
    public static final int cols = 8;
    private CheckersSquare[][] squares;

	  /** Created by Alen, Sam, Chandler */
	/**
 * This is the constructor for Checkers Board class.
 */
    public CheckersBoard() {			
    	squares = new CheckersSquare[rows][cols]; 	
	boolean lastColor = false;		
    	for (int i = 0; i < rows; i++) {
    		for (int j = 0; j < cols; j++) {
    			if (lastColor) {
					squares[i][j] = new CheckersSquare(CheckersSquare.BackgroundColor.DARK, i, j);
				} else {
					squares[i][j] = new CheckersSquare(CheckersSquare.BackgroundColor.LIGHT, i, j);
				}
    			lastColor = !lastColor;
    		}
    		lastColor = !lastColor;
    	}
    }
	/**
 * Method to make sure that the move is within the bounds of the board.
 * @param row variable for the rows of the board.
 * @param col variable for the columns of the board.
 * @return true or false based on bounds.
 */
	  /** Created by Alen, Sam, Chandler */
    public static boolean inBounds(int row, int col) {
    	if (row >= 0 && row < rows &&
    		col >= 0 && col < cols) {	
			return true;
		} else {
			return false;
		}
    }
	/**
 * Method acts as a getter for the square on the board.
 * @param row variable for the rows of the board.
 * @param col variable for the columns of the board.
 * @return
 */
	  /** Created by Alen, Sam, Chandler */
    public CheckersSquare getSquare(int row, int col) {
        if (inBounds(row, col)) {
        	return squares[row][col];
		} else {
			return null;	
		}
    }
    	  /** Created by Alen, Sam, Chandler */
	    /**
     * Method to initialize each piece on the board with the
     * appropriate color.
     */
    public void initializePieces() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 8; col++) {
				if (getSquare(row, col).getBackgroundColor() == CheckersSquare.BackgroundColor.DARK) {
					getSquare(row,col).setPiece(new CheckersPiece(Color.RED, row, col));
				}
			}
		}
		for (int row = 5; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (getSquare(row, col).getBackgroundColor() == CheckersSquare.BackgroundColor.DARK) {
					getSquare(row,col).setPiece(new CheckersPiece(Color.BLACK, row, col));
				}
			}
		}
	}
	
		  /** Created by Alen, Sam, Chandler */
		/**
	 * Method for moving the black and red pieces on the board.
	 * @param from variable for initial position moved from.
	 * @param to variable for desired position to move to.
	 * @return returns true or false based on moved piece.
	 */
	public boolean move(CheckersSquare from, CheckersSquare to) {
		boolean jumpPerformed = false;
		CheckersPiece beingMoved = from.getPiece();
		int oldRow = from.getRow(), newRow = to.getRow();
		int oldCol = from.getCol(), newCol = to.getCol();
		from.setPiece(null);
		beingMoved.setRowCol(to.getRow(), to.getCol());
		to.setPiece(beingMoved);
		if (Math.abs(oldRow - newRow) > 1 || Math.abs(oldCol - newCol) > 1) {
			int takeRow = (oldRow + newRow) / 2;
			int takeCol = (oldCol + newCol) / 2;
			CheckersSquare takeSquare = getSquare(takeRow, takeCol);
			takeSquare.setPiece(null);
			takeSquare.update(takeSquare.getGraphics());
			jumpPerformed = true;
		}
		from.update(from.getGraphics());
		to.update(to.getGraphics());
		return jumpPerformed;
	}
	  /** Created by Alen, Sam, Chandler */
		/**
	 * Method to check possible moves for the pieces.
	 * @param p variable that represents the pieces.
	 * @return the possible moves that the specific piece.
	 */
	public Vector<CheckersSquare> possibleMoves(CheckersPiece p) {
		Vector<CheckersSquare> possibleMoves = new Vector<CheckersSquare>();
		Color pColor = p.getColor();
		int row = p.getRow();
		int col = p.getCol();

		if (CheckersBoard.inBounds(row+1, col+1) && pColor == Color.RED) {
			if (!this.getSquare(row+1, col+1).hasPiece()) {
				possibleMoves.add(this.getSquare(row+1, col+1));
			} else {
				if (CheckersBoard.inBounds(row+2, col+2)) {
					if (!this.getSquare(row+2, col+2).hasPiece() && 
						(this.getSquare(row+1, col+1).getPiece().getColor() != pColor)) {
						possibleMoves.add(this.getSquare(row+2, col+2));
					}
				}
			}
		}
	
		if (CheckersBoard.inBounds(row+1, col-1) && pColor == Color.RED) {
			if(!this.getSquare(row+1, col-1).hasPiece()) {
				possibleMoves.add(this.getSquare(row+1, col-1));
			} else {
				if (CheckersBoard.inBounds(row+2, col-2)) {
					if (!this.getSquare(row+2, col-2).hasPiece() && 
						(this.getSquare(row+1, col-1).getPiece().getColor() != pColor)) {
						possibleMoves.add(this.getSquare(row+2, col-2));
					}
				}
			}
		}

		if (CheckersBoard.inBounds(row-1, col-1) && pColor == Color.BLACK) {
			
			if (!this.getSquare(row-1, col-1).hasPiece()) {
				possibleMoves.add(this.getSquare(row-1, col-1));
			}
			else {
				if (CheckersBoard.inBounds(row-2, col-2)) {
					if (!this.getSquare(row-2, col-2).hasPiece() &&
						(this.getSquare(row-1, col-1).getPiece().getColor() != pColor)) {	
						possibleMoves.add(this.getSquare(row-2, col-2));
					}
				}
			}
		}
		
		if (CheckersBoard.inBounds(row-1, col+1) && pColor == Color.BLACK) {
			if (!this.getSquare(row-1, col+1).hasPiece()) {
				possibleMoves.add(this.getSquare(row-1, col+1));
			}
			else {
				if (CheckersBoard.inBounds(row-2, col+2)) {
					if (!this.getSquare(row-2, col+2).hasPiece() && 
						(this.getSquare(row-1, col+1).getPiece().getColor() != pColor)) {
						possibleMoves.add(this.getSquare(row-2, col+2));
					}
				}
			}
		}

		return possibleMoves;
	}
	  /** Created by Alen, Sam, Chandler */
		/**
	 * Method to highlight the possible moves found on piece.
	 * @param p variable to represent the piece. 
	 * @param doHighlight a flag to highlight available spaces or not. 
	 */
	public void highlightMoves(CheckersPiece p, boolean doHighlight) {
		
		Vector<CheckersSquare> possibleMoves = possibleMoves(p);
		
		if (doHighlight) {
			for (CheckersSquare highlight : possibleMoves) {
				highlight.setHighlight(true);
			}
		}
		
		else {
			for (CheckersSquare highlight : possibleMoves) {
				highlight.setHighlight(false);
			}
		}
	}
}
