import java.util.Vector;
import java.awt.*;

public class Board {
    public static final int rows = 8;
    public static final int cols = 8;
    private Square[][] squares;

    public Board() {
    			
    	squares = new Square[rows][cols];
    	
		boolean lastcolor = false;
		
    	for (int i = 0; i < rows; i++) {
    		for (int j = 0; j < cols; j++) {
    			if (lastcolor) {
					squares[i][j] = new Square(Square.BackgroundColor.DARK, i, j);
				} else {
					squares[i][j] = new Square(Square.BackgroundColor.LIGHT, i, j);
				}
    			lastcolor = !lastcolor;
    		}
    		
    		lastcolor = !lastcolor;
    	}
    }

    public static boolean inBounds(int row, int col) {
    	if (row >= 0 && row < rows &&
    		col >= 0 && col < cols) {	
			return true;
		} else {
			return false;
		}
    }

    public Square getSquare(int row, int col) {
        if (inBounds(row, col)) {
        	return squares[row][col];
		} else {
			return null;	
		}
    }
    
    public void initializePieces() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 8; col++) {
				if (getSquare(row, col).getBackgroundColor() == Square.BackgroundColor.DARK) {
					getSquare(row,col).setPiece(new Piece(Color.RED, row, col));
				}
			}
		}
		
		for (int row = 5; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (getSquare(row, col).getBackgroundColor() == Square.BackgroundColor.DARK) {
					getSquare(row,col).setPiece(new Piece(Color.BLACK, row, col));
				}
			}
		}
	}
	
	
	public boolean move(Square from, Square to) {
		boolean jumpPerformed = false;
		
		Piece beingMoved = from.getPiece();
		
		int oldRow = from.getRow(), newRow = to.getRow();
		int oldCol = from.getCol(), newCol = to.getCol();
		
		from.setPiece(null);
		beingMoved.setRowCol(to.getRow(), to.getCol());
		to.setPiece(beingMoved);
		
		if (Math.abs(oldRow - newRow) > 1 || Math.abs(oldCol - newCol) > 1) {
			int takeRow = (oldRow + newRow) / 2;
			int takeCol = (oldCol + newCol) / 2;
			
			Square takeSquare = getSquare(takeRow, takeCol);
			takeSquare.setPiece(null);
			takeSquare.update(takeSquare.getGraphics());
		
			jumpPerformed = true;
		}
		
		from.update(from.getGraphics());
		to.update(to.getGraphics());
		
		return jumpPerformed;
	}

	public Vector<Square> possibleMoves(Piece p) {
		Vector<Square> possibleMoves = new Vector<Square>();
		Color pColor = p.getColor();
		
		int row = p.getRow();
		int col = p.getCol();

		if (Board.inBounds(row+1, col+1) && pColor == Color.RED) {
			if (!this.getSquare(row+1, col+1).hasPiece()) {
				possibleMoves.add(this.getSquare(row+1, col+1));
			} else {
				if (Board.inBounds(row+2, col+2)) {
					if (!this.getSquare(row+2, col+2).hasPiece() && 
						(this.getSquare(row+1, col+1).getPiece().getColor() != pColor)) {
						possibleMoves.add(this.getSquare(row+2, col+2));
					}
				}
			}
		}
	
		if (Board.inBounds(row+1, col-1) && pColor == Color.RED) {
			if(!this.getSquare(row+1, col-1).hasPiece()) {
				possibleMoves.add(this.getSquare(row+1, col-1));
			} else {
				if (Board.inBounds(row+2, col-2)) {
					if (!this.getSquare(row+2, col-2).hasPiece() && 
						(this.getSquare(row+1, col-1).getPiece().getColor() != pColor)) {
						possibleMoves.add(this.getSquare(row+2, col-2));
					}
				}
			}
		}

		if (Board.inBounds(row-1, col-1) && pColor == Color.BLACK) {
			
			if (!this.getSquare(row-1, col-1).hasPiece()) {
				possibleMoves.add(this.getSquare(row-1, col-1));
			}
			else {
				if (Board.inBounds(row-2, col-2)) {
					if (!this.getSquare(row-2, col-2).hasPiece() &&
						(this.getSquare(row-1, col-1).getPiece().getColor() != pColor)) {	
						possibleMoves.add(this.getSquare(row-2, col-2));
					}
				}
			}
		}
		
		if (Board.inBounds(row-1, col+1) && pColor == Color.BLACK) {
			if (!this.getSquare(row-1, col+1).hasPiece()) {
				possibleMoves.add(this.getSquare(row-1, col+1));
			}
			else {
				if (Board.inBounds(row-2, col+2)) {
					if (!this.getSquare(row-2, col+2).hasPiece() && 
						(this.getSquare(row-1, col+1).getPiece().getColor() != pColor)) {
						possibleMoves.add(this.getSquare(row-2, col+2));
					}
				}
			}
		}

		return possibleMoves;
	}

	public void highlightMoves(Piece p, boolean doHighlight) {
		
		Vector<Square> possibleMoves = possibleMoves(p);
		
		if (doHighlight) {
			for (Square highlight : possibleMoves) {
				highlight.setHighlight(true);
			}
		}
		
		else {
			for (Square highlight : possibleMoves) {
				highlight.setHighlight(false);
			}
		}
	}
}
