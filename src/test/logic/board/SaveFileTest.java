package test.logic.board;

import api.BoardTestInterface;
import org.junit.jupiter.api.*;
import logic.*;

public class SaveFileTest {
    BoardTestInterface board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    @DisplayName("Save and Load Game State")
    void testSaveAndLoadGameState() {
        board.placeStone(1);
        board.placeStone(1);
        board.placeStone(1);
        board.placeStone(1);
        board.placeStone(1);
        board.placeStone(1);
        board.saveBoard();

        BoardTestInterface loadedBoard = new Board();
        loadedBoard.overwriteVariableWithSavestats();

        System.out.print("Loaded Board: \n" + loadedBoard.toString() + "\n");
        System.out.println("Original Board: \n" + board.toString() + "\n");

        Assertions.assertEquals(loadedBoard.toString(), board.toString());

    }
}
