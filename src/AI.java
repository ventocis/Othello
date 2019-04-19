import java.util.ArrayList;
/**
 * Our class for the AI portion of Othello.
 * @author Owner
 *
 */
public class AI {
	/**
	 * Variable for the player.
	 */
	private int player;
	/**
	 * Variable for opponent.
	 */
	private int opponent;
	/**
	 * Acts as constructor for AI.
	 */
	public AI() {	
	}
	/**
	 * Method to assume move.
	 * @param board the board player on.
	 * @return returns the location.
	 */
	public int[] computeMove(final Board board) {
		player = board.getCurrPlyr();
		opponent = getOpponent();
		int[] move = new int[2];
		//Random rand = new Random();

	//	Board check = new Board(state);

		int r, c;

		ArrayList<Score> availableMoves = new ArrayList<Score>();
		
		for (r = 0; r < 8; r++) {
			for (c = 0; c < 8; c++) {
				if (board.isValidMove(c, r)) {
					Score score = new Score(r, c,
					gameValue(Board.getBoardPieces()));
					availableMoves.add(score);
				}
			}
		}
		
		if (availableMoves.size() > 0) {
			int best = -1000009;
			Score bestMove = availableMoves.get(0);
			for (Score s : availableMoves) {
				if (s.getPoints() > best) {
					
					best = s.getPoints();
					bestMove = s;
				}
			}
			move[0] = bestMove.getRow();
			move[1] = bestMove.getCol();
			return move;
		} else {
			move[0] = -1;
			move[1] = 0;
			return move;
		}
		
		
	}
	/**
	 * Returns the opponent.
	 * @return returns the opponent.
	 */
	public int getOpponent() {
		if (player == 1) {
			return 2;
		} else {
			return 1;
		}
	}


	/**
	 * Determines value for AI to tell it what to do.
	 * @param board is the board worked on.
	 * @return returns a value of that position.
	 */
	public int gameValue(final int[][] board) {
		int value = 0;
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				
				// Corners
				if (board[r][c] == player 
						&& ((r == 0 && c == 0)
						|| (r == 7 && c == 0) 
						||
				   (r == 0 && c == 7) 
				 || (r == 7 && c == 7))) {
						value += 1000;
				} else if (board[r][c] == opponent 
						&& ((r == 0 && c == 0) 
						|| (r == 7 && c == 0) 
						||
						(r == 0 && c == 7) 
						|| (r == 7 && c == 7))) {
						value -= 10;
				} else if (board[r][c] == player 
						&& (c == 0 || r == 0 
						|| r == 7 || c == 7)) {
						value += 50;
				} else if (board[r][c] == opponent 
						&& (c == 0 || r == 0 
						|| r == 7 || c == 7)) {
						value -= 5;
				} else if (board[r][c] == player 
						&& ((r == 3 
						&& c == 3) || (r == 3 
						&& c == 4) 
								||
						(r == 4 && c == 3) 
						|| (r == 4 && c == 4))) {
						value += 20;
				} else if (board[r][c] == opponent 
						&& ((r == 3 && c == 3) 
								|| (r == 3 
								&& c == 4) 
								||
						(r == 4 && c == 3) 
						|| (r == 4 && c == 4))) {
						value -= 2;
				} else {
					if (board[r][c] == player) {
						value++;
					} else {
						value--;
					}
				}	
			}
			}
			
		return value;	
	}
}
