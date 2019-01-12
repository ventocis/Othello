
public class Board {
	int [][] boardPieces = new int [8][8];
	int currPlyr;
	int otherPlyr;
	
	/**
	 * This is the default constructor for Board
	 * It sets up the board in its starting position
	 */
	public Board() {
		for(int x = 0; x < 8; x++) { 
			for(int y = 0; y < 8; y++) {
				if((x == 3 && y == 3) || (x == 4 && y == 4))
					setPlyr(x,y,1);
				else if((x == 4 && y == 3) || (x == 3 && y == 4))
					setPlyr(x,y,2);
				else
					setPlyr(x,y,0);
			}
		}
		currPlyr = 1;
		otherPlyr = 2;
	}
	
	/**
	 * This method takes the coordinates of a spot on the board
	 * & returns the player at that piece
	 * @param x - the x coordinate of the pieces
	 * @param y - the y coordinate of the piece
	 * @return  0, if an empty spot, 1 for player 1 or 2 for player 2
	 */
	public int getPlyr(int x, int y) {
		return boardPieces[x][y];
	}
	
	
	/**
	 * This takes the coordinates of a spot on the board & sets the piece at
	 * that place to the player that is passed into the method
	 * @param x - the x coordinate of the spot
	 * @param y - the y coordinate of the spot
	 * @param player - the value of the player (1 or 2 if a player, 0 if empty)
	 */
	public void setPlyr(int x, int y, int player) {
		boardPieces[x][y] = player;
	}
	
	public void nextPlyr() {
		if(currPlyr == 1) {
			otherPlyr = currPlyr;
			currPlyr = 2;
		}
		else {
			otherPlyr = currPlyr;
			currPlyr = 1;
		}
	}
	
	public int getCurrPlyr() {
		return currPlyr;
	}
	
	public boolean isValidMove(int x, int y) {
		//FIXME needs to be finished
		if(x > 7 || x < 0 || y > 7 || y < 0)
			return false;
		
		checkDirections(x,y,1,1);
		checkDirections(x,y,1,0);
		checkDirections(x,y,1,-1);
		checkDirections(x,y,0,1);
		checkDirections(x,y,0,0);
		checkDirections(x,y,0,-1);
		checkDirections(x,y,-1,1);
		checkDirections(x,y,-1,0);
		checkDirections(x,y,-1,-1);
		return true;
	}
	
	public boolean checkDirections(int x, int y, int xChange, int yChange) {
		x += xChange;
		y += yChange;
		
		if(x > 7 || x < 0 || y > 7 || y < 0)
			return false;
		if(boardPieces[x][y] == currPlyr)
			return false;
		
		x += xChange;
		y += yChange;
		
		if(x > 7 || x < 0 || y > 7 || y < 0)
			return false;
		
		for(;y < 8 && y < 8 && x > 0 && y > 0; x += xChange, y += yChange) {
			if(boardPieces[x][y] == currPlyr)
				return true;
		}
		
		return false;
	}
	
}

