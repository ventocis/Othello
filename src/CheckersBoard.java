B
import java.awt.Color;
import java.util.Vector;

/**
 * @author Chandler Scott, Sam Ventocilla, Allen Huric This is the class that
 *         contains logic for board portion of checkers board.
 * 
 * 
 * @version 1.0
 */
public class CheckersBoard {
	/**
	 * Variable for rows.
	 */
	public static final int ROWS = 8;
	/**
	 * Variable for columns.
	 */
	public static final int COLS = 8;
	/**
	 * 2 dimensional array of squares for board.
	 */
	private CheckersSquare[][] squares;

	/**
	 * This is the constructor for Checkers Board class.
	 */
	public CheckersBoard() {
		squares = new CheckersSquare[ROWS][COLS];
		boolean lastColor = false;
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (lastColor) {
					squares[i][j] 
							= new 
					CheckersSquare(CheckersSquare
						.BackgroundColor.DARK, i, j);
				} else {
					squares[i][j] 
							= new 
					CheckersSquare(CheckersSquare
						.BackgroundColor.LIGHT, i, j);
				}
				lastColor = !lastColor;
			}
			lastColor = !lastColor;
		}
	}

	/**
	 * Method to make sure that the move is within the bounds of the board.
	 * 
	 * @param row variable for the rows of the board.
	 * @param col variable for the columns of the board.
	 * @return true or false based on bounds.
	 */
	/** Created by Alen, Sam, Chandler */
	public static boolean inBounds(int row, int col) {
		if (row >= 0 && row < rows && col >= 0 && col < cols) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method to make sure that the move is within the bounds of the board.
	 * 
	 * @param row variable for the rows of the board.
	 * @param col variable for the columns of the board.
	 * @return true or false based on bounds.
	 */
	public static boolean inBounds(final int row, final int col) {
		return row >= 0 
				&& row < ROWS 
				&& col >= 0 
				&& col < COLS;
	}

	/**
	 * Method acts as a getter for the square on the board.
	 * 
	 * @param row variable for the rows of the board.
	 * @param col variable for the columns of the board.
	 * @return returns the square.
	 */
	public CheckersSquare getSquare(final int row, final int col) {
		if (inBounds(row, col)) {
			return squares[row][col];
		} else {
			return null;
		}
	}

	/**
	 * Method to initialize each piece on the 
	 * board with the appropriate color.
	 */
	public void initializePieces() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 8; col++) {
				if (getSquare(row, col)
						.getBackgroundColor() 
						== CheckersSquare
						.BackgroundColor.DARK) {
					getSquare(row, col)
					.setPiece(new 
							CheckersPiece(Color.RED,
							row, col));
				}
			}
		}
		for (int row = 5; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (getSquare(row, col)
						.getBackgroundColor()
						== CheckersSquare
						.BackgroundColor.DARK) {
					getSquare(row, col)
					.setPiece(new 
						CheckersPiece(Color.BLACK,
							row, col));
				}
			}
		}
	}

	/**
	 * Method for moving the black and red pieces on the board.
	 * 
	 * @param from variable for initial position moved from.
	 * @param to   variable for desired position to move to.
	 * @return returns true or false based on moved piece.
	 */
	public boolean move(final CheckersSquare from,
			final CheckersSquare to) {
		boolean jumpPerformed = false;
		CheckersPiece beingMoved = from.getPiece();
		int oldRow = from.getRow(), newRow = to.getRow();
		int oldCol = from.getCol(), newCol = to.getCol();
		from.setPiece(null);
		beingMoved.setRowCol(to.getRow(), to.getCol());
		to.setPiece(beingMoved);
		if (Math.abs(oldRow - newRow) > 1 
				|| Math.abs(oldCol - newCol) > 1) {
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

	/**
	 * Method to check possible moves for the pieces.
	 * 
	 * @param p variable that represents the pieces.
	 * @return the possible moves that the specific piece.
	 */
	public Vector<CheckersSquare> possibleMoves(final CheckersPiece p) {
		Vector<CheckersSquare> possibleMoves 
		= new Vector<CheckersSquare>();
		Color pColor = p.getColor();
		int row = p.getRow();
		int col = p.getCol();

		if (CheckersBoard
				.inBounds(row + 1, col + 1) 
				&& pColor == Color.RED) {
			if (!this.getSquare(row + 1, col + 1).hasPiece()) {
				possibleMoves
				.add(this.getSquare(row + 1, col + 1));
			} else {
				if (CheckersBoard.inBounds(row + 2, col + 2)) {
					if (!this
					.getSquare(row + 2, col + 2).hasPiece()
				&& (this.getSquare(row + 1, col + 1)
						.getPiece()
						.getColor() != pColor)) {
						possibleMoves
						.add(this.getSquare(row + 2,
								col + 2));
					}
				}
			}
		}

		if (CheckersBoard
				.inBounds(row + 1,
						col - 1) 
				&& pColor == Color.RED) {
			if (!this.getSquare(row + 1,
					col - 1).hasPiece()) {
				possibleMoves
				.add(this.getSquare(row + 1, col - 1));
			} else {
				if (CheckersBoard.inBounds(row + 2, col - 2)) {
					if (!this
						.getSquare(row + 2, col - 2)
							.hasPiece()
						&& (this.getSquare(row + 1,
									col - 1)
								.getPiece(
					).getColor() != pColor)) {
						possibleMoves
						.add(this.getSquare(row + 2,
								col - 2));
					}
				}
			}
		}

		if (CheckersBoard
				.inBounds(row - 1,
						col - 1) 
				&& pColor == Color.BLACK) {

			if (!this.getSquare(row - 1, col - 1).hasPiece()) {
				possibleMoves
				.add(this.getSquare(row - 1, col - 1));
			} else {
				if (CheckersBoard.inBounds(row - 2, col - 2)) {
					if (!this
							.getSquare(row - 2,
									col - 2)
							.hasPiece()
						&& (this.getSquare(row - 1,
							col - 1).getPiece()
						.getColor() != pColor)) {
						possibleMoves
						.add(this.getSquare(row - 2,
								col - 2));
					}
				}
			}
		}

		if (CheckersBoard.inBounds(row - 1,
				col + 1) && pColor == Color.BLACK) {
			if (!this.getSquare(row - 1, col + 1).hasPiece()) {
				possibleMoves
				.add(this.getSquare(row - 1, col + 1));
			} else {
				if (CheckersBoard.inBounds(row - 2, col + 2)) {
					if (!this.getSquare(row - 2,
							col + 2).hasPiece()
						&& (this.getSquare(row - 1,
								col + 1)
								.getPiece()
								.getColor() 
								!= pColor)) {
						possibleMoves
						.add(this
						.getSquare(row - 2,
							col + 2));
					}
				}
			}
		}

		return possibleMoves;
	}

	/**
	 * Method to highlight the possible moves found on piece.
	 * 
	 * @param p           variable to represent the piece.
	 * @param doHighlight a flag to highlight available spaces or not.
	 */
	public void highlightMoves(final CheckersPiece p,
			final boolean doHighlight) {

		Vector<CheckersSquare> possibleMoves = possibleMoves(p);

		if (doHighlight) {
			for (CheckersSquare highlight : possibleMoves) {
				highlight.setHighlight(true);
			}
		} else {

			for (CheckersSquare highlight : possibleMoves) {
				highlight.setHighlight(false);
			}
		}
	}
}
