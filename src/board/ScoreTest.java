import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.junit.Test;

public class ScoreTest {

	@Test
	public void testScore() {
		Board gameBoard = new Board();
		Gui g=new Gui(gameBoard);
		AI a=g.myAi;
		int r, c;
		ArrayList<Score> availableMoves = new ArrayList<Score>();
		
		for (r=0; r<8;r++)
			for (c=0;c<8;c++) {
				if(gameBoard.isValidMove(c, r)) {
					Score score = new Score(r, c, a.gameValue(new Board().getBoardPieces()));
					availableMoves.add(score);
				}
			}
		assertEquals(true, availableMoves.size()!=0);
		assertEquals(true, availableMoves.get(0).getPoints()!=0);
		assertEquals(true, availableMoves.get(0).getRow()!=0);
		assertEquals(true, availableMoves.get(0).getCol()!=0);
	}
}
