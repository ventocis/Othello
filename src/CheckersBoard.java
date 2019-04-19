import java.util.Vector;
import java.awt.*;

public class CheckersBoard {
    public static final int rows = 8;
    public static final int cols = 8;
    private CheckersSquare[][] squares;

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

    public static boolean inBounds(int row, int col) {
    	if (row >= 0 && row < rows &&
    		col >= 0 && col < cols) {	
			return true;
		} else {
			return false;
		}
    }

    public CheckersSquare getSquare(int row, int col) {
        if (inBounds(row, col)) {
        	return squares[row][col];
		} else {
			return null;	
		}
    }
    
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
