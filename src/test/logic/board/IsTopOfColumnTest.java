package test.logic.board;

import api.BoardTestInterface;
import logic.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IsTopOfColumnTest {

    BoardTestInterface board;
    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    @DisplayName("WHEN column 1 had 6 stones placed THEN return 0")
    void fullColumnTest(){
        board.placeStone(1);
        board.placeStone(1);
        board.placeStone(1);
        board.placeStone(1);
        board.placeStone(1);
        board.placeStone(1);
        board.placeStone(1);

        Assertions.assertEquals(board.isTopOfColumn(1), 0);
    }

}
