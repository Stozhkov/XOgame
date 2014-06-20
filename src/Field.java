public class Field {

    private static final char DEFAULT_FIELD_VALUE = ' ';
    private final int fieldSize;
    private char field[][];

    public Field(int fieldSize) {

        this.fieldSize = fieldSize;
        field = new char[fieldSize][fieldSize];
        setFieldsDefault();
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
}