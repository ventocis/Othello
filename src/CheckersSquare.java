import java.awt.*;
/**
 * @author Chandler Scott, Sam Ventocilla, Allen Huric
 * This is the class that contains individual square logic checkers
 * board. 
 * 
 * @version 1.0
 */
@SuppressWarnings("serial")
public class CheckersSquare extends Canvas {
    public enum BackgroundColor { LIGHT, DARK };
	private BackgroundColor bgColor;
    private boolean isPieceExisting;
    private CheckersPiece CheckersPiece;

    private int row;
    private int col;
	
	/**
     * Constructor for the square class.
     * @param c variable for the background color of each square.
     * @param row variable representing the row for each square.
     * @param col variable representing the column for each square.
     */
    public CheckersSquare(BackgroundColor c, int row, int col) {
    	
    	this.setSize(64, 64);
    	
    	if(c == BackgroundColor.DARK)
    		this.setBackground(Color.DARK_GRAY);
    	else
    		this.setBackground(Color.LIGHT_GRAY);
    	
        bgColor = c;
        isPieceExisting = false;
        CheckersPiece = null;
        
        this.row = row;
        this.col = col;
        
    }
	    /**
     * Method to check if square contains piece.
     * @return returns true if has a piece. 
     */
    public boolean hasPiece() {
        return this.isPieceExisting;
    }
     /**
     * Method to return row of square.
     * @return returns the row location.
     */
    public int getRow() {
    	return this.row;
    }
     /**
     * Method to return the column of square.
     * @return returns the column location.
     */
    public int getCol() {
    	return this.col;
    }
     /**
     * Method to return the background color of the square.
     * @return returns the current color.
     */
    public CheckersSquare.BackgroundColor getBackgroundColor() {
    	return this.bgColor;
    }
    
	    /**
     * Method to get the piece on the square.
     * @return returns the piece.
     */
    public CheckersPiece getPiece() {
    	if(this.hasPiece())
    		return this.CheckersPiece;
    	
    	return null;
    }
	
	/**
     	* Method to change the color of the square to its proper color.
     	*/
	public void paint(Graphics g) {
        if (this.getBackgroundColor() == CheckersSquare.BackgroundColor.DARK) {
			this.setBackground(Color.DARK_GRAY);
        } else {
            this.setBackground(Color.LIGHT_GRAY);
        }
		
		if (this.hasPiece()) {
			g.setColor(CheckersPiece.getColor());
			g.fillOval(5, 5, 54, 54);
		} else {
            g.clearRect(0, 0, 64, 64);
        }			
	}    
	 
	/**
	 * Sets the piece to its valid location. 
	 * @param visitor acts as placeholder if piece is there.
	 */
	public void setPiece(CheckersPiece visitor) {
    	if(visitor != null) {
    		this.CheckersPiece = visitor;
    		this.isPieceExisting = true;
    	}
    	
    	else {
    		this.CheckersPiece = null;
    		this.isPieceExisting = false;
    	}
    }
	
		/**
	 * Sets the highlight color of the square when used.
	 * @param doHighlight flag for performing the highlight.
	 */
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
