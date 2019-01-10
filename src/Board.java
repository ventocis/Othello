
public class Board {
	int [][] boardPieces = new int [7][7];
	int currentPlayer;
	
	/**
	 * This is the default constructor for Board
	 * It sets up the board in its starting position
	 */
	public Board() {
		for(int x = 0; x < 8; x++) 
			for(int y = 0; y < 8; y++)
				if((x == 3 && y == 3) || (x == 4 && y == 4))
					setPlayer(x,y,1);
				else if((x == 4 && y == 3) || (x == 3 && y == 4))
					setPlayer(x,y,2);
				else
					setPlayer(x,y,0);
		currentPlayer = 1;
	}
	
	/**
	 * This method takes the coordinates of a spot on the board
	 * & returns the player at that piece
	 * @param x - the x coordinate of the pieces
	 * @param y - the y coordinate of the piece
	 * @return  0, if an empty spot, 1 for player 1 or 2 for player 2
	 */
	public int getPlayer(int x, int y) {
		return boardPieces[x][y];
	}
	
	
	/**
	 * This takes the coordinates of a spot on the board & sets the piece at
	 * that place to the player that is passed into the method
	 * @param x - the x coordinate of the spot
	 * @param y - the y coordinate of the spot
	 * @param player - the value of the player (1 or 2 if a player, 0 if empty)
	 */
	public void setPlayer(int x, int y, int player) {
		boardPieces[x][y] = player;
	}
	
	public void nextPlayer() {
		if(currentPlayer == 1)
			currentPlayer = 2;
		else
			currentPlayer = 1;
	}
	
	public boolean isValidMove(int x, int y) {
		//FIXME needs to be finished
		return true;
	}
	
}

