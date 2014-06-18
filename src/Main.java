public class Main {
    public static void main(String[] args) {
        boolean endGame = false;
        Field field = new Field();
        Player player = new Player();

        System.out.println("*********************************************");
        System.out.println("Игра крестики - нолики");
        System.out.println("*********************************************");
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
}
