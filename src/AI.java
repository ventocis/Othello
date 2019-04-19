import java.util.ArrayList;

public class AI {

	int player, opponent;

	public AI() {
		
	}

	public int[] computeMove(Board board) {
		
		player = board.getCurrPlyr();
		opponent = getOpponent();
		
		
		int[] move = new int[2];
		//Random rand = new Random();

	//	Board check = new Board(state);

		int r, c;

		ArrayList<Score> availableMoves = new ArrayList<Score>();
		
		for (r=0; r<8;r++)
			for (c=0;c<8;c++) {
				if(board.isValidMove(c, r)) {
					Score score = new Score(r, c, gameValue(Board.getBoardPieces()));
					availableMoves.add(score);
				}
			}
		
		if(availableMoves.size() > 0) {
			int best = -1000009;
			Score bestMove = availableMoves.get(0) ;
			for (Score s : availableMoves) {
				if (s.getPoints() > best) {
					
					best = s.getPoints();
					bestMove = s;
				}
			}
			move[0] = bestMove.getRow();
			move[1] = bestMove.getCol();
			return move;
		}
		else {
			move[0] = -1;
			move[1] = 0;
			return move;
		}
		
		
	}

	private int getOpponent() {
		if (player == 1)
			return 2;
		else
			return 1;
	}



	private int gameValue(int[][] board) {
		int value = 0;
		for (int r = 0; r < 8; r++)
			for (int c = 0; c < 8; c++)
				
				// Corners
				if (board[r][c] == player && ((r==0 && c==0) || (r==7 && c==0) ||
				   (r==0 && c==7) || (r==7 && c==7)))
						value += 1000;
			
				else if (board[r][c] == opponent && ((r==0 && c==0) || (r==7 && c==0) ||
						(r==0 && c==7) || (r==7 && c==7)))
						value -= 10;
				
				// Edges
				else if (board[r][c] == player && (c==0 || r==0 || r ==7 || c==7))
						value += 50;
				
				else if (board[r][c] == opponent && (c==0 || r==0 || r ==7 || c==7))
						value -= 5;
				
				// Center 2x2
				else if (board[r][c] == player && ((r==3 && c==3) || (r==3 && c==4) ||
						(r==4 && c==3) || (r==4 && c==4)))
						value += 20;
				else if (board[r][c] == opponent && ((r==3 && c==3) || (r==3 && c==4) ||
						(r==4 && c==3) || (r==4 && c==4)))
						value -= 2;
				
				// All others
				else {
					if (board[r][c] == player)
						value++;
					else
						value--;
				}						
			
		return value;	
	}
}