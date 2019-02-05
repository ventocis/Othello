
public  class Board {
	static int[][] boardPieces = new int[8][8];
	int currPlyr;
	int otherPlyr;
	int finalX;
	int finalY;

	/**
	 * This is the default constructor for Board It sets up the board in its
	 * starting position
	 */
	public Board() {
		newGame();
	}

	/**
	 * This method takes the coordinates of a spot on the board & returns the player
	 * at that piece
	 * 
	 * @param x - the x coordinate of the pieces
	 * @param y - the y coordinate of the piece
	 * @return 0, if an empty spot, 1 for player 1 or 2 for player 2
	 */
	public int getPlyr(int x, int y) {
		return boardPieces[x][y];
	}

	/**
	 * This takes the coordinates of a spot on the board & sets the piece at that
	 * place to the player that is passed into the method
	 * 
	 * @param x      - the x coordinate of the spot
	 * @param y      - the y coordinate of the spot
	 * @param player - the value of the player (1 or 2 if a player, 0 if empty)
	 */
	public void setPlyr(int x, int y, int player) {
		boardPieces[x][y] = player;
	}

	public void nextPlyr() {
		if (currPlyr == 1) {
			otherPlyr = currPlyr;
			currPlyr = 2;
		} else {
			otherPlyr = currPlyr;
			currPlyr = 1;
		}
	}

	/**
	 * Returns current player
	 * @return current player in game
	 */
	public int getCurrPlyr() {
		return currPlyr;
	}

	/**
	 * 
	 * @param x - the x position of the move
	 * @param y - the y position of the move
	 * @return true if move is valid, else false
	 */
	public boolean isValidMove(int x, int y) {
		if (!onBoard(x, y))
			return false;
		if (boardPieces[x][y] != 0)
			return false;

		// cycle through all different changes in directions
		for (int xChange = -1; xChange < 2; xChange++)
			for (int yChange = -1; yChange < 2; yChange++)
				if (checkDirections(x, y, xChange, yChange))
					return true;

		return false;
	}

	/**
	 * This function is used to check to see if there are any pieces to capture
	 * in the selected direction (direction is set by xChange & yChange)
	 * @param x
	 * @param y
	 * @param xChange
	 * @param yChange
	 * @return true if the selection direction can capture pieces, else false
	 */
	public boolean checkDirections(int x, int y, int xChange, int yChange) {

		//makes sure the move is on the Board
		if (!onBoard(x, y))
			return false;
		//returns false if there is already a piece in the selected space
		if (boardPieces[x][y] != 0)
			return false;

		x += xChange;
		y += yChange;

		//makes sure that move is on the board
		if (!onBoard(x, y))
			return false;

		//makes sure that the piece immediately adjacent to it isn't the same player
		if (boardPieces[x][y] == currPlyr)
			return false;

		//goes through the remaining pieces & returns true there are opponents pieces
		//surrounded on both sides by the current player's pieces, else it returns false
		for (; onBoard(x, y); x += xChange, y += yChange) {
			if (boardPieces[x][y] == 0)
				return false;
			if (boardPieces[x][y] == currPlyr) {
				finalX = x;
				finalY = y;
				return true;
			}
		}

		return false;
	}

	/**
	 * This method plays the pieces & flips all of the opponent's captured pieces
	 * @param initX - x value of the piece that was played
	 * @param initY - y valie of the piece that was played
	 */
	public void playPiece(int initX, int initY) {
		boolean flag = false;

		//this loop goes through & checks all of the possible directions that the opponents
		//pieces could possibly be captured
		//xchange is used to set the change in the horizontal direction
		for (int xChange = -1; xChange < 2; xChange++)
			//yChange is used to set the change in the vertical direction
			for (int yChange = -1; yChange < 2; yChange++)
				//checks to see if opponent pieces in that direction can be captured
				if (checkDirections(initX, initY, xChange, yChange)) {
					int tempX = initX + xChange;
					int tempY = initY + yChange;
					flag = true;

					//flips all captured pieces
					for (; (tempX != finalX) || (tempY != finalY); tempX += xChange, tempY += yChange) {
						boardPieces[tempX][tempY] = currPlyr;
					}
				}
		if(flag)
			boardPieces[initX][initY] = currPlyr;
	}

	/**
	 * checks to see if the selected move is on the board
	 * @param x - x value of the chosen move
	 * @param y - y value of the chosen move
	 * @return - true if the move is on the board, else false
	 */
	private boolean onBoard(int x, int y) {
		if (x > 7 || x < 0 || y > 7 || y < 0)
			return false;
		return true;
	}

	/**
	 * Returns the winner
	 * @return 1 if player One wins, 2 if Player 2 wins, -1 if tie
	 */
	public int whoWon() {
		int plyrOnePts = 0;
		int plyrTwoPts = 0;
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				if(boardPieces[x][y] == 1) {
					plyrOnePts++;
				}
				else if(boardPieces[x][y] == 2) {
					plyrTwoPts++;
				}

		if(plyrOnePts > plyrTwoPts)
			return 1;
		if(plyrTwoPts > plyrOnePts)
			return 2;

		return -1;
	}
	
	/**
	 * Determines if there is a winner
	 * @return true if game is over, else false
	 */
	public boolean gameOver() {
		int counter = 0;
		int x = 0, y = 0;
		for(; x < 8; x++) {
			for(; y < 8; y++) {
				
				if(boardPieces[x][y] != 0)
					counter++;
			}
		}
		
		//check to see if it went through all of the pieces & the last piece wasn't empty
		if(counter == 64) {
			System.out.println("Full board");
			return true;
		}
		
		if(!canMove()) {
			nextPlyr();
			if(!canMove()) {
				nextPlyr();
				System.out.println("no one can move");
				return true;
			}
			else
				nextPlyr();
		}
		
		return false;
		
	}

	public void newGame() {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if ((x == 3 && y == 3) || (x == 4 && y == 4))
					setPlyr(x, y, 1);
				else if ((x == 4 && y == 3) || (x == 3 && y == 4))
					setPlyr(x, y, 2);
				else
					setPlyr(x, y, 0);
			}
		}
		currPlyr = 1;
		otherPlyr = 2;
	}

	public int whiteScore() {
		int whiteScore = 0;
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				if(boardPieces[x][y] == 2)
					whiteScore++;

		return whiteScore;
	}

	public int blackScore() {
		int blackScore = 0;
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				if(boardPieces[x][y] == 1)
					blackScore++;

		return blackScore;
	}
	
	
	public boolean canMove() {
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				if(isValidMove(x, y)) 
					return true;
				
		return false;
	}	
}
