package board;
/**
 * This is a class to get the score of a position.
 * @author Owner
 *
 */
public class Score {
	/**
	 * Variables for rows, columns, and points.
	 */
	private int row, col, points;
	/**
	 * Method to get the row.
	 * @return returns the row.
	 */
	public int getRow() {
		return row;
	}
	/**
	 * Method to set the row.
	 * @param row returns the set row.
	 */
	public void setRow(final int row) {
		this.row = row;
	}
	/**
	 * Method to get the col.
	 * @return returns the col.
	 */
	public int getCol() {
		return col;
	}
	/**
	 * Method to set the col.
	 * @param col returns the set col.
	 */
	public void setCol(final int col) {
		this.col = col;
	}
	/**
	 * Method to get the points.
	 * @return returns the points.
	 */
	public int getPoints() {
		return points;
	}
	/**
	 * Method to set the points.
	 * @param points set the value of points.
	 */
	public void setPoints(final int points) {
		this.points = points;
	}
	/**
	 * Acts as a constructor for the class. 
	 * @param r variable for the rows.
	 * @param c variable for the cols.
	 * @param p variable for the points.
	 */
	public Score(final int r, final int c, final int p) {
		row = r;
		col = c;
		points = p;
	}
}
