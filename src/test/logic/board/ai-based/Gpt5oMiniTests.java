package test.logic.board;

import api.BoardTestInterface;
import logic.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Gpt5oMiniTests {

    BoardTestInterface board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    @DisplayName("WHEN column 0 is empty THEN return number of rows (6)")
    void emptyColumnZero() {
        Assertions.assertEquals(6, board.isTopOfColumn(0));
    }

    @Test
    @DisplayName("WHEN column 2 has 3 stones placed THEN return 3")
    void partlyFilledColumn() {
        board.placeStone(2);
        board.placeStone(2);
        board.placeStone(2);

        Assertions.assertEquals(3, board.isTopOfColumn(2));
    }

    @Test
    @DisplayName("WHEN column 3 is filled completely THEN return 0")
    void fullColumn() {
        for (int i = 0; i < board.getrows(); i++) {
            board.placeStone(3);
        }
        Assertions.assertEquals(0, board.isTopOfColumn(3));
    }

    @Test
    @DisplayName("WHEN negative column index is given THEN return 0 (out of bounds)")
    void negativeColumnIndex() {
        Assertions.assertEquals(0, board.isTopOfColumn(-1));
    }

    @Test
    @DisplayName("WHEN column index is greater than last column THEN return 0 (out of bounds)")
    void tooLargeColumnIndex() {
        // default board has 7 columns, last index is 6 -> use 7 as out of bounds
        Assertions.assertEquals(0, board.isTopOfColumn(7));
    }
}

