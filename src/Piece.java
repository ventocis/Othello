import java.awt.*;

public class Piece {
	
	private int row;
	private int col;
	public Color color;
	
	
	public Piece(Color color, int row, int col) {
		this.color = color;
		this.row = row;
		this.col = col;
	}
	
	public int getRow() {
		return row;
	}
	
	public Color getColor() {
		return color;
	}
	
	public int getCol() {
		return col;
	}
	
	public void setRowCol(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public String toString() {
		
		StringBuilder s = new StringBuilder();
		
		if (this.color == Color.BLACK) {
			s.append("BLACK ");
		} else {
			s.append("RED ");
		}

		s.append("piece is at row " + Integer.toString(this.getRow()) + 
				 ", col " + Integer.toString(this.getCol()));
		
		return s.toString();
	}
}
