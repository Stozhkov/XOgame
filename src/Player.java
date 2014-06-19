public class Player {

    private char whoseNextMove = ' ';

    public void playerSelection(int numberOfUsers) {

        if (numberOfUsers == 1) {
            whoseNextMove = 'X';
        } else if (numberOfUsers == 2) {
            whoseNextMove = 'O';
        } else {
            System.out.println("Какого черта?...");
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
