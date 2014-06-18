import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static final int DEFAULT_MIN_FIELD_SIZE = 3;
    public static final int DEFAULT_MAX_FIELD_SIZE = 10;

    public static void main(String[] args) {
        boolean endGame = false;

        Player player = new Player();

        System.out.println("*********************************************");
        System.out.println("Игра крестики - нолики");
        System.out.println("*********************************************");
        System.out.print("Введите размер игрового поля: ");
        Field field = new Field(getPlayerNum(DEFAULT_MIN_FIELD_SIZE, DEFAULT_MAX_FIELD_SIZE));

        System.out.println("1 игрок выбирает за кого он будет играть.");
        System.out.println("  Введите 1 если будите играть Х");
        System.out.println("  Введите 2 если будите играть О");

        player.changePlayer();
        player.playerSelection();

        while (!endGame){
            field.showFields();
            player.printWhoseMove();
            field.movePlayer(player.getWhoseNextMove());
            endGame = field.validation(player.getWhoseNextMove());
            player.changePlayer();
        }

        field.showFields();
    }

    public static int getPlayerNum(int minValue, int maxValue) {
        Scanner reader = new Scanner(System.in);
        int playerNum;

        try {
            playerNum = reader.nextInt();
        }
        catch (InputMismatchException e)
        {
            System.out.print("Вы ввели не число. Введите еще раз: ");
            playerNum = getPlayerNum(minValue, maxValue);
        }

        if (playerNum > 0 && playerNum <= minValue) {
            return playerNum;
        }
        else {
            System.out.print("Введенное вами число выходит за пределы разрешенного диапазона. Введите еще раз: ");
        }

        return getPlayerNum(minValue, maxValue);
    }
}
