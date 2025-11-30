package org.hszg.logic.board;

import org.hszg.api.BoardTestInterface;
import org.hszg.logic.Board;
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
    @DisplayName("GIVEN a board that allows with the default parameters (7-7) WHEN column 1 had 6 stones placed THEN return 0")
    void fullColumnTest(){
        board.placeStone(1);
        board.placeStone(1);
        board.placeStone(1);
        board.placeStone(1);
        board.placeStone(1);
        board.placeStone(1);

        Assertions.assertEquals(0, board.isTopOfColumn(1));
    }

}
