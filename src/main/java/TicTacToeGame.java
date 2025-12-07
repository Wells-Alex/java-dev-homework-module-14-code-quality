import java.util.Random;
import java.util.Scanner;

import static java.lang.System.*;

public class TicTacToeGame {

    public static void main(String[] args) {
        Board board = new Board();
        Scanner scanner = new Scanner(in);
        Random random = new Random();

        out.println("Welcome to Tic-Tac-Toe! Enter box number (1-9). Enjoy!");

        boolean gameOver = false;

        while (!gameOver) {
            board.print();

            // ход игрока
            int playerIndex = readPlayerMove(scanner, board);
            board.setMove(playerIndex, Board.PLAYER_MARK);

            // проверка победы или ничьи после хода игрока
            if (board.isWin(Board.PLAYER_MARK)) {
                board.print();
                out.println("You won the game! Thanks for playing!");
                gameOver = true;
            } else if (board.isFull()) {
                board.print();
                out.println("It's a draw! Thanks for playing!");
                gameOver = true;
            } else {
                // ход компьютера
                int computerIndex = board.getRandomEmptyCell(random);
                if (computerIndex >= 0) {
                    board.setMove(computerIndex, Board.COMPUTER_MARK);
                    out.println("Computer chose: " + (computerIndex + 1));
                }

                // проверка победы или ничьи после хода компьютера
                if (board.isWin(Board.COMPUTER_MARK)) {
                    board.print();
                    out.println("You lost the game! Thanks for playing!");
                    gameOver = true;
                } else if (board.isFull()) {
                    board.print();
                    out.println("It's a draw! Thanks for playing!");
                    gameOver = true;
                }
            }
        }

        scanner.close();
    }

    private static int readPlayerMove(Scanner scanner, Board board) {
        int index = -1;
        boolean validInput = false;

        while (!validInput) {
            out.print("Your move (1-9): ");
            String line = scanner.nextLine().trim();

            int choice = -1;
            try {
                choice = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                out.println("Invalid input. Enter a number from 1 to 9.");
            }

            index = choice - 1;

            if (choice >= 1 && choice <= 9) {
                if (board.isCellEmpty(index)) {
                    validInput = true; // корректный ввод — выходим из цикла
                } else {
                    out.println("That one is already in use. Enter another.");
                }
            } else if (choice != -1) {
                out.println("Invalid input. Enter a number from 1 to 9.");
            }
        }

        return index;
    }
}