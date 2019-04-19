

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AlTest {

	@Test
	public void testAl() {
		Board gameBoard = new Board();
		Gui g=new Gui(gameBoard);
		AI a=g.myAi;
		assertEquals(true, a.getOpponent()!=0);
		assertEquals(true, a.gameValue(new int[8][8])!=0);
		assertEquals(true, a.computeMove(gameBoard)!=null);
	}
}
