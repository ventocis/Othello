

import static org.junit.Assert.assertEquals;

import org.junit.Test;
/**
 * This is the AI testing class.
 * @author Owner
 *
 */
public class AlTest {
	/**
	 * This is the test for AI.
	 */
	@Test
	public void testAl() {
		Board gameBoard = new Board();
		Gui g = new Gui(gameBoard);
		AI a = g.getMyAi();
		assertEquals(true, a.getOpponent() != 0);
		assertEquals(true, a.gameValue(new int[8][8]) != 0);
		assertEquals(true, a.computeMove(gameBoard) != null);
	}
}
