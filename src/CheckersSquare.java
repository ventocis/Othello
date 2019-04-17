import java.awt.*;

@SuppressWarnings("serial")
public class CheckersSquare extends Canvas {
    public enum BackgroundColor { LIGHT, DARK };
	private BackgroundColor bgColor;
    private boolean isPieceExisting;
    private Piece piece;

    private int row;
    private int col;

    public CheckersSquare(BackgroundColor c, int row, int col) {
    	
    	this.setSize(64, 64);
    	
    	if(c == BackgroundColor.DARK)
    		this.setBackground(Color.DARK_GRAY);
    	else
    		this.setBackground(Color.LIGHT_GRAY);
    	
        bgColor = c;
        isPieceExisting = false;
        piece = null;
        
        this.row = row;
        this.col = col;
        
    }

    public boolean hasPiece() {
        return this.isPieceExisting;
    }
    
    public int getRow() {
    	return this.row;
    }
    
    public int getCol() {
    	return this.col;
    }
    
    public Square.BackgroundColor getBackgroundColor() {
    	return this.bgColor;
    }
    
    public Piece getPiece() {
    	if(this.hasPiece())
    		return this.piece;
    	
    	return null;
    }

	public void paint(Graphics g) {
        if (this.getBackgroundColor() == Square.BackgroundColor.DARK) {
			this.setBackground(Color.DARK_GRAY);
        } else {
            this.setBackground(Color.LIGHT_GRAY);
        }
		
		if (this.hasPiece()) {
			g.setColor(piece.getColor());
			g.fillOval(5, 5, 54, 54);
		} else {
            g.clearRect(0, 0, 64, 64);
        }			
	}    
	 
	public void setPiece(Piece visitor) {
    	if(visitor != null) {
    		this.piece = visitor;
    		this.isPieceExisting = true;
    	}
    	
    	else {
    		this.piece = null;
    		this.isPieceExisting = false;
    	}
    }

    public void setHighlight(boolean doHighlight) {
    	
    	
    	Graphics g = this.getGraphics();
    	
		if(doHighlight) {

	    	if(!this.hasPiece()) {
	    		
	    		g.setColor(Color.BLACK);
	    	
	    		for(int i = 0; i < 360; i+= 30)
	    			g.drawArc(5, 5, 54, 54, i, 15);
	    	} else {
	    		g.setColor(Color.YELLOW);
	    		g.draw3DRect(0, 0, 63, 63, false);
	    	}
    	} else {
			super.update(this.getGraphics());
		}
    }
}
