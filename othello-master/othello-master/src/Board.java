/**
 * 
 * @author Chandler Scott, Sam Ventocilla, Allen Huric
 * @version 1.0
 */
public  class Board {
	/**
	 * This is an array of arrays of integers(2d array).
	 */
	private static int[][] boardPieces = new int[8][8];
	/**
	 * This is an integer variable for current player.
	 */
	private static int currPlyr; 
	
	private static int otherPlyr;
	

	/**
	 * This is an integer variable to help with moving pieces.
	 * When moving columns
	 */
	private static int finalX;
	/**
	 * This is an integer variable to help with moving pieces.
	 * When moving rows
	 */
	private static int finalY;

	/**
	 * This is the default constructor for Board It sets up the board in its
	 * starting position.
	 */
	public Board() {
		newGame();
	}
	
	/**
    	 * Return a string representation of the board.
    	 * @return A board
    	 */
   	 @Override
    	public String toString() {
       		 String str = "";
        
        for (int row = 0; row < getBoardPieces().length; row++) {
            for (int col = 0; col < getBoardPieces().length; col++) {
                str += getBoardPieces()[row][col];
            }
            
            str += "\n";
        }
        
        return str;
    	}

	/**
	 * This method takes the coordinates of a spot on the board and 
	 * returns the player at that piece.
	 * 
	 * @param x - the x coordinate of the pieces
	 * @param y - the y coordinate of the piece
	 * @return 0, if an empty spot, 1 for player 1 or 2 for player 2
	 */
	public int getPlyr(final int x, final int y) {
		return getBoardPieces()[x][y];
	}

	/**
	 * This takes the coordinates of a spot on the board and sets 
	 * the piece at that place to the player that is passed into
	 * the method.
	 * 
	 * @param x      - the x coordinate of the spot
	 * @param y      - the y coordinate of the spot
	 * @param player - the value of the player 
	 * (1 or 2 if a player, 0 if empty)
	 */
	public void setPlyr(final int x, final int y, final int player) {
		getBoardPieces()[x][y] = player;
	}

	/**
	 * This method toggles the current player, between other player
	 *  and current player.
	 */
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
	 * This function returns the current player.
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
	public boolean isValidMove(final int x, final int y) {
		if (!onBoard(x, y)) {
			return false;
		}
		if (getBoardPieces()[x][y] != 0) {
			return false;
		}
		// cycle through all different changes in directions
		for (int xChange = -1; xChange < 2; xChange++) {
			for (int yChange = -1; yChange < 2; yChange++) {
				if (checkDirections(x, y, xChange, yChange)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * This function is used to check to see if there are any pieces
	 * to capture in one of the eight  selected directions. 
	 * (direction is set by xChange & yChange)
	 * @param x variable for coordinate rows
	 * @param y variable for coordinate columns
	 * @param xChange change in direction variable
	 * @param yChange change in direction variable
	 * @return true if the selection direction
	 *  can capture pieces, else false
	 */
	public boolean checkDirections(int x, int y, final int xChange, final int yChange) {

		//makes sure the move is on the Board
		if (!onBoard(x, y)) {
			return false;
		}
		//returns false if a piece in the selected space
		if (getBoardPieces()[x][y] != 0) {
			return false;
		}
		x += xChange;
		y += yChange;

		//makes sure that move is on the board
		if (!onBoard(x, y)) {
			return false;
		}
		//makes sure that the piece immediately adjacent to it isn't the
		//same player
		if (getBoardPieces()[x][y] == currPlyr) {
			return false;
		}

		/*goes through the remaining pieces & returns true there are
		 *opponents pieces surrounded on both sides by the current 
		 *player's pieces, else it returns false
		 */
		for (; onBoard(x, y); x += xChange, y += yChange) {
			if (getBoardPieces()[x][y] == 0) {
				return false;
			}
			if (getBoardPieces()[x][y] == currPlyr) {
				finalX = x;
				finalY = y;
				return true;
			}
		}
		return false;
	}

	/**
	 * This method plays the pieces and flips all of the opponent's 
	 * captured pieces.
	 * @param initX - x value of the piece that was played
	 * @param initY - y value of the piece that was played
	 */
	public void playPiece(final int initX, final int initY) {
		boolean flag = false;

		/*This loop checks all of the possible directions.
		 *that the opponents pieces could possibly be captured
		 *xChange is used to set the change in the horizontal direction
		 */
		for (int xChange = -1; xChange < 2; xChange++) {
			//yChange is used to set change
			//in the vertical direction
			for (int yChange = -1;
					yChange < 2; yChange++) {
				//checks if opponent pieces 
				//in that direction can be
				//captured
				if (checkDirections(initX, initY,
						xChange, yChange)) {
					int tempX = initX + xChange;
					int tempY = initY + yChange;
					flag = true;

					//flips all captured pieces
					for (; (tempX != finalX) 
							|| (tempY != finalY);
							tempX += xChange,
							tempY += yChange) {
						getBoardPieces()
						[tempX][tempY] = currPlyr;
					}
				}
			}
		}
		if (flag) {
			getBoardPieces()[initX][initY] = currPlyr;
		}
	}

	/**
	 * This method checks to see if the selected move is on the board.
	 * @param x - x value of the chosen move
	 * @param y - y value of the chosen move
	 * @return - true if the move is on the board, else false
	 */
	private boolean onBoard(final int x, final int y) {
		if (x > 7 || x < 0 || y > 7 || y < 0) {
			return false;
		}
		return true;
	}

	/**
	 * This method returns the winner based on the count of black
	 * verses white pieces on the board as you navigate the board.
	 * @return 1 if player One wins, 2 if Player 2 wins, -1 if tie
	 */
	public int getWinner() {
		//These two variables count the number of points for each player
		int plyrOnePts = 0;
		int plyrTwoPts = 0;
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (getBoardPieces()[x][y] == 1) {
					plyrOnePts++;
				} else if (getBoardPieces()[x][y] == 2) {
					plyrTwoPts++;
				}
			}
		}

		if (plyrOnePts > plyrTwoPts) {
			return 1;
		}
		if (plyrTwoPts > plyrOnePts) {
			return 2;
		}
		//returns -1 if there's a tie
		return -1;
	}

	/**
	 * This method creates a new game with current player set to
	 * player 1(black).
	 */
	public void newGame() {
		//sets the board array back to the values for a new game
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if ((x == 3 && y == 3) || (x == 4 && y == 4)) {
					setPlyr(x, y, 1);
				} else if ((x == 4 && y == 3) 
						|| (x == 3 && y == 4)) {
					setPlyr(x, y, 2);
				} else {
					setPlyr(x, y, 0);
				}
			}
		}
		currPlyr = 1;
		otherPlyr = 2;
	}

	/**
	 * This method counts the number of pieces white has on the board.
	 * @return returns white's score
	 */
	public int whiteScore() {
		int whiteScore = 0;

		//counts the number white pieces
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (getBoardPieces()[x][y] == 2) {
					whiteScore++;
				}
			}
		}
		return whiteScore;
	}

	/**
	 * This method counts the number of pieces black has on the board.
	 * @return returns black's score
	 */
	public int blackScore() {
		int blackScore = 0;

		//counts the number of black pieces
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (getBoardPieces()[x][y] == 1) {
					blackScore++;
				}
			}
		}
		return blackScore;
	}

	/**
	 * This method determines if the current player can move.
	 * @return true if the current player can move, false otherwise
	 */
	public boolean canMove() {
		//This for loop checks to see if there's a valid move at
		//any of the places on the board.
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (isValidMove(x, y)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @return the boardPieces
	 */
	public static int[][] getBoardPieces() {
		return boardPieces;
	}

	/**
	 * @param boardPieces the boardPieces to set.
	 */
	public static void setBoardPieces(final int[][] boardPieces) {
		Board.boardPieces = boardPieces;
	}

	/**
	 * 
	 * @param i number we are setting the currPlyr to.
	 */
	public void setCurrPlyr(final int i) {
		currPlyr = i;
	}	
}
