import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Chandler Scott 
 * This is the class that contains all of logic for the Battle Ship
 * board. 
 * 
 * smallShip = 2 spaces(1)
 * medShip = 3 spaces(2)
 * bigShip = 4 spaces(1)
 * biggestShip = 5 spaces(1) 
 * 
 * 
 */
public class BattleBoard {
	/**
	 * This is a variable for the dimensions of the board.
	 */
	private static int SIZE = 10;
	/**
	 * This is an array of arrays of integers(2d array).
	 */
	private static int[][] board = new int[getSIZE()][getSIZE()];
	/**
	 * This is an integer variable for current player.
	 */
	private static int humanPlayer = 0; 
	private static int compPlayer = 1;

	private static int currPlayer;

	private static final int SHIP = 3; // a new game will have 17 ship squares total
	private static final int REDPEG = 2; // a finished game will contain 17
	private static final int GREYPEG = 5; // max on board can be 83
	private static final int EMPTY = 4; //represents a spot on board with no peg or ship
	private static final int SHIPMAX = 17; //max amount of ship spaces allowed on the board
	private static int count = 0;

	/**
	 * These are the names for all of the ships.
	 */
	private static final String smallShip = "small ship";
	private static final String medShip = "medium ship";
	private static final String bigShip = "big ship";
	private static final String biggestShip = "biggest ship";


	/**
	 * Constructor for the board.
	 */
	public BattleBoard() {
		currPlayer = 0;
		initializeBoard();
	}
	/**
	 * Starts up the board with all empty squares.
	 */
	protected void initializeBoard() {
		for(int i = 0; i < getSIZE(); i++){
			for(int j = 0; j < getSIZE(); j++) {
				board[i][j] = getEmpty();
			}
		}
	}
	/**
	 * Switches between two different players.
	 */
	private void switchPlayer() {
		int player = this.getCurrPlyr();
		if (player == getHumanPlayer()) {
			player = compPlayer;
		} else {
			player = getHumanPlayer();
		}
	}
	/**
	 * Method to check if given coordinates are on the board.
	 * @param x represents rows.
	 * @param y represents columns.
	 * @return true if on the board false if not on the board
	 */
	private boolean onBoard(final int x, final int y) {
		if (x > getSIZE() || x < 0 || y > getSIZE() || y < 0) {
			return false;
		}
		return true;
	}
	/**
	 * Method to validate if the placement of the peg is valid.
	 * 
	 * @param x position on the board representing a row.
	 * @param y position on the board representing a column.
	 * @return true only if the spot is empty, or a ship value, and on the board.
	 */
	private boolean isValidPegMove(final int x, final int y) {
		if(onBoard(x, y) && (board[x][y] == getEmpty() || board[x][y] == getShip())) {
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @param x represents rows
	 * @param y represents columns.
	 * @return true if hit false is not
	 */
	private boolean checkHit(final int x, final int y) {
		if(board[x][y] == getShip()) {
			return true;
		}
		return false;
	}
	/**
	 * This method is used place peg pieces.
	 * 
	 * @param x represents the rows.
	 * @param y represents the columns.
	 */
	private void placePegPieceAt(final int x, final int y) {
		if(isValidPegMove(x,y)) {
			if(checkHit(x,y)) {
				board[x][y] = getRedpeg();
			}else if(!checkHit(x,y)){
				board[x][y] = getGreypeg();
			}
		}
	}

	/**
	 * Method to make sure when placing a ship that is adjacent to the 
	 * y axis is valid.
	 * 
	 * @param sname is the name of the ship.
	 * @param x represents the rows.
	 * @param y represents the columns. 
	 * @return true or false
	 */
	protected boolean isValidHorizontalShipMove(String sname, final int x, final int y) {
		int row = x;
		int col = y;
		
		if(count >= SHIPMAX) {
			return false;
		}

		if(board[row][col] != getEmpty()) {
			return false;
		}

		if(onBoard(x,y) && sname.equals(smallShip)) {
			//checks to see if the ship is in bounds on the east border
			if(onBoard(row, col+1)) {
				for(int i = col+1; i == col; i--) {
					if(board[row][i] == getEmpty()) {
						return true;
					}
				}
			}
		}
		if(onBoard(x,y) && sname.equals(medShip)) {
			//checks to see if the ship is in bounds on the east border
			if(onBoard(row, col+2)) {
				for(int i = col+2; i == col; i--) {
					if(board[row][i] == getEmpty()) {
						return true;
					}
				}
			}
		}
		if(onBoard(x,y) && sname.equals(bigShip)) {
			//checks to see if the ship is in bounds on the east border
			if(onBoard(row, col+3)) {
				for(int i = col+3; i == col; i--) {
					if(board[row][i] == getEmpty()) {
						return true;
					}
				}
			}
		}
		if(onBoard(x,y) && sname.equals(biggestShip)) {
			//checks to see if the ship is in bounds on the east border
			if(onBoard(row, col+4)) {
				for(int i = col+4; i == col; i--) {
					if(board[row][i] == getEmpty()) {
						return true;
					}
				}
			}
		}
		return false;
	}
	/**
	 * Method to make sure when placing a ship that is parallel to the
	 * y axis is valid.
	 * 
	 * @param sname is the name of the ship.
	 * @param x represents the rows.
	 * @param y represents the columns
	 * @return true or false 
	 */
	private boolean isValidVerticalShipMove(String sname, final int x, final int y) {
		int row = x;
		int col = y;
		
		if(count >= SHIPMAX) {
			return false;
		}

		if(board[row][col] != getEmpty()) {
			return false;
		}

		if(onBoard(x,y) && sname.equals(smallShip)) {
			//checks to see if the ship is in bounds on the north border
			if(onBoard(row-1, col)) {
				for(int i = row-1; i == row; i++) {
					if(board[i][col] == getEmpty()) {
						return true;
					}
				}
			}
		}
		if(onBoard(x,y) && sname.equals(medShip)) {
			//checks to see if the ship is in bounds on the north border
			if(onBoard(row-2, col)) {
				for(int i = row-2; i == row; i++) {
					if(board[i][col] == getEmpty()) {
						return true;
					}
				}
			}
		}
		if(onBoard(x,y) && sname.equals(bigShip)) {
			//checks to see if the ship is in bounds on the north border
			if(onBoard(row-3, col)) {
				for(int i = row-3; i == row; i++) {
					if(board[i][col] == getEmpty()) {
						return true;
					}
				}
			}
		}
		if(onBoard(x,y) && sname.equals(biggestShip)) {
			//checks to see if the ship is in bounds on the north border
			if(onBoard(row-4, col)) {
				for(int i = row-4; i == row; i++) {
					if(board[i][col] == getEmpty()) {
						return true;
					}
				}
			}
		}
		return false;
	}
	/**
	 * Sets the values for the various ships in the proper direction and 
	 * size.
	 * 
	 * @param sname is the name of the ship placed.
	 * @param x represents rows.
	 * @param y represents columns.
	 */
	protected void placeShipAt(String sname, final int x, final int y) {
		//work on this after completing the functions for isValidShipMove
		int row = x;
		int col = y;
		
		//count variable is used in this method to keep track of how many
		//ships are on the board at one time, each square taken up by a 
		//black square counts as part of a ship. The max on a board is 17.

		if(sname.equals("small ship v")) {
			if(isValidVerticalShipMove("small ship", x, y)) {
				for(int i = row-1; i == row; i++) {
					board[i][col] = getShip();
					count++;
				}
			}			
		} 
		if(sname.equals("medium ship v")) {
			if(isValidVerticalShipMove("medium ship", x, y)) {
				for(int i = row-2; i == row; i++) {
					board[i][col] = getShip();
					count++;
				}
			}
		} 
		if(sname.equals("big ship v")) {
			if(isValidVerticalShipMove("big ship", x, y)) {
				for(int i = row-3; i == row; i++) {
					board[i][col] = getShip();
					count++;
				}
			}
		} 
		if(sname.equals("biggest ship v")) {
			if(isValidVerticalShipMove("biggest ship", x, y)) {
				for(int i = row-4; i == row; i++) {
					board[i][col] = getShip();
					count++;
				}
			}
		} 
		if(sname.equals("small ship h")) {
			if(isValidHorizontalShipMove("small ship", x, y)) {
				for(int i = col+1; i == col; i--) {
					board[row][i] = getShip();
						count++;
				}
			}
		}			
		 
		if(sname.equals("medium ship h")) {
			if(isValidHorizontalShipMove("medium ship", x, y)) {
				for(int i = col+2; i == col; i--) {
					board[row][i] = getShip();
						count++;
				}
			}
		} 
		if(sname.equals("big ship h")) {
			if(isValidHorizontalShipMove("big ship", x, y)) {
				for(int i = col+3; i == col; i--) {
					board[row][i] = getShip();
						count++;
				}
			}
		} 
		if(sname.equals("biggest ship h")) {
			if(isValidHorizontalShipMove("biggest ship", x, y)) {
				for(int i = col+4; i == col; i--) {
					board[row][i] = getShip();
						count++;
				}
			}
		} 

	}

	public int getCurrPlyr() {
		return currPlayer;
	}
	public int setCurrPlyr() {
		currPlayer++;
		currPlayer = currPlayer%2;
		return currPlayer;
	}

	private boolean gameOver() {
		int hitCount = 0;
		for(int i = 0; i < getSIZE(); i++) {
			for(int j = 0; j < getSIZE(); j++) {
				if(board[i][j] == getRedpeg()) {
					hitCount++;
				}
			}
		}
		if(hitCount >= SHIPMAX) {
			return true;
		}
		return false;
	}

	public int getPlayerScore(int player) {
		int playerScore = 0;
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; i < SIZE; j++) {
				if(board[i][j] == REDPEG) {
					playerScore++;
				}
			}
		}
		return playerScore;
	}

	public int getPlyr(int x, int y) {
		return board[x][y];
	}
	
	public void setPlyr(int x, int y) {
		board[x][y] = currPlayer;
	}

	/**
	 * @return the ship.
	 */
	public static int getShip() {
		return SHIP;
	}

	/**
	 * @return the red peg.
	 */
	public static int getRedpeg() {
		return REDPEG;
	}

	/**
	 * @return the grey peg.
	 */
	public static int getGreypeg() {
		return GREYPEG;
	}

	/**
	 * @return the empty.
	 */
	public static int getEmpty() {
		return EMPTY;
	}

	/**
	 * @return the humanPlayer.
	 */
	public static int getHumanPlayer() {
		return humanPlayer;
	}

	/**
	 * @param humanPlayer, the humanPlayer to set.
	 */
	public static void setHumanPlayer(int humanPlayer) {
		BattleBoard.humanPlayer = humanPlayer;
	}
	/**
	 * @return the sIZE
	 */
	public static int getSIZE() {
		return SIZE;
	}
	/**
	 * @param sIZE the sIZE to set
	 */
	public static void setSIZE(int sIZE) {
		SIZE = sIZE;
	}


}
