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
    @DisplayName("GIVEN board with dimensions 6x6")
    void setUp() {
        board = new Board();
    }

    @Test
    @DisplayName("WHEN column 1 has 3 stones placed THEN return 3")
    void halfwayFullColumnTest(){
        board.placeStone(1);
        board.placeStone(1);
        board.placeStone(1);


        Assertions.assertEquals(3, board.isTopOfColumn(1));
    }

    @Test
    @DisplayName("WHEN column 1 has 6 stones THEN return 0")
    void fullColumnTest(){
        for (int i = 0; i < 6; i++) {
            board.placeStone(i);
        }

        Assertions.assertEquals(0, board.isTopOfColumn(1));
    }

    @Test
    @DisplayName("WHEN column 1 has 0 stones THEN return 6")
    void emptyColumnTest(){
        Assertions.assertEquals(6, board.isTopOfColumn(1));
    }

    @Test
    @DisplayName("GIVEN a 10x10 board WHEN column 10 has 0 stones THEN return 10")
    void emptyColumn10Test(){
        board = new Board(10, 10);
        Assertions.assertEquals(10, board.isTopOfColumn(9));
    }

    @Test
    @DisplayName("GIVEN a 10x10 board WHEN column 1 has 10 stones THEN return 0")
    void fullColumn10Test(){
        board = new Board(10, 10);
        for (int i = 0; i < 10; i++) {
            board.placeStone(1);
        }

        Assertions.assertEquals(0, board.isTopOfColumn(1));
    }

    @Test
    @DisplayName("WHEN column -1 is called THEN do nothing")
    void nothingTest(){
        Assertions.assertEquals(0, board.isTopOfColumn(-1));
    }

    @Test
    @DisplayName("WHEN column 7 is called THEn do nothing")
    void nothing7Test(){
        Assertions.assertEquals(0, board.isTopOfColumn(7));
    }
}
