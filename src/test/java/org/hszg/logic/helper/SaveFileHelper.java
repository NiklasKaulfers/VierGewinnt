package org.hszg.logic.helper;

import org.hszg.api.BoardTestInterface;
import org.junit.jupiter.api.Assertions;

import java.io.FileReader;
import java.io.IOException;

public class SaveFileHelper {

    private SaveFileHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static String loadSaveStats() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int charCode;

        try (FileReader reader = new FileReader("savestats.txt")) {
            while ((charCode = reader.read()) != -1) {
                stringBuilder.append((char) charCode);
            }
        } catch (IOException e) {
            Assertions.fail("File reader error, Check if file exists");
        }
        return stringBuilder.toString();
    }

    public static String getSaveCodeFromBoard(BoardTestInterface board) {
        int playerTurn = board.getTurn() ? 1 : 2;
        int rows = board.getrows();
        int columns = board.getColumns();
        int isFull = board.getIsFull() ? 1 : 0;

        // all positions in the board from top left to bottom right
        String boardLayout = board.toString()
                .replace("[", "")
                .replace("]", "")
                .replace("\n", "");

        return playerTurn + "a" + rows + "a" + columns + "a" + isFull + "aB" + boardLayout;
    }
}
