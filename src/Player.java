import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {
    private char whoseNextMove = ' ';

    public void playerSelection() {
        System.out.print("Ожидается ввод 1 или 2: ");
        Scanner reader = new Scanner(System.in);
        int numberOfUsers = 0;

        try {
            numberOfUsers = reader.nextInt();
        }
        catch (InputMismatchException e){}


        if (numberOfUsers == 1) {
            whoseNextMove = 'X';
        } else if (numberOfUsers == 2) {
            whoseNextMove = 'O';
        } else {
            System.out.println("Получен не верный параметр...");
            System.out.println();
            playerSelection();
        }
    }

    public char getWhoseNextMove() {
        return whoseNextMove;
    }

    public void changePlayer() {
        if (whoseNextMove == 'X') {
            whoseNextMove = 'O';
        } else {
            whoseNextMove = 'X';
        }
    }

    public void printWhoseMove() {
        System.out.println("Ходит игрок - \"" + whoseNextMove + "\"");
    }
}
