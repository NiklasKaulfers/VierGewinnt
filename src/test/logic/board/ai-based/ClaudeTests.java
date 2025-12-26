package logic;

import api.TileInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class BoardIsTopOfColumnTest {

    private Board board;

    @BeforeEach
    void setUp() {
        // Create a standard 6x7 board before each test
        board = new Board();
    }

    @Test
    @DisplayName("Empty column should return the bottom row + 1")
    void testEmptyColumn() {
        // All columns in a new board are empty
        // For a 6-row board, bottom row is index 5, so should return 6
        int result = board.isTopOfColumn(0);
        assertEquals(6, result, "Empty column should return board.length (6)");
    }

    @Test
    @DisplayName("Column with one stone should return correct position")
    void testColumnWithOneStone() {
        // Place one stone in column 3
        board.placeStone(3);

        // After placing one stone at the bottom, top should be at row 5 (index 4 + 1)
        int result = board.isTopOfColumn(3);
        assertEquals(5, result, "Column with one stone should return 5");
    }

    @Test
    @DisplayName("Column with multiple stones should return correct position")
    void testColumnWithMultipleStones() {
        // Place three stones in column 2
        board.placeStone(2);
        board.placeStone(2);
        board.placeStone(2);

        // After placing three stones, top should be at row 3 (index 2 + 1)
        int result = board.isTopOfColumn(2);
        assertEquals(3, result, "Column with three stones should return 3");
    }

    @Test
    @DisplayName("Almost full column should return 1")
    void testAlmostFullColumn() {
        // Fill column 4 with 5 stones (one space left at top)
        for (int i = 0; i < 5; i++) {
            board.placeStone(4);
        }

        int result = board.isTopOfColumn(4);
        assertEquals(1, result, "Almost full column should return 1");
    }

    @Test
    @DisplayName("Completely full column should return 0")
    void testFullColumn() {
        // Fill column 1 completely (6 stones)
        for (int i = 0; i < 6; i++) {
            board.placeStone(1);
        }

        int result = board.isTopOfColumn(1);
        assertEquals(0, result, "Full column should return 0");
    }

    @Test
    @DisplayName("First column (index 0) should work correctly")
    void testFirstColumn() {
        int result = board.isTopOfColumn(0);
        assertEquals(6, result, "First column when empty should return 6");

        board.placeStone(0);
        result = board.isTopOfColumn(0);
        assertEquals(5, result, "First column with one stone should return 5");
    }

    @Test
    @DisplayName("Last column (index 6) should work correctly")
    void testLastColumn() {
        int result = board.isTopOfColumn(6);
        assertEquals(6, result, "Last column when empty should return 6");

        board.placeStone(6);
        result = board.isTopOfColumn(6);
        assertEquals(5, result, "Last column with one stone should return 5");
    }

    @Test
    @DisplayName("Different columns should not affect each other")
    void testColumnsAreIndependent() {
        // Place stones in column 2
        board.placeStone(2);
        board.placeStone(2);

        // Column 3 should still be empty
        int resultColumn3 = board.isTopOfColumn(3);
        assertEquals(6, resultColumn3, "Adjacent empty column should still return 6");

        // Column 2 should show 2 stones
        int resultColumn2 = board.isTopOfColumn(2);
        assertEquals(4, resultColumn2, "Column with stones should return correct value");
    }

    @Test
    @DisplayName("Custom board size should work correctly")
    void testCustomBoardSize() {
        // Create a custom 4x5 board (4 rows, 5 columns)
        Board customBoard = new Board(5, 4);

        // Empty column should return 4 (number of rows)
        int result = customBoard.isTopOfColumn(0);
        assertEquals(4, result, "Empty column in 4-row board should return 4");

        // Fill the column completely
        for (int i = 0; i < 4; i++) {
            customBoard.placeStone(0);
        }

        result = customBoard.isTopOfColumn(0);
        assertEquals(0, result, "Full column in custom board should return 0");
    }

    @Test
    @DisplayName("isTopOfColumn should work correctly after mixed placements")
    void testMixedPlacements() {
        // Create a pattern: column 0: 2 stones, column 1: 4 stones, column 2: 1 stone
        board.placeStone(0);
        board.placeStone(1);
        board.placeStone(0);
        board.placeStone(1);
        board.placeStone(2);
        board.placeStone(1);
        board.placeStone(1);

        assertEquals(4, board.isTopOfColumn(0), "Column 0 should have 2 stones (return 4)");
        assertEquals(2, board.isTopOfColumn(1), "Column 1 should have 4 stones (return 2)");
        assertEquals(5, board.isTopOfColumn(2), "Column 2 should have 1 stone (return 5)");
        assertEquals(6, board.isTopOfColumn(3), "Column 3 should be empty (return 6)");
    }

    @Test
    @DisplayName("Manually setting board state should work with isTopOfColumn")
    void testManuallySetBoard() {
        TileInterface[][] customBoard = new Tile[6][7];

        // Initialize all tiles as empty
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                customBoard[i][j] = new Tile();
            }
        }

        // Manually set bottom 3 positions of column 2 to be occupied
        customBoard[5][2].setStatus(1);
        customBoard[4][2].setStatus(2);
        customBoard[3][2].setStatus(1);

        board.setBoard(customBoard);

        int result = board.isTopOfColumn(2);
        assertEquals(3, result, "Column with manually set 3 stones should return 3");
    }
}