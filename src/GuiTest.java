import org.junit.Test;

import java.awt.event.ActionEvent;

import static org.junit.Assert.assertNotNull;

public class GuiTest {

    /*
    *
    * Test cons regarding black&white scores
    * */
    @Test
    public void testGuiCons() {
        Board board = new Board();
        board.blackScore();
        board.whiteScore();
        Gui guiTest = new Gui(board);

        assertNotNull(guiTest);
        }


        /*
        *
        * Test actionPerformed, cons and GUI
        *
        * */
    @Test
    public void actionPerformed() {
        Board board = new Board();
        Gui guiTest = new Gui(board);
        guiTest.actionPerformed(new ActionEvent(true, 1, "Testing action event"));
    }
}
