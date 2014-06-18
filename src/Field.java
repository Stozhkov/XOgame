import java.util.InputMismatchException;
import java.util.Scanner;

public class Field {

    private static final int FIELD_SIZE = 3;
    private static final char DEFAULT_FIELD_VALUE = ' ';
    private char field[][] = new char[FIELD_SIZE][FIELD_SIZE];

    Field() {

        setFieldsDefault();

    }

    public void showFields() {
        for (int i = 0; i < FIELD_SIZE; i++){
            for (int j = 0; j < FIELD_SIZE; j++)
            {
                System.out.print("[" + field[i][j] + "]");
            }
            System.out.print("\n");
        }
    }

    private void setFieldsDefault() {
        for (int i = 0; i < FIELD_SIZE; i++){
            for (int j = 0; j < FIELD_SIZE; j++)
            {
                field[i][j] = DEFAULT_FIELD_VALUE;
            }
        }
    }

    private void setFields(int verticalCoordinate, int horizontalCoordinate, char whoseNextMove) {
        if (field[verticalCoordinate][horizontalCoordinate] == DEFAULT_FIELD_VALUE) {
            field[verticalCoordinate][horizontalCoordinate] = whoseNextMove;
        } else {
            System.out.println("Данная ячейка занята. Выберете другую.");
            movePlayer(whoseNextMove);
        }
    }

    public void movePlayer(char whoseNextMove) {
        System.out.print("Введите координату по вертикальной оси от 1 до " + FIELD_SIZE + ":");
        int verticalCoordinate = getPlayerNum();
        System.out.print("Введите координату по горизонтальной оси от 1 до " + FIELD_SIZE + ":");
        int horizontalCoordinate = getPlayerNum();
        System.out.println();
        setFields(verticalCoordinate - 1, horizontalCoordinate - 1, whoseNextMove);
    }

    private int getPlayerNum() {
        Scanner reader = new Scanner(System.in);
        int playerNum;

        try {
            playerNum = reader.nextInt();
        }
        catch (InputMismatchException e)
        {
            System.out.print("Вы ввели не число. Введите еще раз: ");
            playerNum = getPlayerNum();
        }

        if (playerNum > 0 && playerNum <= FIELD_SIZE) {
            return playerNum;
        }
        else {
            System.out.print("Введенное вами число выходит за пределы поля. Введите еще раз: ");
        }

        return getPlayerNum();
    }



    public boolean validation (char whoseNextMove) {
        if (checkLines() || checkColumns() || checkDiagonalLeft() || checkDiagonalRight()) {
            System.out.println("Победил игрок \"" + whoseNextMove + "\"");
            return true;
        }

        if (checkNoMoves()) {
            System.out.println("Ходов больше нет.");
            System.out.println("Победила дружба! ;-)");
            return true;
        }

        return false;
    }

    private boolean checkLines() {
        for (int i = 0; i < FIELD_SIZE; i++) {

            char cell = 0;

            if (field[i][0] != DEFAULT_FIELD_VALUE) {
                cell = field[i][0];
            }
            for (int j = 1; j < FIELD_SIZE; j++) {
                if (field[i][j] == cell) {
                    if (j == (FIELD_SIZE - 1)) {
                        return true;
                    }
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    private boolean checkColumns() {
        for (int j = 0; j < FIELD_SIZE; j++) {

            char cell = 0;

            if (field[0][j] != DEFAULT_FIELD_VALUE) {
                cell = field[0][j];
            }
            for (int i = 1; i < FIELD_SIZE; i++) {
                if (field[i][j] == cell) {
                    if (i == (FIELD_SIZE - 1)) {
                        return true;
                    }
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonalLeft() {

        char cell;

        if (field[0][0] != DEFAULT_FIELD_VALUE) {
            cell = field[0][0];
        } else {
            return false;
        }

        for (int i = 1; i < FIELD_SIZE; i++) {

            if (field[i][i] == cell) {
                if (i == (FIELD_SIZE - 1)) {
                    return true;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    private boolean checkDiagonalRight() {

        char cell;

        if (field[FIELD_SIZE-1][0] != DEFAULT_FIELD_VALUE) {
            cell = field[FIELD_SIZE-1][0];
        } else {
            return false;
        }

        for (int i = 1; i < FIELD_SIZE; i++)
            if (field[FIELD_SIZE-1-i][i] == cell) {
                if (i == (FIELD_SIZE - 1)) {
                    return true;
                }
            } else {
                return false;
            }
        return false;
    }

    private boolean checkNoMoves() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++){
                if (field[i][j] == DEFAULT_FIELD_VALUE) {
                    return false;
                }
            }
        }
        return true;
    }
}