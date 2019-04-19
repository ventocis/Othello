package board;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Chandler Scott, Sam Ventocilla, Allen Huric This is the class that
 *         contains individual square logic checkers board.
 * 
 * @version 1.0
 */
@SuppressWarnings("serial")
public class CheckersSquare extends Canvas {
	/**
	 * Enums for background color.
	 * @author Owner
	 *
	 */
	public enum BackgroundColor {
		/**
		 * Colors for background.
		 */
		LIGHT, DARK
	};
	/**
	 * Variable for background color.
	 */
	private BackgroundColor bgColor;
	/**
	 * Variable for is the piece there.
	 */
	private boolean isPieceExisting;
	/**
	 * Variable for the checkers piece.
	 */
	private CheckersPiece checkersPiece;
	/**
	 * Variable for the rows.
	 */
	private int row;
	/**
	 * Variable for the columns.
	 */
	private int col;

	/**
	 * Constructor for the square class.
	 * 
	 * @param c   variable for the background color of each square.
	 * @param row variable representing the row for each square.
	 * @param col variable representing the column for each square.
	 */
	public CheckersSquare(final BackgroundColor c,
			final int row, final int col) {

		this.setSize(64, 64);

		if (c == BackgroundColor.DARK) {
			this.setBackground(Color.DARK_GRAY);
		} else {
			this.setBackground(Color.LIGHT_GRAY);
		}
		bgColor = c;
		isPieceExisting = false;
		checkersPiece = null;

		this.row = row;
		this.col = col;

	}

	/**
	 * Method to check if square contains piece.
	 * 
	 * @return returns true if has a piece.
	 */
	public boolean hasPiece() {
		return this.isPieceExisting;
	}

	/**
	 * Method to return row of square.
	 * 
	 * @return returns the row location.
	 */
	public int getRow() {
		return this.row;
	}

	/**
	 * Method to return the column of square.
	 * 
	 * @return returns the column location.
	 */
	public int getCol() {
		return this.col;
	}

	/**
	 * Method to return the background color of the square.
	 * 
	 * @return returns the current color.
	 */
	public CheckersSquare.BackgroundColor getBackgroundColor() {
		return this.bgColor;
	}

	/**
	 * Method to get the piece on the square.
	 * 
	 * @return returns the piece.
	 */
	public CheckersPiece getPiece() {
		if (this.hasPiece()) {
			return this.checkersPiece;
		}
		return null;
	}

	/**
	 * Method to change the color of the square to its proper color.
	 * @param g is the graphics variable.
	 */
	public void paint(final Graphics g) {
		if (this.getBackgroundColor() 
				== CheckersSquare.BackgroundColor.DARK) {
			this.setBackground(Color.DARK_GRAY);
		} else {
			this.setBackground(Color.LIGHT_GRAY);
		}

		if (this.hasPiece()) {
			g.setColor(checkersPiece.getColor());
			g.fillOval(5, 5, 54, 54);
		} else {
			g.clearRect(0, 0, 64, 64);
		}
	}

	/**
	 * Sets the piece to its valid location.
	 * 
	 * @param visitor acts as placeholder if piece is there.
	 */
	public void setPiece(final CheckersPiece visitor) {
		if (visitor != null) {
			this.checkersPiece = visitor;
			this.isPieceExisting = true;
		} else {
			this.checkersPiece = null;
			this.isPieceExisting = false;
		}
	}

	/**
	 * Sets the highlight color of the square when used.
	 * 
	 * @param doHighlight flag for performing the highlight.
	 */
	public void setHighlight(final boolean doHighlight) {

		Graphics g = this.getGraphics();

		if (doHighlight) {

			if (!this.hasPiece()) {

				g.setColor(Color.BLACK);

				for (int i = 0; i < 360; i += 30) {
					g.drawArc(5, 5, 54, 54, i, 15);
				}
			} else {
				g.setColor(Color.YELLOW);
				g.draw3DRect(0, 0, 63, 63, false);
			}
		} else {
			super.update(this.getGraphics());
		}
	}
}
