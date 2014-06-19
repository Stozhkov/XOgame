public class Field {

    private static final char DEFAULT_FIELD_VALUE = ' ';

    public int getFieldSize() {
        return fieldSize;
    }

    private final int fieldSize;

    private char field[][];

    public Field(int fieldSize) {
        this.fieldSize = fieldSize;
        field = new char[fieldSize][fieldSize];
        setFieldsDefault();
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

    private void setFieldsDefault() {
        for (int i = 0; i < fieldSize; i++){
            for (int j = 0; j < fieldSize; j++)
            {
                field[i][j] = DEFAULT_FIELD_VALUE;
            }
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
        for (int i = 0; i < fieldSize; i++) {

            char cell = 0;

            if (field[i][0] != DEFAULT_FIELD_VALUE) {
                cell = field[i][0];
            }
            for (int j = 1; j < fieldSize; j++) {
                if (field[i][j] == cell) {
                    if (j == (fieldSize - 1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkColumns() {
        for (int j = 0; j < fieldSize; j++) {

            char cell = 0;

            if (field[0][j] != DEFAULT_FIELD_VALUE) {
                cell = field[0][j];
            }
            for (int i = 1; i < fieldSize; i++) {
                if (field[i][j] == cell) {
                    if (i == (fieldSize - 1)) {
                        return true;
                    }
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

        for (int i = 1; i < fieldSize; i++) {

            if (field[i][i] == cell) {
                if (i == (fieldSize - 1)) {
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

        if (field[fieldSize -1][0] != DEFAULT_FIELD_VALUE) {
            cell = field[fieldSize -1][0];
        } else {
            return false;
        }

        for (int i = 1; i < fieldSize; i++)
            if (field[fieldSize -1-i][i] == cell) {
                if (i == (fieldSize - 1)) {
                    return true;
                }
            } else {
                return false;
            }
        return false;
    }

    private boolean checkNoMoves() {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++){
                if (field[i][j] == DEFAULT_FIELD_VALUE) {
                    return false;
                }
            }
        }
        return true;
    }
}