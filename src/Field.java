import java.util.Random;

public class Field {

    private static final char DEFAULT_FIELD_VALUE = ' ';
    private final int fieldSize;
    private char field[][];
    private int verticalCoordinate;
    private int horizontalCoordinate;

    public Field(int fieldSize) {

        this.fieldSize = fieldSize;
        field = new char[fieldSize][fieldSize];
        setFieldsDefault();
    }

    public int getVerticalCoordinate() {
        return verticalCoordinate + 1;
    }

    public int getHorizontalCoordinate() {
        return horizontalCoordinate + 1;
    }

    private void setFieldsDefault() {

        for (int i = 0; i < fieldSize; i++){
            for (int j = 0; j < fieldSize; j++)
            {
                field[i][j] = DEFAULT_FIELD_VALUE;
            }
        }
    }

    public int getFieldSize() {

        return fieldSize;
    }

    public void showFields() {

        for (int i = 0; i < fieldSize; i++){
            for (int j = 0; j < fieldSize; j++)
            {
                System.out.print("[" + field[i][j] + "]");
            }
            System.out.print("\n");
        }
    }

    public boolean setFields(int verticalCoordinate, int horizontalCoordinate, char whoseNextMove) {

        if (field[verticalCoordinate][horizontalCoordinate] == DEFAULT_FIELD_VALUE) {
            field[verticalCoordinate][horizontalCoordinate] = whoseNextMove;
            return true;
        } else {
            System.out.println("Данная ячейка занята. Выберете другую.");
            return false;
        }
    }

    public boolean validation (char whoseNextMove) {

        if (checkLines(whoseNextMove) ||
                checkColumns(whoseNextMove) ||
                    checkDiagonalLeft(whoseNextMove) ||
                        checkDiagonalRight(whoseNextMove)) {
            System.out.println("Игра окончена. Победил игрок \"" + whoseNextMove + "\"");
            return true;
        }

        if (!checkNoMoves()) {
            System.out.println("Игра окончена. Ходов больше нет.");
            System.out.println("Победила дружба! ;-)");
            return true;
        }

        return false;
    }

    private boolean checkLines(int whoseNextMove) {

        int sumWinningLine = whoseNextMove * fieldSize;
        boolean checkResult = false;
        int sumCheckedLine;

        for (int i = 0; i < fieldSize; i++) {

            sumCheckedLine = 0;

            for (int j = 0; j < fieldSize; j++) {
                sumCheckedLine += field[i][j];
            }

            if (sumCheckedLine == sumWinningLine) {
                checkResult = true;
            }
        }

        return checkResult;
    }

    private boolean checkColumns(int whoseNextMove) {

        int sumWinningLine = whoseNextMove * fieldSize;
        boolean checkResult = false;
        int sumCheckedLine;

        for (int j = 0; j < fieldSize; j++) {

            sumCheckedLine = 0;

            for (int i = 0; i < fieldSize; i++) {
                sumCheckedLine += field[i][j];
            }

            if (sumCheckedLine == sumWinningLine) {
                checkResult = true;
            }
        }

        return checkResult;
    }

    private boolean checkDiagonalLeft(int whoseNextMove) {

        int sumWinningLine = whoseNextMove * fieldSize;
        boolean checkResult = false;
        int sumCheckedLine = 0;

        for (int i = 0; i < fieldSize; i++) {

            sumCheckedLine += field[i][i];
        }

        if (sumCheckedLine == sumWinningLine) {
            checkResult = true;
        }

        return checkResult;
    }

    private boolean checkDiagonalRight(int whoseNextMove) {

        int sumWinningLine = whoseNextMove * fieldSize;
        boolean checkResult = false;
        int sumCheckedLine = 0;

        for (int i = 0; i < fieldSize; i++) {
            sumCheckedLine += field[(fieldSize - 1) - i][i];
        }

        if (sumCheckedLine == sumWinningLine) {
            checkResult = true;
        }

        return checkResult;
    }

    private boolean checkNoMoves() {

        boolean checkResult = false;

        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++){
                if (field[i][j] == DEFAULT_FIELD_VALUE) {
                    checkResult = true;
                }
            }
        }

        return checkResult;
    }

    public void think(char whoseNextMove, char opponent) {

        if (checkLinesWinningMove(whoseNextMove) ||
                checkColumnsWinningMove(whoseNextMove) ||
                checkDiagonalLeftWinningMove(whoseNextMove) ||
                checkDiagonalRightWinningMove(whoseNextMove)) {
            System.out.println("Вижу выигрышную комбинацию");
            setFields(verticalCoordinate, horizontalCoordinate, whoseNextMove);
        } else if (checkLinesDefensiveMove(opponent) ||
                checkColumnsDefensiveMove(opponent) ||
                checkDiagonalLeftDefensiveMove(opponent) ||
                checkDiagonalRightDefensiveMove(opponent)) {
            System.out.println("Вижу надо защищаться");
            setFields(verticalCoordinate, horizontalCoordinate, whoseNextMove);
        } else {
            randomComputerMove(whoseNextMove);
        }

    }

    private void randomComputerMove(char whoseNextMove) {

        Random random = new Random();
        verticalCoordinate = random.nextInt(fieldSize);
        horizontalCoordinate = random.nextInt(fieldSize);

        if (field[verticalCoordinate][horizontalCoordinate] == DEFAULT_FIELD_VALUE) {
            field[verticalCoordinate][horizontalCoordinate] = whoseNextMove;
        } else {
            randomComputerMove(whoseNextMove);
        }
    }

    private boolean checkLinesWinningMove(char whoseNextMove) {

        int sumWinningLine = (whoseNextMove * (fieldSize - 1)) + (int)DEFAULT_FIELD_VALUE;
        int winningLines = -1;
        boolean checkResult = false;
        int sumCheckedLine;

        for (int i = 0; i < fieldSize; i++) {

            sumCheckedLine = 0;

            for (int j = 0; j < fieldSize; j++) {
                sumCheckedLine += field[i][j];
            }

            if (sumCheckedLine == sumWinningLine) {
                checkResult = true;
                winningLines = i;
                verticalCoordinate = winningLines;
                break;
            }
        }

        if (winningLines != -1) {
            for (int j = 0; j < fieldSize; j++) {
                if (field[winningLines][j] == DEFAULT_FIELD_VALUE) {
                    horizontalCoordinate = j;
                }
            }
        }

        return checkResult;
    }

    private boolean checkColumnsWinningMove(char whoseNextMove) {

        int sumWinningLine = (whoseNextMove * (fieldSize - 1)) + (int)DEFAULT_FIELD_VALUE;
        int winningColumns = -1;
        boolean checkResult = false;
        int sumCheckedLine;

        for (int j = 0; j < fieldSize; j++) {

            sumCheckedLine = 0;

            for (int i = 0; i < fieldSize; i++) {
                sumCheckedLine += field[i][j];
            }

            if (sumCheckedLine == sumWinningLine) {
                checkResult = true;
                winningColumns = j;
                horizontalCoordinate = winningColumns;
                break;
            }
        }

        if (winningColumns != -1) {
            for (int i = 0; i < fieldSize; i++) {
                if (field[i][winningColumns] == DEFAULT_FIELD_VALUE) {
                    verticalCoordinate = i;
                }
            }
        }

        return checkResult;
    }

    private boolean checkDiagonalLeftWinningMove(int whoseNextMove) {

        int sumWinningDiagonal = (whoseNextMove * (fieldSize - 1)) + (int)DEFAULT_FIELD_VALUE;
        boolean checkResult = false;
        int sumCheckedDiagonal = 0;

        for (int i = 0; i < fieldSize; i++) {
            sumCheckedDiagonal += field[i][i];
        }

        if (sumCheckedDiagonal == sumWinningDiagonal) {
            checkResult = true;

            for (int i = 0; i < fieldSize; i++) {
                if (field[i][i] == DEFAULT_FIELD_VALUE) {
                    verticalCoordinate = i;
                    horizontalCoordinate = i;
                }
            }
        }

        return checkResult;

    }

    private boolean checkDiagonalRightWinningMove(int whoseNextMove) {

        int sumWinningDiagonal = (whoseNextMove * (fieldSize - 1)) + (int)DEFAULT_FIELD_VALUE;
        boolean checkResult = false;
        int sumCheckedDiagonal = 0;

        for (int i = 0; i < fieldSize; i++) {
            sumCheckedDiagonal += field[(fieldSize - 1) - i][i];
        }

        if (sumCheckedDiagonal == sumWinningDiagonal) {
            checkResult = true;

            for (int i = 0; i < fieldSize; i++) {
                if (field[(fieldSize - 1) - i][i] == DEFAULT_FIELD_VALUE) {
                    verticalCoordinate = (fieldSize - 1) - i;
                    horizontalCoordinate = i;
                }
            }
        }

        return checkResult;
    }

    private boolean checkLinesDefensiveMove(char opponent) {

        int sumWinningLine = (opponent * (fieldSize - 1)) + (int)DEFAULT_FIELD_VALUE;
        int winningLines = -1;
        boolean checkResult = false;
        int sumCheckedLine;

        for (int i = 0; i < fieldSize; i++) {

            sumCheckedLine = 0;

            for (int j = 0; j < fieldSize; j++) {
                sumCheckedLine += field[i][j];
            }

            if (sumCheckedLine == sumWinningLine) {
                checkResult = true;
                winningLines = i;
                verticalCoordinate = winningLines;
                break;
            }
        }

        if (winningLines != -1) {
            for (int j = 0; j < fieldSize; j++) {
                if (field[winningLines][j] == DEFAULT_FIELD_VALUE) {
                    horizontalCoordinate = j;
                }
            }
        }

        return checkResult;
    }

    private boolean checkColumnsDefensiveMove(char opponent) {

        int sumWinningLine = (opponent * (fieldSize - 1)) + (int)DEFAULT_FIELD_VALUE;
        int winningColumns = -1;
        boolean checkResult = false;
        int sumCheckedLine;

        for (int j = 0; j < fieldSize; j++) {

            sumCheckedLine = 0;

            for (int i = 0; i < fieldSize; i++) {
                sumCheckedLine += field[i][j];
            }

            if (sumCheckedLine == sumWinningLine) {
                checkResult = true;
                winningColumns = j;
                horizontalCoordinate = winningColumns;
                break;
            }
        }

        if (winningColumns != -1) {
            for (int i = 0; i < fieldSize; i++) {
                if (field[i][winningColumns] == DEFAULT_FIELD_VALUE) {
                    verticalCoordinate = i;
                }
            }
        }

        return checkResult;
    }

    private boolean checkDiagonalLeftDefensiveMove(char opponent) {

        int sumWinningDiagonal = (opponent * (fieldSize - 1)) + (int)DEFAULT_FIELD_VALUE;
        boolean checkResult = false;
        int sumCheckedDiagonal = 0;

        for (int i = 0; i < fieldSize; i++) {
            sumCheckedDiagonal += field[i][i];
        }

        if (sumCheckedDiagonal == sumWinningDiagonal) {
            checkResult = true;

            for (int i = 0; i < fieldSize; i++) {
                if (field[i][i] == DEFAULT_FIELD_VALUE) {
                    verticalCoordinate = i;
                    horizontalCoordinate = i;
                }
            }
        }

        return checkResult;
    }

    private boolean checkDiagonalRightDefensiveMove(char opponent) {

        int sumWinningDiagonal = (opponent * (fieldSize - 1)) + (int)DEFAULT_FIELD_VALUE;
        boolean checkResult = false;
        int sumCheckedDiagonal = 0;

        for (int i = 0; i < fieldSize; i++) {
            sumCheckedDiagonal += field[(fieldSize - 1) - i][i];
        }

        if (sumCheckedDiagonal == sumWinningDiagonal) {
            checkResult = true;

            for (int i = 0; i < fieldSize; i++) {
                if (field[(fieldSize - 1) - i][i] == DEFAULT_FIELD_VALUE) {
                    verticalCoordinate = (fieldSize - 1) - i;
                    horizontalCoordinate = i;
                }
            }
        }

        return checkResult;
    }
}