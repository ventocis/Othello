import java.util.ArrayList;

/**
 * @author Owner
 * This is the class that contains all of logic for the Battle Ship
 * board. 
 * 
 * Smallship = 2 spaces(1)
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
	private static int[][] board = new int[SIZE][SIZE];
	/**
	 * This is an integer variable for current player.
	 */
	private static int humanPlayer; 
	
	private static int compPlayer;
	
	private static final int SHIP = 3; // a new game will have 17 ship squares
	
	private static final int REDPEG = 2; // a finished game will contain 17
	
	private static final int GREYPEG = 1; // max on board can be 83
	
	private static final int EMPTY = 0; //represents a spot on board with no peg or ship
	
	
	
	
	public BattleBoard() {
		initializeBoard();
	}
	
	public void initializeBoard() {
		for(int i = 0; i < SIZE; i++){
			for(int j = 0; j < SIZE; j++) {
				board[i][j] = 0;
			}
		}
	}
	
	public void switchPlayer() {
		int player = this.getCurrPlyr();
		if (player == humanPlayer) {
			player = compPlayer;
		} else {
			player = humanPlayer;
		}

	}
	private boolean onBoard(final int x, final int y) {
		if (x > SIZE || x < 0 || y > SIZE || y < 0) {
			return false;
		}
		return true;
	}
	
	public boolean isValidMove(final int x, final int y) {
		if(onBoard(x, y) && board[x][y] == EMPTY) {
			return true;
		}
		return false;
	}
	
	public boolean checkHit(final int x, final int y) {
		if(board[x][y] == SHIP) {
			return true;
		}
		return false;
	}
	
	public void placePieceAt(final int x, final int y) {
		if(isValidMove(x,y)) {
			if(checkHit(x,y)) {
				board[x][y] = REDPEG;
			}else {
				board[x][y] = GREYPEG;
			}
		}else {
			System.out.println("This is not a valid move try again...");
			return;
		}
	}

	public void newGame() {
		// TODO Auto-generated method stub
		
	}

	public void playPiece(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	public void nextPlyr() {
		// TODO Auto-generated method stub
		
	}

	public int getCurrPlyr() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean gameOver() {
		int hitCount = 0;
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				if(board[i][j] == REDPEG) {
					hitCount++;
				}
			}
		}
		if(hitCount == 17) {
			return true;
		}
		return false;
	}

	public String playerScore() {
		// TODO Auto-generated method stub
		return null;
	}

	public String compScore() {
		// TODO Auto-generated method stub
		return null;
	}

	public String player1Score() {
		// TODO Auto-generated method stub
		return null;
	}

	public String player2Score() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getPlyr(int x, int y) {
		// TODO Auto-generated method stub
		return 0;
	}


}
