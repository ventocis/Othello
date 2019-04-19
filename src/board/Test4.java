package board;


import static org.junit.Assert.assertEquals;

import java.awt.Color;

import javax.swing.JPanel;

import org.junit.Test;


/**
 * Test class number four.
 * @author Owner
 *
 */
public class Test4 {
	/**
	 * Final test case for the in bound and color and 
	 * various pieces in the project.
	 */
  @Test
    public void testCheckers() {
    CheckersBoard board = new CheckersBoard();
    assertEquals(false, CheckersBoard.inBounds(10, 10));
    assertEquals(true, board.getSquare(1, 2) != null);
    assertEquals(true, board.getSquare(1, 2).getHeight() != 0);
    board.initializePieces();
    Color c = new Color(0);
    CheckersPiece piece = new CheckersPiece(c, 1, 1);
    assertEquals(true, board.possibleMoves(piece) != null);
    board.highlightMoves(piece, true);
    CheckersGui app = new CheckersGui();
    app.resetGame();
    app.setBoard(board, new JPanel());
    app.updateGameStatus();
    assertEquals(null, app.getWinner());
  }
}
