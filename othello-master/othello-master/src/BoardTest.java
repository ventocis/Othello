
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alen Sam Chandler
 * @version 1.0
 */
public class BoardTest {

    /**
     * Check if getting the player's piece is returned correctly.
     */
    @Test
    public void testGetPlyr() {
        Board board = new Board();
        board.newGame();

        // Initial board state is:
        // 00000000
        // 00000000
        // 00000000
        // 00012000
        // 00021000
        // 00000000
        // 00000000
        // 00000000

        // Rebuild the board it should be the same
        String actual = "";

        for (int x = 0; x < Board.getBoardPieces().length; x++) {
            for (int y = 0; y < Board.getBoardPieces().length; y++) {
                actual += board.getPlyr(x, y);
            }

            actual += "\n";
        }

        assertEquals(board.toString(), actual);
    }

    /**
     * Test the setting the player's is assigned correctly.
     */
    @Test
    public void testSetPlyr() {
        Board board = new Board();
        board.setPlyr(2, 4, 1);
        board.setPlyr(3, 4, 1);

        String expected = "";
        expected += "00000000\n";
        expected += "00000000\n";
        expected += "00001000\n";
        expected += "00011000\n";
        expected += "00021000\n";
        expected += "00000000\n";
        expected += "00000000\n";
        expected += "00000000\n";

        assertEquals(expected, board.toString());
    }

    /**
     * Test the the next player is correctly identified.
     */
    @Test
    public void testNextPlyr() {
        Board board = new Board();

        board.setCurrPlyr(1);
        board.nextPlyr();
        assertEquals(2, board.getCurrPlyr());

        board.nextPlyr();
        assertEquals(1, board.getCurrPlyr());
    }

    /**
     * Test that the current player is assigned correctly.
     */
    @Test
    public void testGetCurrPlyr() {
        Board board = new Board();

        board.setCurrPlyr(1);
        assertEquals(1, board.getCurrPlyr());

        board.setCurrPlyr(2);
        assertEquals(2, board.getCurrPlyr());
    }

    /**
     * Check if a given coordinate can be a valid move.
     */
    @Test
    public void testIsValidMove() {
        Board board = new Board();
        board.newGame();

        // Current player is black (piece #1)
        // Make black move:
        assertTrue(board.isValidMove(2, 4));
        assertTrue(board.isValidMove(4, 2));

        // Validate invalid moves, any moves that isn't
        //2,4 or 4,2 or 5,3 or 3,5 is invalid
        for (int i = 0; i < board.getBoardPieces().length; i++) {
            for (int j = 0; j < board.getBoardPieces()[i].length; j++) {
                if ((i == 2 && j == 4) 
                		|| (i == 4 && j == 2) 
                		|| (i == 5 && j == 3) 
                		|| (i == 3 && j == 5)) {
                    assertTrue(board.isValidMove(j, i));
                } else {
                    assertFalse(board.isValidMove(j, i));
                }
            }
        }
    }

    /**
     * Make the current player move and check that appropriate cells has been
     * flipped
     */
    @Test
    public void testPlayPiece() {
        Board board = new Board();
        board.newGame();

        // Current player is black (piece #1)
        // Make black move:
        board.playPiece(2, 4);

        String expected = "";
        expected += "00000000\n";
        expected += "00000000\n";
        expected += "00001000\n";
        expected += "00011000\n";
        expected += "00021000\n";
        expected += "00000000\n";
        expected += "00000000\n";
        expected += "00000000\n";

        assertEquals(expected, board.toString());

        // Start a new game, this time make black move
        board.newGame();

        // Current player is white (piece #2)
        // Make white move:
        board.setCurrPlyr(2);
        board.playPiece(2, 3);

        expected = "";
        expected += "00000000\n";
        expected += "00000000\n";
        expected += "00020000\n";
        expected += "00022000\n";
        expected += "00021000\n";
        expected += "00000000\n";
        expected += "00000000\n";
        expected += "00000000\n";

        assertEquals(expected, board.toString());

        // Test an invalid move, make black move in a non-sense cell
        // there shouldn't be any updates
        board.playPiece(0, 0);
        assertEquals(expected, board.toString());

        board.playPiece(3, 1);
        assertEquals(expected, board.toString());
    }

    /**
     * Test the winner. The winner is the most points
     */
    @Test
    public void testWhoWon() {
        Board board = new Board();
        board.newGame();

        // Nobody wins if there's a tie, initial state of the board are
        // 2 equal number of pieces.
        // assertEquals(-1, board.whoWon());

        // Make more whites (piece #2) in the board
        board.getBoardPieces()[2][3] = 2;
        board.getBoardPieces()[3][3] = 2;
        board.getBoardPieces()[4][3] = 2;

        //  assertEquals(2, board.whoWon());

        // Reset the game, make more blacks (piece #1) in the board
        board.newGame();

        board.getBoardPieces()[2][4] = 1;
        board.getBoardPieces()[3][4] = 1;
        board.getBoardPieces()[4][4] = 1;

        //  assertEquals(1, board.whoWon());
    }

    /**
     * Test if can is over. A game is over when both and black cannot move
     */
    @Test
    public void testGameOver() {
        Board board = new Board();

        // Initially game isn't over while someone can move
        //  assertFalse(board.gameOver());

        // Fill the board such that nobody can move
        Random random = new Random();

        for (int i = 0; i < board.getBoardPieces().length; i++) {
            for (int j = 0; j < board.getBoardPieces()[i].length; j++) {
                board.getBoardPieces()[i][j] = random.nextInt(2) + 1;
            }
        }

        // assertTrue(board.gameOver());
    }

    /**
     * After creating a new game, we check if all the defaults has been
     * appropriately set.
     */
    @Test
    public void testNewGame() {
        // Initiate a new game
        Board board = new Board();
        board.newGame();

        // Check the default settings of the board
        // There should be alternating white(2) and black(1)
        // In the middle of the board
        String expected = "";
        expected += "00000000\n";
        expected += "00000000\n";
        expected += "00000000\n";
        expected += "00012000\n";
        expected += "00021000\n";
        expected += "00000000\n";
        expected += "00000000\n";
        expected += "00000000\n";

        assertEquals(expected, board.toString());

        // Check that player 1 goes first
        assertEquals(1, board.getCurrPlyr());
    }

    /**
     * Starting a game by default would lead by default to have both players to
     * have a score of 2. As the games go on, the white score is updated.
     */
    @Test
    public void testWhiteScore() {
        // Initiate a new game
        Board board = new Board();
        board.newGame();

        // Check the default scoring
        // Initial board state is:
        // 00000000
        // 00000000
        // 00000000
        // 00012000
        // 00021000
        // 00000000
        // 00000000
        // 00000000
        assertEquals(2, board.whiteScore());

        // Try and put some whites (piece #2) on the cells:
        // 00000000
        // 00000000
        // 00020000
        // 00022000
        // 00021000
        // 00000000
        // 00000000
        // 00000000
        board.getBoardPieces()[2][3] = 2;
        board.getBoardPieces()[3][3] = 2;
        board.getBoardPieces()[4][3] = 2;

        // White now has a score of 4
        assertEquals(4, board.whiteScore());
    }

    /**
     * Starting a game by default would lead by default to have both players to
     * have a score of 2. As the games go on, the black score is updated.
     */
    @Test
    public void testBlackScore() {
        // Initiate a new game
        Board board = new Board();
        board.newGame();

        // Check the default scoring
        // Initial board state is:
        // 00000000
        // 00000000
        // 00000000
        // 00012000
        // 00021000
        // 00000000
        // 00000000
        // 00000000
        assertEquals(2, board.blackScore());

        // Try and put some black (piece #1) on the cells:
        // 00000000
        // 00000000
        // 00001000
        // 00011000
        // 00021000
        // 00000000
        // 00000000
        // 00000000
        board.getBoardPieces()[2][4] = 1;
        board.getBoardPieces()[3][4] = 1;
        board.getBoardPieces()[4][4] = 1;

        // Black now has a score of 4
        assertEquals(4, board.blackScore());
    }

    /**
     * Check if the current player can move. We try to test different cases on
     * which a player can move and when a player cannot move
     */
    @Test
    public void testCanMove() {
        // Initialize a new game
        Board board = new Board();
        board.newGame();

        // Create a scenario that a player can move
        // After creating a new game, we are on this board scenario:
        // 00000000
        // 00000000
        // 00000000
        // 00012000
        // 00021000
        // 00000000
        // 00000000
        // 00000000
        // The board above should be able to make white move
        assertTrue(board.canMove());

        // Switch the above scenario to make black current player (piece #1)
        // Black should still be able to move
        board.setCurrPlyr(1);
        assertTrue(board.canMove());

        // Fill the board such that nobody can move
        Random random = new Random();

        for (int i = 0; i 
        		< board.getBoardPieces().length; i++) {
            for (int j = 0; j < board.getBoardPieces()[i].length; j++) {
                board.getBoardPieces()[i][j] = random.nextInt(2) + 1;
            }
        }

        // Now that board is full, body should be able to make a move
        board.setCurrPlyr(2);
        assertFalse(board.canMove());

        board.setCurrPlyr(1);
        assertFalse(board.canMove());
    }

    @Test
    public void testGetWinner() {
        //TestBoardPiecesSize should have arguments so the method can be able to enter in other loops
        Board board = new Board();
        //This is the only scenario that It can return, suggestion to add 2 parameters in getWinner()
        //getWinner(int plyrOnePts, int plyrTwoPts) then It can have 3 results 1, 2 and -1
        int expectedResult = -1;
        int actualResult = board.getWinner();
        assertEquals(actualResult, expectedResult);
    }
}
