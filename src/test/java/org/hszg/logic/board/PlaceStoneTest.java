package org.hszg.logic.board;

import org.hszg.api.BoardTestInterface;
import org.hszg.logic.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlaceStoneTest {

    // this test causes the behaviour as is in the code, however this is not best practice
    // there is a need to implement this method again, so that there are no unhandled errors with this and a graceful avoidance method instead
    @Test
    @DisplayName("out of bounds check (above)")
    void boardPlaceStoneOutOfBoundsAbove() {
        BoardTestInterface board = new Board();
        IndexOutOfBoundsException error = null;
        try {
            board.placeStone(999);
        } catch (IndexOutOfBoundsException e) {
            error = e;
        }
        assertNotNull(error);
    }

    @Test
    @DisplayName("out of bounds check (below)")
    void boardPlaceStoneOutOfBoundsBelow() {
        BoardTestInterface board = new Board();
        IndexOutOfBoundsException error = null;
        try {
            board.placeStone(-1);
        } catch (IndexOutOfBoundsException e) {
            error = e;
        }
        assertNotNull(error);
    }

    @Test
    @DisplayName("valid placing check")
    void boardPlaceStoneValidPlacing() {
        BoardTestInterface board = new Board();
        board.placeStone(0);
        board.placeStone(0);
        board.placeStone(1);
        board.placeStone(2);

        assertEquals(1, board.getBoard()[board.getrows() - 1][0].getStatus());
        assertEquals(1, board.getBoard()[board.getrows() - 1][1].getStatus());
        assertEquals(2, board.getBoard()[board.getrows() - 2][0].getStatus());
        assertEquals(0, board.getBoard()[board.getrows() - 2][1].getStatus());
        assertEquals(0, board.getBoard()[board.getrows() - 3][0].getStatus());
    }

    @Test
    @DisplayName("place too many stones on top of one another")
    void BoardPlaceStoneAtIndex0() {
        BoardTestInterface board = new Board();
        IllegalArgumentException error = null;

        try {
            for (int i = 0; i < 100; i++) {
                board.placeStone(0);
            }
        } catch (IllegalArgumentException e) {
            error = e;
        }
        assertNotNull(error);
        assertEquals("Column is full", error.getMessage());
    }
}
