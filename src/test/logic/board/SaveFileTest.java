package test.logic.board;

import api.BoardTestInterface;
import org.junit.jupiter.api.*;
import logic.*;
import test.logic.helper.SaveFileHelper;

import java.io.File;
import java.io.IOException;

public class SaveFileTest {
    BoardTestInterface board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @AfterEach
    void tearDown() {
        File saveFile = new File("savestats.txt");
        boolean destroyedFileSuccess = saveFile.delete();
        if (!destroyedFileSuccess) {
            throw new IllegalStateException("Unable to delete file");
        }
    }

    @Test
    @DisplayName("GIVEN a board with some turns passed" +
            "WHEN saving and loading the Board" +
            "THEN have the newly loaded Board match the Board at its saving state")
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
    @DisplayName("GIVEN a board with turns passed in a specific order " +
            "WHEN loading the save" +
            "THEN expect the savecode to be 1a6a7a0aB020000001000000200000010000022100001120000")
    void testSaveAndLoadFixedString() {
        board.placeStone(1);
        board.placeStone(2);
        board.placeStone(2);
        board.placeStone(1);
        board.placeStone(1);
        board.placeStone(1);
        board.placeStone(1);
        board.placeStone(1);
        board.placeStone(0);
        board.placeStone(0);
        board.saveBoard();

        String actualSaveCode = "";

        try {
            actualSaveCode = SaveFileHelper.loadSaveStats();
        } catch (IOException e) {
            Assertions.fail(e.getMessage());
        }

        Assertions.assertEquals("1a6a7a0aB020000001000000200000010000022100001120000", actualSaveCode);

    }

    @Test
    @DisplayName("GIVEN a board with some turns passed WHEN saving and loading that save " +
            "THEN have the original Board be the same as the loaded Board")
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
    @DisplayName("GIVEN a full board WHEN saving THEN properly implement the isFull variable")
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

    @Test
    @DisplayName("GIVEN an existing board " +
            "WHEN loading a faulty savecode " +
            "THEN maintain the original board and disregard the savefile")
    void testSaveWithFaultyValues() {
        board.placeStone(1);
        board.placeStone(1);
        board.placeStone(1);
        BoardTestInterface preLoadedBoard = board;
        try {
            SaveFileHelper.writeSaveStats("ABZDEF");
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
        try {
            board.overwriteVariableWithSavestats();
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
        Assertions.assertEquals(preLoadedBoard, board, "Invalid has been loaded and overwritten something");
    }

    @Test
    @DisplayName("GIVEN a just initialized board WHEN calling load save THEN do nothing")
    void testLoadingOfNoSaveFile(){
        BoardTestInterface existingBoard = board;
        try {
            board.overwriteVariableWithSavestats();
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
        Assertions.assertEquals(existingBoard, board, "Invalid has been loaded and overwritten something");
    }

    @Test
    @DisplayName("GIVEN an empty board WHEN saving THEN expect the saveCode to be")
    void testSaveCodeOnBoardWithNoTurns(){
        board.placeStone(0);
        board.placeStone(0);
        BoardTestInterface originalBoard = board;
        String actualSaveCode = "1a6a7a0aB000000000000000000000000000000000000000000";
        try {
            SaveFileHelper.writeSaveCode(actualSaveCode);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
        board.overwriteVariableWithSavestats();
        Assertions.assertEquals(board, originalBoard, "Invalid has been loaded and overwritten something");
    }
}
