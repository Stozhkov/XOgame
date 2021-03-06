import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static final int MIN_FIELD_SIZE = 2;
    public static final int MAX_FIELD_SIZE = 10;

    public static void main(String[] args) {

        int verticalCoordinate;
        int horizontalCoordinate;

        boolean endGame = false;
        boolean playerMadeMove;
        boolean isComputer;
        boolean isComputerMove = false;

        System.out.println("*******************************************************");
        System.out.println("Игра крестики - нолики");
        System.out.println("*******************************************************");

        System.out.println("С кем будите играть?.");
        System.out.println("  Введите 1 если будите играть с компьютером");
        System.out.println("  Введите 2 если будите играть с человеком");
        System.out.print("Ожидается ввод пользователя: ");
        int playerNum = getPlayerNum(1, 2);

        switch (playerNum) {
            case 1:
                isComputer = true;
                break;
            default:
                isComputer = false;
                break;
        }

        Player player = new Player();

        System.out.println("Задайте размер игрового поля.");
        System.out.print("Диапазон допустимых значений от " + MIN_FIELD_SIZE + " до " + MAX_FIELD_SIZE + ": ");
        Field field = new Field(getPlayerNum(MIN_FIELD_SIZE, MAX_FIELD_SIZE));

        System.out.println("1 игрок выбирает за кого он будет играть.");
        System.out.println("  Введите 1 если будите играть Х");
        System.out.println("  Введите 2 если будите играть О");
        System.out.print("Ожидается ввод пользователя: ");

        player.playerSelection(getPlayerNum(1, 2));

        System.out.println("*******************************************************");
        System.out.println("Игра началась!");
        field.showFields();

        while (!endGame){

            if (isComputer && isComputerMove) {

                System.out.println("Ход компьютера.");
                field.think(player.getWhoseNextMove(), player.whoOpponent());
                endGame = field.validation(player.getWhoseNextMove());
                player.changePlayer();
                isComputerMove = !isComputerMove;



            } else {

                player.printWhoseMove();
                playerMadeMove = false;

                while (!playerMadeMove) {

                    System.out.print("Введите координату по вертикальной оси от 1 до " + field.getFieldSize() + ":");
                    verticalCoordinate = getPlayerNum(1, field.getFieldSize());

                    System.out.print("Введите координату по горизонтальной оси от 1 до " + field.getFieldSize() + ":");
                    horizontalCoordinate = getPlayerNum(1, field.getFieldSize());

                    playerMadeMove = field.setFields(verticalCoordinate - 1, horizontalCoordinate - 1, player.getWhoseNextMove());
                }

                endGame = field.validation(player.getWhoseNextMove());
                player.changePlayer();
                isComputerMove = !isComputerMove;
            }
            field.showFields();
        }
    }

    public static int getPlayerNum(int minValue, int maxValue) {

        Scanner reader = new Scanner(System.in);
        int playerNum;

        try {
            playerNum = reader.nextInt();
        }
        catch (InputMismatchException e)
        {
            System.out.println("Вы ввели не число.");
            System.out.print("Введите еще раз: ");
            playerNum = getPlayerNum(minValue, maxValue);
        }

        if (playerNum <= maxValue && playerNum >= minValue) {
            return playerNum;
        }
        else {
            System.out.println("Введенное вами число выходит за пределы разрешенного диапазона.");
            System.out.println("Диапазон допустимых значений от " + minValue + " до " + maxValue);
            System.out.print("Введите еще раз: ");
        }

        return getPlayerNum(minValue, maxValue);
    }
}