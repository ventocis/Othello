import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BoardTest {
	
	@Test
	void testBoard() {
		fail("Not yet implemented");
	}

	@Test
	void testGetPlyr() {
		fail("Not yet implemented");
	}

	@Test
	void testSetPlyr() {
		fail("Not yet implemented");
	}

	@Test
	void testNextPlyr() {
		fail("Not yet implemented");
	}

	@Test
	void testGetCurrPlyr() {
		fail("Not yet implemented");
	}

	@Test
	void testBadCoordinates() {
		Board board = new Board();
		assertFalse(board.isValidMove(3,8));
	}
	@Test
	void testGoodCoordinates() {
		Board board = new Board();
		assertTrue(board.isValidMove(3,5));
	}

	@Test
	void testPlayPiece() {
		fail("Not yet implemented");
	}

	@Test
	void testGetWinner() {
		fail("Not yet implemented");
	}

	@Test
	void testNewGame() {
		fail("Not yet implemented");
	}

	@Test
	void testWhiteScore() {
		fail("Not yet implemented");
	}

	@Test
	void testBlackScore() {
		fail("Not yet implemented");
	}

	@Test
	void testCanMove() {
		fail("Not yet implemented");
	}

}
