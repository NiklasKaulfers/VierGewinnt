package test.logic.board;

import api.BoardTestInterface;
import org.junit.jupiter.api.*;
import logic.*;
import test.logic.helper.SaveFileHelper;

import java.io.IOException;

public class SaveFileTest {
    BoardTestInterface board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    @DisplayName("Test successful loading and saving")
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

        Assertions.assertEquals(board.toString(), loadedBoard.toString());
    }

    @Test
    @DisplayName("Test correct creation of savecode")
    void testSaveCode() {
        board.placeStone(1);
        board.placeStone(1);
        board.placeStone(1);
        board.placeStone(1);
        board.placeStone(1);
        board.placeStone(1);
        board.saveBoard();

        String actualSaveCode = "";

        try {
            actualSaveCode = SaveFileHelper.loadSaveStats();
        } catch (IOException e) {
            Assertions.fail(e.getMessage());
        }

        String expectedSaveCode = SaveFileHelper.getSaveCodeFromBoard(board);
        Assertions.assertEquals(expectedSaveCode, actualSaveCode);
    }

    @Test
    @DisplayName("Test detection of full board and it being saved properly")
    void testDetectionOfFullBoard() {
        for  (int i = 0; i < board.getrows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                board.placeStone(j);
            }
        }
        Assertions.assertTrue(board.getIsFull(), "Board is not full");
        board.saveBoard();

        String saveCode = "";

        try {
            saveCode = SaveFileHelper.loadSaveStats();
        } catch (IOException e) {
            Assertions.fail(e.getMessage());
        }
        Assertions.assertEquals(SaveFileHelper.getSaveCodeFromBoard(board), saveCode);
    }

}
