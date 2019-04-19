package board;

import java.awt.Color;

/**
 * @author Chandler Scott, Sam Ventocilla, Allen Huric This is the class that
 *         contains individual piece logic checkers board.
 * 
 * @version 1.0
 */
public class CheckersPiece {
	/**
	 * Rows for piece.
	 */

	private int row;
	/**
	 * Columns for piece.
	 */
	private int col;
	/**
	 * Color for piece.
	 */
	private Color color;

	/**
	 * Constructor for the Checkers piece class.
	 * 
	 * @param color variable for the color of the piece.
	 * @param row   variable for the row on the board.
	 * @param col   variable for the column on the board.
	 */
	public CheckersPiece(final Color color, final int row, final int col) {
		this.color = color;
		this.row = row;
		this.col = col;
	}

	/**
	 * Method to get the row of the board.
	 * 
	 * @return gets the variable row.
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Method to get the color of the piece on the board.
	 * 
	 * @return gets the variable color for the piece.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Method to get the column of the board.
	 * 
	 * @return the column of the board.
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Method to set the row and column for each piece.
	 * 
	 * @param row variable for the row on board.
	 * @param col variable for the column on board.
	 */
	public void setRowCol(final int row, final int col) {
		this.row = row;
		this.col = col;
	}

	/**
	 * Simple to string method that converts various values to 
	 * string in order to display status.
	 * 
	 * @return changes to a string.
	 */
	public String toString() {

		StringBuilder s = new StringBuilder();

		if (this.color == Color.BLACK) {
			s.append("BLACK ");
		} else {
			s.append("RED ");
		}

		s.append("CheckersPiece is at row " 
		+ Integer.toString(this.getRow()) + ", col ");

		return s.toString();
	}
}
