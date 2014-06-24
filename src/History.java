public class History {

    private int stack [][];
    private final int stackSize;
    private int stackPosition = -1;

    public History(int stackSize) {
        this.stackSize = stackSize * stackSize;
        stack = new int[this.stackSize][3];
    }

    public int getStackSize() {
        return stackSize;
    }

    public void addToStack(int verticalCoordinate, int horizontalCoordinate, char whoseNextMove) {
        stackPosition++;
        stack[stackPosition][0] = verticalCoordinate;
        stack[stackPosition][1] = horizontalCoordinate;
        stack[stackPosition][2] = (int)whoseNextMove;
    }

    public void printStack () {
        System.out.println("---лог старт-------------");
        for (int i = 0; i <= stackPosition; i++) {
            System.out.print("Игрок " + (char)stack[i][2] + " сходил в ячейку ");
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    System.out.print(stack[i][j] + ", ");
                } else {
                    System.out.print(stack[i][j]);
                }
            }
            System.out.println("");
        }
        System.out.println("---лог стоп--------------");
    }
}
