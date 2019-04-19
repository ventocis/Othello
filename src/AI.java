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
//		while (true) {
//			r = rand.nextInt(8);
//			c = rand.nextInt(8);
////		for (int r = 1; r < 8; r++) {
////			for (int c = 1; c < 8; c++) {
//
//			if (check.isLegal(state.getBoard(), r, c)) {
//				move[0] = r;
//				move[1] = c;
//				return move;
//			}
//			System.out.println(r + " " + c);
//
//		}
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
					//TODO delete
					System.out.println(" " + best);
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
	
//	int minimax(Node node, int depth, int isMaximizingPlayer, int alpha, int beta, GameState game) {
//		
//		if(node == null)
//			return 0; 
//		
//		//gameValue(game.getBoard());
//		
//		// If leaf, return value
//		if (node.noChildren(node)) {
//			return gameValue(game.getBoard()); 
//		}
//		
//		GameState game2 = game;
//		
//		Stack<Node> children = node.getChildren(node);
//	
//	    if (isMaximizingPlayer == player) {
//	        int bestVal = -1000000; 
//	    
//	        for (Node child : children) {
//	        	int value = minimax(child, depth+1, opponent, alpha, beta, game2);
//	            bestVal = max( bestVal, value); 
//	            alpha = max( alpha, bestVal);
//	            if (beta <= alpha) 
//	                break;
//	        }
//	        return bestVal;
//	    }
//	    else {
//
//	    	int bestVal = 1000000;
//	
//	    	for (Node child :children) {
//	            int value = minimax(child, depth+1, player, alpha, beta, game2);
//	            bestVal = min(bestVal, value);
//	            beta = min( beta, bestVal);
//	            if (beta <= alpha)
//	                break;
//	    	}
//	    	return bestVal;
//	    }
	
//	private int min(int bestVal, int value) {
//		if (bestVal < value) 
//			return bestVal;
//		return value;
//	}
//
//	private int max(int bestVal, int value) {
//		if (bestVal > value) 
//			return bestVal;
//		return value;
	
//	}
}