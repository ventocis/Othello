package board;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import javax.swing.JPanel;

import org.junit.Test;
/**
 * This is testing class one.
 * @author Owner
 *
 */
public class Test1 {
	/**
	 * Method to test the pieces.
	 */
	@Test
	public void testPieces() {
		Color c = new Color(0);
		CheckersPiece myUnit = new CheckersPiece(c, 1, 1);
		String color = myUnit.getColor().toString();
		int row = myUnit.getRow();
		int col = myUnit.getCol();
		assertEquals("java.awt.Color[r=0,g=0,b=0]", color);
		assertEquals(1, row);
		assertEquals(1, col);
	}
	/**
	 * Method to test the color.
	 */
	@Test
	public void testColor() {
		Color c = new Color(0);
		CheckersPiece piece = new CheckersPiece(c, 1, 1);
		CheckersSquare square = 
				new CheckersSquare(CheckersSquare
						.BackgroundColor.DARK, 10, 10);
		square.setPiece(piece);
		assertEquals(true, square.getPiece() != null);
		assertEquals(CheckersSquare
				.BackgroundColor.DARK, 
				square.getBackgroundColor());
		assertEquals(true, square.getHeight() != 0);
	}
	/**
	 * Method to test the checkers board.
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
	/**
	 * Method to test the checkers board.
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
