package test.logic.board;

import api.BoardTestInterface;
import org.junit.jupiter.api.*;
import logic.*;

import java.io.FileReader;

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

        System.out.print("Loaded Board: \n" + loadedBoard.toString() + "\n");
        System.out.println("Original Board: \n" + board.toString() + "\n");

        Assertions.assertEquals(loadedBoard.toString(), board.toString());
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

        StringBuilder stringBuilder = new StringBuilder();
        FileReader reader = null;
        int ch;

        try {
            reader = new FileReader("savestats.txt");
            while ((ch = reader.read()) != -1) {
                stringBuilder.append((char) ch);
            }
            reader.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            Assertions.assertNull(e, "File reader error, Check if file exists");
            return;
        }

        int playerTurn = board.getTurn() ? 1 : 2;
        int rows = board.getrows();
        int columns = board.getColumns();
        int isFull = board.getIsFull() ? 1 : 0;

        // all positions in the board from top left to bottom right
        String boardLayout = board.toString()
                .replaceAll("\\[", "")
                .replaceAll("]", "")
                .replaceAll("\n", "");

        String saveCode = playerTurn + "a" + rows + "a" + columns + "a" + isFull + "aB" + boardLayout;
        Assertions.assertEquals(saveCode, stringBuilder.toString());
    }
}
