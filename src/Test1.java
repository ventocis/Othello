
import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Test;

public class Test1 {

  @Test
    public void testConcatenate() {
    Color c = new Color(0);
    CheckersPiece myUnit = new CheckersPiece(c,1,1);
    String color = myUnit.getColor().toString();
    int row = myUnit.getRow();
    int col = myUnit.getCol();
    assertEquals("java.awt.Color[r=0,g=0,b=0]", color);
    assertEquals(1, row);
    assertEquals(1, col);
  }
}
