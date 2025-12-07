import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.lang.System.*;

public class Board {
    public static final int BOARD_SIZE = 9;
    public static final char EMPTY = ' ';
    public static final char PLAYER_MARK = 'X';
    public static final char COMPUTER_MARK = 'O';

    private final char[] cells = new char[BOARD_SIZE];

    // все выигрышные комбинации (индексы)
    private static final int[][] WIN_COMBINATIONS = {
            {0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}
    };

    public Board() {
        reset();
    }

    public void reset() {
        Arrays.fill(cells, EMPTY);
    }

    public void setMove(int index, char mark) {
        if (index >= 0 && index < BOARD_SIZE && cells[index] == EMPTY) {
            cells[index] = mark;
        }
    }

    public boolean isCellEmpty(int index) {
        return index >= 0 && index < BOARD_SIZE && cells[index] == EMPTY;
    }

    public boolean isFull() {
        for (char c : cells) {
            if (c == EMPTY) return false;
        }
        return true;
    }

    public boolean isWin(char mark) {
        for (int[] combo : WIN_COMBINATIONS) {
            if (cells[combo[0]] == mark &&
                    cells[combo[1]] == mark &&
                    cells[combo[2]] == mark) {
                return true;
            }
        }
        return false;
    }

    public int getRandomEmptyCell(Random rnd) {
        List<Integer> emptyIndices = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (cells[i] == EMPTY) emptyIndices.add(i);
        }
        if (emptyIndices.isEmpty()) return -1;
        return emptyIndices.get(rnd.nextInt(emptyIndices.size()));
    }

    public void print() {
        out.println();
        out.println(" " + displayChar(0) + " | " + displayChar(1) + " | " + displayChar(2) + " ");
        out.println("---+---+---");
        out.println(" " + displayChar(3) + " | " + displayChar(4) + " | " + displayChar(5) + " ");
        out.println("---+---+---");
        out.println(" " + displayChar(6) + " | " + displayChar(7) + " | " + displayChar(8) + " ");
        out.println();
    }

    private String displayChar(int index) {
        return cells[index] == EMPTY ? String.valueOf(index + 1) : String.valueOf(cells[index]);
    }
}