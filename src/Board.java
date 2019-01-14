
public class Board {
	int[][] boardPieces = new int[8][8];
	int currPlyr;
	int otherPlyr;
	int finalX;
	int finalY;

	/**
	 * This is the default constructor for Board It sets up the board in its
	 * starting position
	 */
	public Board() {
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

	public int getCurrPlyr() {
		return currPlyr;
	}

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

	public boolean checkDirections(int x, int y, int xChange, int yChange) {

		if (!onBoard(x, y))
			return false;
		if (boardPieces[x][y] != 0)
			return false;

		x += xChange;
		y += yChange;

		if (!onBoard(x, y))
			return false;
		if (boardPieces[x][y] == currPlyr)
			return false;

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

	public void playPiece(int initX, int initY) {
		boolean flag = false;

		for (int xChange = -1; xChange < 2; xChange++)
			for (int yChange = -1; yChange < 2; yChange++)
				if (checkDirections(initX, initY, xChange, yChange)) {
					int tempX = initX + xChange;
					int tempY = initY + yChange;
					flag = true;

					for (; (tempX != finalX) || (tempY != finalY); tempX += xChange, tempY += yChange) {
						boardPieces[tempX][tempY] = currPlyr;
					}
				}
		if(flag)
			boardPieces[initX][initY] = currPlyr;
	}

	private boolean onBoard(int x, int y) {
		if (x > 7 || x < 0 || y > 7 || y < 0)
			return false;
		return true;
	}
	
	public int isWinner() {
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
				else
					return 0;
		
		if(plyrOnePts > plyrTwoPts)
			System.out.println("Player one won! Plyr 1 pts: " + plyrOnePts + "\nPlyr 2 pts" + plyrTwoPts);

		else
			System.out.println("Player two won! Plyr 1 pts: " + plyrOnePts + "\nPlyr 2 pts" + plyrTwoPts);
		return 1;

	}

}
