package board;


import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Test;
/**
 * Class for test three.
 * @author Owner
 *
 */
public class Test3 {
	/**
	 * Method for testing the color and in bounds. 
	 */
  @Test
    public void testCheck() {
    CheckersBoard board = new CheckersBoard();
    assertEquals(false, CheckersBoard.inBounds(10, 10));
    assertEquals(true, board.getSquare(1, 2) != null);
    assertEquals(true, board.getSquare(1, 2).getHeight() != 0);
    board.initializePieces();
    Color c = new Color(0);
    CheckersPiece piece = new CheckersPiece(c, 1, 1);
    assertEquals(true, board.possibleMoves(piece) != null);
    board.highlightMoves(piece, true);
        
  }
}
