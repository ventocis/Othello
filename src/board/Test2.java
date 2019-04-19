package board;


import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Test;
/**
 * Test for the color.
 * @author Owner
 *
 */
public class Test2 {
	/**
	 * Test case for the color.
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
    		.BackgroundColor.DARK, square.getBackgroundColor());
    assertEquals(true, square.getHeight() != 0);
  }
}
