package test.logic.board;

import api.BoardTestInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import logic.*;


public class MiniMaxTest {
    @BeforeEach
    void setUp() {
        BoardTestInterface board = new Board();
    }

    @Test
    @DisplayName("MiniMax Test")
    void miniMaxTest() {}
}
